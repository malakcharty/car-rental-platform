package org.sid.payment_service.dto;
import lombok.Data;
import org.sid.payment_service.enums.PaymentProvider;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long rentalId;
    private BigDecimal amount;
    private PaymentProvider provider;
}
