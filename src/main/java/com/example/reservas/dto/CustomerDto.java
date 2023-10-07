package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @lombok.Getter(onMethod_ = {@JsonProperty("p_customer_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_customer_id")})
    private Long id;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_customer_person_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_customer_person_id")})
    private Long person;
}
