package org.sid.rental_service.clients;

import org.sid.rental_service.dto.PaymentRequest;
import org.sid.rental_service.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
