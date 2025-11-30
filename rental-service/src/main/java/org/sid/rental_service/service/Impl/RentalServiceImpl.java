package org.sid.rental_service.service.Impl;

import org.sid.rental_service.clients.CarClient;
import org.sid.rental_service.dto.CarResponse;
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
    private final CarClient carClient;

    public RentalServiceImpl(RentalRepository rentalRepository,
                             CustomerRepository customerRepository,
                             CarClient carClient) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.carClient = carClient;
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
    public Rental createRental(Long carId,
                               Customer customer,
                               LocalDate startDate,
                               LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new RuntimeException("End date must be after start date");
        }

        CarResponse car = carClient.getCarById(carId);

        if (car == null) {
            throw new RuntimeException("Car not found with id " + carId);
        }

        if (!"AVAILABLE".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("Car is not available");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days == 0) days = 1;

        BigDecimal totalPrice = car.getDailyPrice()
                .multiply(BigDecimal.valueOf(days));

        Customer savedCustomer = customerRepository.save(customer);

        Rental rental = new Rental();
        rental.setCarId(carId);
        rental.setCustomer(savedCustomer);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setTotalPrice(totalPrice);
        rental.setStatus(RentalStatus.PENDING);
        Rental saved = rentalRepository.save(rental);

        carClient.updateCarStatus(carId, "RESERVED");

        return saved;
    }

    @Override
    public Rental updateStatus(Long rentalId, RentalStatus status) {
        Rental rental = getRentalById(rentalId);
        rental.setStatus(status);
        Rental updated = rentalRepository.save(rental);

        if (status == RentalStatus.CONFIRMED) {
            carClient.updateCarStatus(rental.getCarId(), "RENTED");
        } else if (status == RentalStatus.CANCELLED || status == RentalStatus.COMPLETED) {
            carClient.updateCarStatus(rental.getCarId(), "AVAILABLE");
        }

        return updated;
    }


    @Override
    public List<Rental> getRentalsByStatus(RentalStatus status) {
        return rentalRepository.findByStatus(status);
    }
}
