package com.cardealership.dealership.Locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/locations")
public class LocationsController {
    @Autowired
    private final LocationsRepository locationsRepository;

    public LocationsController(LocationsRepository locationsRepository){
        this.locationsRepository = locationsRepository;
    }

    @GetMapping("")
    public Iterable<Location> allLocations(){
        Iterable<Location> locations = locationsRepository.findAll();
        return locations;
    }

    @GetMapping("/{id}")
    public Location oneLocation(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Location location = locationsRepository.findById(longId).get();
        return location;
    }

    @PostMapping("")
    public Location newLocation(@RequestBody Location location){
        return this.locationsRepository.save(location);
    }

    @PatchMapping("/{id}")
    public Location updateLocation(@PathVariable String id, @RequestBody Location location){
        Long longId = Long.parseLong(id);
        Location toUpdate = locationsRepository.findById(longId).get();
        if(location.getName()!=null){
            toUpdate.setName(location.getName());
        }
        if(location.getAddress()!=null){
            toUpdate.setAddress(location.getAddress());
        }
        return this.locationsRepository.save(toUpdate);
    }

    @DeleteMapping("/{id}")
    public Location deleteLoc(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Location toDelete = this.locationsRepository.findById(longId).get();
        this.locationsRepository.delete(toDelete);
        return toDelete;
    }
}
