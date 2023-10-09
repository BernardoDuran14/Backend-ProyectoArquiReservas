package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_user_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_user_id")})
    private Long id;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_user_username")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_user_username")})
    private String username;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_user_password")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_user_password")})
    private String password;
}
