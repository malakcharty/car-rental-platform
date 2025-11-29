package org.sid.car_service.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.car_service.enums.CarCategory;
import org.sid.car_service.enums.CarStatus;

import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    @Enumerated(EnumType.STRING)
    private CarCategory category;
    private BigDecimal dailyPrice;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
}