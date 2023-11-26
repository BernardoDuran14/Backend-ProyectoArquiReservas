package com.example.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspaciosDisponiblesDto {
    Long id;
    String nombreEspacio;
    Boolean disponible;
}
