package org.sid.rental_service.service.Impl;

import org.sid.rental_service.entities.Customer;
import org.sid.rental_service.entities.Rental;
import org.sid.rental_service.enums.RentalStatus;
import org.sid.rental_service.repositories.CustomerRepository;
import org.sid.rental_service.repositories.RentalRepository;
import org.sid.rental_service.service.RentalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;

    public RentalServiceImpl(RentalRepository rentalRepository,
                             CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id " + id));
    }
    @Override
    public Rental createRental(Rental rental) {

        Customer savedCustomer = null;
        if (rental.getCustomer() != null) {
            savedCustomer = customerRepository.save(rental.getCustomer());
            rental.setCustomer(savedCustomer);
        }

        if (rental.getStatus() == null) {
            rental.setStatus(RentalStatus.PENDING);
        }

        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateStatus(Long rentalId, RentalStatus status) {
        Rental rental = getRentalById(rentalId);
        rental.setStatus(status);
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getRentalsByStatus(RentalStatus status) {
        return rentalRepository.findByStatus(status);
    }
}
