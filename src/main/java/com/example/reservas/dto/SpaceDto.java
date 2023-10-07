package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDto {

    @lombok.Getter(onMethod_ = {@JsonProperty("p_space_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_space_id")})
    private Long id;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_space_name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_space_name")})
    private String name;

    @lombok.Getter(onMethod_ = {@JsonProperty("p_space_floor_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("p_space_floor_id")})
    private Long floor;

}
