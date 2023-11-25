package com.example.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitReservaDto {
    private String name;
    private String fechaInicio; // forma
    private String fechaFin;
    private String horaInicio;
    private String horaFin;
}
