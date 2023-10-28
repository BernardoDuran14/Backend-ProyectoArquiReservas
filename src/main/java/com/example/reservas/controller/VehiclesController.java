package com.example.reservas.controller;

import com.example.reservas.dto.VehiclesDto;
import com.example.reservas.dto.VehiclesDtoSpecial;
import com.example.reservas.entity.Vehicles;
import com.example.reservas.service.impl.VehiclesImpl;
import com.example.reservas.service.inter.VehiclesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesImpl vehiclesService;

    @GetMapping("/all")
    public ResponseEntity<List<VehiclesDto>> getAllVehicles() {
        try {
            return ResponseEntity.ok(vehiclesService.getAllVehicles());
        } catch (Exception e) {
            log.error("Error al obtener la lista de vehiculos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicles> getVehiclesById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehiclesService.getVehiclesById(id));
        } catch (Exception e) {
            log.error("Error al obtener el vehiculo", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveVehicles(@RequestBody VehiclesDto vehiclesDto) {
        try {
            log.info("Vehiculo a guardar: {}", vehiclesDto);
            vehiclesService.saveVehicles(vehiclesDto);
            return ResponseEntity.ok("Vehiculo guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al guardar el vehiculo");
        }
    }

    @PostMapping("/save_both")
    public ResponseEntity<String> saveVehiclesAndCustomer(@RequestBody VehiclesDtoSpecial vehicles) {
        try {
            log.info("Vehiculo a guardar: {}", vehicles);
            vehiclesService.saveVehiclesAndCustomer(vehicles);
            return ResponseEntity.ok("Vehiculo guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al guardar el vehiculo");
        }
    }

    /*
    haz un dato de prueba para el metodo anterior
    {
        "id": 1,
        "brand": "Toyota",
        "model": "Corolla",
        "color": "Rojo",
        "licensePlate": "ABC123",
        "customer": {
            "id": 1,
            "person": {

                "name": "Juan",
                "surname": "Perez",
                "dni": "12345678",
                "phone": "987654321",
                user
            },
    */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicles(@PathVariable Long id) {
        try {
            vehiclesService.deleteVehicles(id);
            return ResponseEntity.ok("Vehiculo eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al eliminar el vehiculo");
        }
    }
}

/* haz un dato de prueba para insertar con postman
{
    "id": 1,
    "brand": "Toyota",
    "model": "Corolla",
    "color": "Rojo",
    "licensePlate": "ABC123",
    "customer": 1
}
*/
