package org.sid.rental_service.web;

import lombok.RequiredArgsConstructor;
import org.sid.rental_service.dto.CreateRentalRequest;
import org.sid.rental_service.entities.Customer;
import org.sid.rental_service.entities.Rental;
import org.sid.rental_service.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public Rental createRental(@RequestBody CreateRentalRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        return rentalService.createRental(
                request.getCarId(),
                customer,
                request.getStartDate(),
                request.getEndDate()
        );
    }


    @GetMapping("/{id}")
    public Rental getRental(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @GetMapping
    public List<Rental> allRentals() {
        return rentalService.getAllRentals();
    }
}
