package com.example.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.reservas.dto.PersonDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoSpecial {

    private Long id;
    private PersonDto person;
}
