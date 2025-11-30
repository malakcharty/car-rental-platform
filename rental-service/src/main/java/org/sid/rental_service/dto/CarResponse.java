package org.sid.rental_service.dto;



import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarResponse {
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private String category;   // ECONOMY, SUV, ...
    private BigDecimal dailyPrice;
    private String status;     // AVAILABLE, RENTED, MAINTENANCE
}

