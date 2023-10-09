package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesDto {

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_vehicles_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_vehicles_id")})
    private Long id;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_vehicles_brand")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_vehicles_brand")})
    private String brand;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_vehicles_color")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_vehicles_color")})
    private String color;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_vehicles_license_plate")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_vehicles_license_plate")})
    private String licensePlate;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_vehicles_customer_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_vehicles_customer_id")})
    private Long customer;
}
