package org.sid.rental_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.rental_service.enums.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Rental {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    @ManyToOne
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;
}
