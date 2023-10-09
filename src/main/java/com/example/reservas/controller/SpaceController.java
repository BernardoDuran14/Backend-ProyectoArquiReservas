package com.example.reservas.controller;

import com.example.reservas.dto.SpaceDto;
import com.example.reservas.service.inter.SpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping("/all")
    public ResponseEntity<List<SpaceDto>> getAllFloors() {
        try {
            return ResponseEntity.ok(spaceService.getAllSpaces());
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceDto> getFloorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(spaceService.getSpaceById(id));
        } catch (Exception e) {
            log.error("Error al obtener el piso", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/floor/{id}")
    public ResponseEntity<List<SpaceDto>> getAllFloorsByFloor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(spaceService.getAllSpacesByFloor(id));
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFloor(@RequestBody SpaceDto spaceDto) {
        try {
            spaceService.saveSpace(spaceDto);
            return ResponseEntity.ok("Piso guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el piso", e);
            return ResponseEntity.badRequest().body("Error al guardar el piso");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFloor(@RequestBody SpaceDto spaceDto) {
        try {
            spaceService.updateSpace(spaceDto);
            return ResponseEntity.ok("Piso actualizado correctamente");
        } catch (Exception e) {
            log.error("Error al actualizar el piso", e);
            return ResponseEntity.badRequest().body("Error al actualizar el piso");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFloor(@PathVariable Long id) {
        try {
            spaceService.deleteSpace(id);
            return ResponseEntity.ok("Piso eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar el piso", e);
            return ResponseEntity.badRequest().body("Error al eliminar el piso");
        }
    }
}
