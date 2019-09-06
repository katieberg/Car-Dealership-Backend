package com.cardealership.dealership.Cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private final CarsRepository carsRepository;

    public CarsController(CarsRepository carsRepository){
        this.carsRepository = carsRepository;
    }

    @GetMapping("")
    public Iterable<Car> allCars(){
        Iterable<Car> cars = carsRepository.findAll();
        return cars;
    }

    @GetMapping("/{locId}")
    public Iterable<Car> carsByLocation(@PathVariable String locId){
        Long longId = Long.parseLong(locId);
        Iterable<Car> cars = carsRepository.findByLocation_Id(longId);
        return cars;
    }

    @GetMapping("/car/{carId}")
    public Car carById(@PathVariable String carId){
        Long longId = Long.parseLong(carId);
        Car toReturn = carsRepository.findById(longId).get();
        return toReturn;
    }

    @PostMapping("")
    public Car newCar(@RequestBody Car car){
        return this.carsRepository.save(car);
    }

    @PatchMapping("/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable String id){
        Long carId = Long.parseLong(id);
        Car toUpdate = carsRepository.findById(carId).get();
        if(car.getVin()!=null){
            toUpdate.setVin(car.getVin());
        }
        if(car.getYear()!=null){
            toUpdate.setYear(car.getYear());
        }
        if(car.getMake()!=null){
            toUpdate.setMake(car.getMake());
        }
        if(car.getModel()!=null){
            toUpdate.setModel(car.getModel());
        }
        if(car.getMiles()!=null){
            toUpdate.setMiles(car.getMiles());
        }
        if(car.getPrice()!=null){
            toUpdate.setPrice(car.getPrice());
        }
        if(car.getPhoto_url()!=null){
            toUpdate.setPhoto_url(car.getPhoto_url());
        }
        if(car.getLocation()!=null){
            toUpdate.setLocation(car.getLocation());
        }
        return this.carsRepository.save(toUpdate);
    }

    @DeleteMapping("/{id}")
    public Car deleteCar(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Car toDelete = this.carsRepository.findById(longId).get();
        this.carsRepository.delete(toDelete);
        return toDelete;
    }

}
