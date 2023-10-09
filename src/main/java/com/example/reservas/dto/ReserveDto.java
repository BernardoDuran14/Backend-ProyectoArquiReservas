package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDto {

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_id")})
    private Long id;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_customer_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_customer_id")})
    private Long customer;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_space_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_space_id")})
    private Long space;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_employee_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_employee_id")})
    private Long employee;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_vehicles_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_vehicles_id")})
    private Long vehicles;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_def")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_def")})
    private Boolean def;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_start_date")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_start_date")})
    private String startDate;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_reserve_end_date")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_reserve_end_date")})
    private String endDate;
}
