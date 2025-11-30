package org.sid.car_service.services;

import org.sid.car_service.entities.Car;
import org.sid.car_service.enums.CarStatus;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Long id);
    Car createCar(Car car);
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);

    List<Car> getAvailableCars();
    Car updateCarStatus(Long id, CarStatus status);
}