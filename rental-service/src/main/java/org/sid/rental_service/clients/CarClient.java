package org.sid.rental_service.clients;


import org.sid.rental_service.dto.CarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "car-service")
public interface CarClient {

    @GetMapping("/cars/{id}")
    CarResponse getCarById(@PathVariable("id") Long id);
    @PutMapping("/cars/{id}/status")
    void updateCarStatus(@PathVariable("id") Long id,
                         @RequestParam("status") String status);

}
