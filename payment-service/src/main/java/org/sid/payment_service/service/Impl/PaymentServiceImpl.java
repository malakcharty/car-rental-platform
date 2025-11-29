package org.sid.payment_service.service.Impl;
import org.sid.payment_service.entities.Payment;
import org.sid.payment_service.enums.PaymentProvider;
import org.sid.payment_service.enums.PaymentStatus;
import org.sid.payment_service.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.sid.payment_service.service.PaymentService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

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
}

