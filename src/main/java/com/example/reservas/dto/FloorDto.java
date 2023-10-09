package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDto {

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_floor_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_floor_id")})
    private Long id;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_floor_name")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_floor_name")})
    private String name;
}
