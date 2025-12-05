package org.sid.rental_service.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class PaymentResponse {
    private Long id;
    private Long rentalId;
    private BigDecimal amount;
    private String status;
    private String provider;
    private String transactionId;
}
