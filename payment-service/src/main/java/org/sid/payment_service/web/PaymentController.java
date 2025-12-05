package org.sid.payment_service.web;


import lombok.RequiredArgsConstructor;
import org.sid.payment_service.dto.PaymentRequest;
import org.sid.payment_service.entities.Payment;
import org.sid.payment_service.enums.PaymentProvider;
import org.sid.payment_service.enums.PaymentStatus;
import org.sid.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(
            @RequestParam Long rentalId,
            @RequestParam BigDecimal amount,
            @RequestParam PaymentProvider provider
    ) {
        return paymentService.createPayment(rentalId, amount, provider);
    }

    @PutMapping("/{paymentId}/status")
    public Payment updateStatus(
            @PathVariable Long paymentId,
            @RequestParam PaymentStatus status,
            @RequestParam(required = false) String transactionId
    ) {
        return paymentService.updateStatus(paymentId, status, transactionId);
    }

    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/rental/{rentalId}")
    public List<Payment> getPaymentsByRental(@PathVariable Long rentalId) {
        return paymentService.getPaymentsByRental(rentalId);
    }
    @PostMapping("/process")
    public Payment processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

}
