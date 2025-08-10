package com.example.pmsproject.Controller;

import com.example.pmsproject.Service.PaymentsService;
import com.example.pmsproject.entity.Payments;
import com.example.pmsproject.entity.Property;
import com.example.pmsproject.entity.Tenant;
import com.example.pmsproject.repository.PropertyRepository;
import com.example.pmsproject.repository.TenantRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    private final PaymentsService paymentsService;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;

    public PaymentController(PaymentsService paymentsService, PropertyRepository propertyRepository, TenantRepository tenantRepository) {
        this.paymentsService = paymentsService;
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestParam Long propertyId, @RequestParam Long tenantId) {
        try {
            Stripe.apiKey = stripeSecretKey;
            System.out.println("Property ID: " + propertyId); // Debugging
            System.out.println("Tenant ID: " + tenantId); // Debugging
            Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);

            if (propertyOpt.isEmpty() || tenantOpt.isEmpty()) {
                System.out.println("Invalid property or tenant ID");
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid property or tenant ID"));
            }

            Property property = propertyOpt.get();
            Tenant tenant = tenantOpt.get();

            // Save PENDING payment
            Payments payment = new Payments(property, tenant, property.getPrice(), "PENDING", LocalDateTime.now());
            Payments savedPayment = paymentsService.generatePayments(payment);

            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/payment-success?session_id={CHECKOUT_SESSION_ID}&paymentId=" + savedPayment.getPaymentId())
                    .setCancelUrl("http://localhost:3000/payment-cancel")
                    .putMetadata("paymentId", String.valueOf(savedPayment.getPaymentId()))
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(property.getPropertyName())
                                                    .build())
                                            .setUnitAmount((long) (property.getPrice() * 100)) // Convert price to cents
                                            .build())
                                    .setQuantity(1L)
                                    .build())
                    .build();

            Session session = Session.create(params);

            // Update payment with session ID
            savedPayment.setStripeSessionId(session.getId());
            paymentsService.generatePayments(savedPayment);

            return ResponseEntity.ok(Map.of("checkoutUrl", session.getUrl()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Payment failed: " + e.getMessage()));
        }
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(
            @RequestParam String session_id,
            @RequestParam Long paymentId
    ) {
        Optional<Payments> paymentOpt = paymentsService.getPaymentById(paymentId);
        if (paymentOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid payment ID");
        }

        Payments payment = paymentOpt.get();
        payment.setStatus("SUCCESS");
        paymentsService.generatePayments(payment);

        return ResponseEntity.ok("Payment successful! Session ID: " + session_id);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sig) {
        try {
            Event event = Webhook.constructEvent(payload, sig, "YOUR_STRIPE_WEBHOOK_SECRET");

            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getData().getObject();
                String paymentId = session.getMetadata().get("paymentId");

                Optional<Payments> paymentOpt = paymentsService.getPaymentById(Long.parseLong(paymentId));
                if (paymentOpt.isPresent()) {
                    Payments payment = paymentOpt.get();
                    payment.setStatus("SUCCESS");
                    paymentsService.generatePayments(payment);
                }
            }
            return ResponseEntity.ok("Received");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook error: " + e.getMessage());
        }
    }


    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Payment was canceled. Please try again.");
    }
}
