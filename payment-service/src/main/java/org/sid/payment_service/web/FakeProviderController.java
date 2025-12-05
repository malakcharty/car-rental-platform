package org.sid.payment_service.web;

import org.sid.payment_service.dto.PaymentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fake-provider")
public class FakeProviderController {

    @PostMapping
    public Map<String, Object> fakePay(@RequestBody PaymentRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Paiement simulé avec succès");
        response.put("rentalId", request.getRentalId());
        response.put("amount", request.getAmount());
        response.put("provider", request.getProvider());
        return response;
    }
}
