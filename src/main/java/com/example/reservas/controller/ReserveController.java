package com.example.reservas.controller;
import com.example.reservas.dto.InitReservaDto;
import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.Space;
import com.example.reservas.service.inter.ReserveService;
import com.example.reservas.service.inter.SpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/all")
    public ResponseEntity<List<Space>> getAllFloors(@RequestBody InitReservaDto initReservaDto) {
        try {
            return ResponseEntity.ok(reserveService.getAllReservesAvailables(initReservaDto));
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
