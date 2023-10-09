package com.example.reservas.controller;

import com.example.reservas.dto.FloorDto;
import com.example.reservas.service.impl.FloorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/floors")
public class FloorController {

    @Autowired
    private FloorImpl floorService;

    @GetMapping("/all")
    public ResponseEntity<List<FloorDto>> getAllFloors() {
        try {
            return ResponseEntity.ok(floorService.getAllFloors());
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorDto> getFloorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(floorService.getFloorById(id));
        } catch (Exception e) {
            log.error("Error al obtener el piso", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFloor(@RequestBody FloorDto floorDto) {
        try {
            floorService.saveFloor(floorDto);
            return ResponseEntity.ok("Piso guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el piso", e);
            return ResponseEntity.badRequest().body("Error al guardar el piso");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFloor(@RequestBody FloorDto floorDto) {
        try {
            floorService.updateFloor(floorDto);
            return ResponseEntity.ok("Piso actualizado correctamente");
        } catch (Exception e) {
            log.error("Error al actualizar el piso", e);
            return ResponseEntity.badRequest().body("Error al actualizar el piso");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFloor(@PathVariable Long id) {
        try {
            floorService.deleteFloor(id);
            return ResponseEntity.ok("Piso eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar el piso", e);
            return ResponseEntity.badRequest().body("Error al eliminar el piso");
        }
    }

}
