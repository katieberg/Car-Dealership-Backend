package com.cardealership.dealership.Cars;

import com.cardealership.dealership.Locations.Location;
import lombok.Data;

import javax.persistence.*;

import javax.persistence.GeneratedValue;


@Entity
@Data
@Table (name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String vin;


    @Column
    private Integer year;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer miles;

    @Column
    private Integer price;

    @Column
    private String photo_url;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
}
