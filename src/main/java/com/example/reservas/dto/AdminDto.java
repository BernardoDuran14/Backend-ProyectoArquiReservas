package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    @lombok.Getter(onMethod_ = {@JsonProperty("p_admin_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_admin_id")})
    private Long id;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_admin_person_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_admin_person_id")})
    private Long person;
}
