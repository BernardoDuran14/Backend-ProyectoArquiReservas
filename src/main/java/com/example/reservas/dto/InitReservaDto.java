package com.example.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitReservaDto {
    private String name;
    private Date fechaInicio; // forma
    private Date fechaFin;
    private int horaInicio;
    private int horaFin;
}
