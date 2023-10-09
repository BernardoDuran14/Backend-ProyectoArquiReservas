package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_vehicles")
public class Vehicles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_vehicles_id")
    private Long id;

    @Column(name = "p_vehicles_brand")
    private String brand;

    @Column(name = "p_vehicles_color")
    private String color;

    @Column(name = "p_vehicles_license_plate")
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "p_vehicles_customer_id", referencedColumnName = "p_customer_id")
    private Customer customer;
}
