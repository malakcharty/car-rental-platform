package org.sid.rental_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long rentalId;
    private BigDecimal amount;
    private String provider;
}
