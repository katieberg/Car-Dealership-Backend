package com.cardealership.dealership.Locations;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.GeneratedValue;


@Entity
@Data
@Table (name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

}
