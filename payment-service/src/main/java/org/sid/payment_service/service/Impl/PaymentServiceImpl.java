package org.sid.payment_service.service.Impl;

import lombok.RequiredArgsConstructor;
import org.sid.payment_service.dto.PaymentRequest;
import org.sid.payment_service.entities.Payment;
import org.sid.payment_service.enums.PaymentProvider;
import org.sid.payment_service.enums.PaymentStatus;
import org.sid.payment_service.repositories.PaymentRepository;
import org.sid.payment_service.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;

    private static final String PAYMENT_PROVIDER_URL = "http://localhost:8083/fake-provider";

    @Override
    public Payment createPayment(Long rentalId,
                                 BigDecimal amount,
                                 PaymentProvider provider) {

        Payment payment = new Payment();
        payment.setRentalId(rentalId);
        payment.setAmount(amount);
        payment.setProvider(provider);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updateStatus(Long paymentId,
                                PaymentStatus status,
                                String transactionId) {
        Payment payment = getPaymentById(paymentId);
        payment.setStatus(status);
        payment.setTransactionId(transactionId);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    @Override
    public List<Payment> getPaymentsByRental(Long rentalId) {
        return paymentRepository.findByRentalId(rentalId);
    }

    @Override
    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public Payment processPayment(PaymentRequest request) {

        Payment payment = new Payment();
        payment.setRentalId(request.getRentalId());
        payment.setAmount(request.getAmount());
        payment.setProvider(request.getProvider());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        try {
            webClient.post()
                    .uri(PAYMENT_PROVIDER_URL)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId(UUID.randomUUID().toString());

        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
        }

        return paymentRepository.save(payment);
    }
}
