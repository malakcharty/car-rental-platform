package org.sid.car_service.web;

import lombok.RequiredArgsConstructor;
import org.sid.car_service.entities.Car;
import org.sid.car_service.enums.CarStatus;
import org.sid.car_service.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> findAll() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.createCar(car);
    }
    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @PutMapping("/{id}/status")
    public Car updateStatus(@PathVariable Long id,
                            @RequestParam("status") CarStatus status) {
        return carService.updateCarStatus(id, status);
    }
}
