package com.example.reservas.controller;
import com.example.reservas.dto.EspaciosDisponiblesDto;
import com.example.reservas.dto.InitReservaDto;
import com.example.reservas.dto.ReserveDto;
import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.Reserve;
import com.example.reservas.entity.Space;
import com.example.reservas.service.inter.ReserveService;
import com.example.reservas.service.inter.SpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/all")
    public ResponseEntity<List<EspaciosDisponiblesDto>> getAllReservesAvailables(@RequestBody InitReservaDto initReservaDto) {
        try {
//            String notification = reserveService.sendNotification("Obteniendo lista de pisos disponibles");
            return ResponseEntity.ok(reserveService.getAllReservesAvailables(initReservaDto));
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }
//
//    @GetMapping("/not_availables")
//    public ResponseEntity<List<Space>> getAllReservesNotAvailables(@RequestBody InitReservaDto initReservaDto) {
//        try {
//            return ResponseEntity.ok(reserveService.getAllReservesAvailables(initReservaDto));
//        } catch (Exception e) {
//            log.error("Error al obtener la lista de pisos", e);
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/save")
    public ResponseEntity<String> saveReserve(@RequestBody ReserveDto reserveDto) {
        try {
//            String notification = reserveService.sendNotification("Guardando piso");
            reserveService.saveReserve(reserveDto);
            return ResponseEntity.ok("Piso guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el piso", e);
            return ResponseEntity.badRequest().body("Error al guardar el piso");
        }
    }

    @GetMapping("/user/{dni}")
    public ResponseEntity<List<Reserve>> getReservesByUser(@PathVariable Long dni) {
        try {
//            String notification = reserveService.sendNotification("Obteniendo lista de pisos");
            return ResponseEntity.ok(reserveService.getReservesByUser(dni));
        } catch (Exception e) {
            log.error("Error al obtener la lista de pisos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserve> getReserveById(@PathVariable Long id) {
        try {
//            String notification = reserveService.sendNotification("Obteniendo piso");
            return ResponseEntity.ok(reserveService.getReserveById(id));
        } catch (Exception e) {
            log.error("Error al obtener el piso", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserve(@PathVariable Long id) {
        try {
//            String notification = reserveService.sendNotification("Eliminando piso");
            reserveService.deleteReserve(id);
            return ResponseEntity.ok("Piso eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar el piso", e);
            return ResponseEntity.badRequest().body("Error al eliminar el piso");
        }
    }

//    @GetMapping("/test_notification")
//    public ResponseEntity<String> testNotification() {
//        try {
//            String notification = reserveService.sendNotification("Test de notificación");
//            return ResponseEntity.ok(reserveService.sendNotification(notification));
//        } catch (Exception e) {
//            log.error("Error al enviar la notificación", e);
//            return ResponseEntity.badRequest().body("Error al enviar la notificación");
//        }
//    }
}
