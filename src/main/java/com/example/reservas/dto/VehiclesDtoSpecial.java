package com.example.reservas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesDtoSpecial {

    private Long id;
    private String brand;
    private String color;
    private String licensePlate;
    private String etag;
    private CustomerDtoSpecial customer;

}
