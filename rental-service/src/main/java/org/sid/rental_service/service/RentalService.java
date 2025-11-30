package org.sid.rental_service.service;

import org.sid.rental_service.entities.Customer;
import org.sid.rental_service.entities.Rental;
import org.sid.rental_service.enums.RentalStatus;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {

    List<Rental> getAllRentals();
    Rental getRentalById(Long id);

    Rental createRental(Long carId,
                        Customer customer,
                        LocalDate startDate,
                        LocalDate endDate);

    Rental updateStatus(Long rentalId, RentalStatus status);

    List<Rental> getRentalsByStatus(RentalStatus status);
}