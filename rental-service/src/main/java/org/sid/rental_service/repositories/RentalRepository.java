package org.sid.rental_service.repositories;

import org.sid.rental_service.entities.Rental;
import org.sid.rental_service.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByStatus(RentalStatus status);

}
