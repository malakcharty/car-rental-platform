package org.sid.car_service.services.Impl;

import org.sid.car_service.entities.Car;
import org.sid.car_service.enums.CarStatus;
import org.sid.car_service.repositories.CarRepository;
import org.sid.car_service.services.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }

    @Override
    public Car createCar(Car car) {
        if (car.getStatus() == null) {
            car.setStatus(CarStatus.AVAILABLE);
        }
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existing = getCarById(id);
        existing.setRegistrationNumber(car.getRegistrationNumber());
        existing.setBrand(car.getBrand());
        existing.setModel(car.getModel());
        existing.setCategory(car.getCategory());
        existing.setDailyPrice(car.getDailyPrice());
        existing.setStatus(car.getStatus());
        return carRepository.save(existing);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }

    @Override
    public void updateCarStatus(Long id, CarStatus status) {
        Car car = getCarById(id);
        car.setStatus(status);
        carRepository.save(car);
    }
}