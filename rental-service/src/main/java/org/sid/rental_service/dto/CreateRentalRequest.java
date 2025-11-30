package org.sid.rental_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateRentalRequest {
    private Long carId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate startDate;
    private LocalDate endDate;
}
