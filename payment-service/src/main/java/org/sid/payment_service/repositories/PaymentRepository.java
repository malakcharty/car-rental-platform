package org.sid.payment_service.repositories;

import org.sid.payment_service.entities.Payment;
import org.sid.payment_service.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByRentalId(Long rentalId);
    List<Payment> findByStatus(PaymentStatus status);
}