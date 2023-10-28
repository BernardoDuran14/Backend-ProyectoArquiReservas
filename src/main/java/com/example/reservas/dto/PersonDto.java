package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_id")})
    private Long id;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_name")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_name")})
    private String name;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_surname")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_surname")})
    private String surname;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_email")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_email")})
    private String email;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_phone")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_phone")})
    private String phone;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_dni")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_dni")})
    private String dni;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_person_user_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_person_user_id")})
    private UserDto user;
}
