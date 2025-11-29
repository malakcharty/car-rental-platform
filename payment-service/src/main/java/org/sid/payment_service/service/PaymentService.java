package org.sid.payment_service.service;

import org.sid.payment_service.entities.Payment;
import org.sid.payment_service.enums.PaymentProvider;
import org.sid.payment_service.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    Payment createPayment(Long rentalId,
                          BigDecimal amount,
                          PaymentProvider provider);

    Payment updateStatus(Long paymentId,
                         PaymentStatus status,
                         String transactionId);

    Payment getPaymentById(Long id);

    List<Payment> getPaymentsByRental(Long rentalId);

    List<Payment> getPaymentsByStatus(PaymentStatus status);
}
