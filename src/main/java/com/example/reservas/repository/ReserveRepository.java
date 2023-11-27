package com.example.reservas.repository;

import com.example.reservas.entity.Reserve;
import com.example.reservas.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("SELECT r FROM Reserve r WHERE r.deleted = false and r.status = false and r.space.floor.id = :id")
    public List<Space> listaEspaciosDisponiblesPorPiso(@PathVariable Long id);

    // en el siguiente query, quiero ver los espacios disponibles por piso, pero que no esten reservados en un rango de fechas
    @Query("SELECT s FROM Reserve r, Space s " + // hacer los joins
            "WHERE r.deleted = false and r.space.id= s.id " +
            "and r.status = false and r.space.floor.name = :name " +
            "and r.startDate = :fechaInicio " +
            "and r.startTime = :horaInicio ")
    public List<Space> listaEspaciosNoDisponiblesPorPisoYFecha(@PathVariable String name, @PathVariable Date fechaInicio,
                                                               @PathVariable int horaInicio);
    /* convertir el anterior query a uno que pueda utilizar en consola de postgres
    SELECT s.id, s.name, s.floor_id, s.deleted, s.created_at, s.updated_at
    FROM reserves r, spaces s
    WHERE r.deleted = false and r.space_id = s.id
    and r.status = false and s.floor_id = 1
    and r.start_date = '2021-06-01'
    and r.start_time = 8;
     */

//    @Query("SELECT s FROM Reserve r, Space s " + // hacer los joins
//            "WHERE r.deleted = false and r.space.id= s.id " +
//            "and r.status = true and r.space.floor.name = :name " +
//            "and r.startDate >= :fechaInicio and r.endDate <= :fechaFin " +
//            "and r.startTime >= :horaInicio and r.endTime <= :horaFin ")
//    public List<Space> listaEspaciosNoDisponiblesPorPisoYFecha(@PathVariable String name, @PathVariable Date fechaInicio,
//                                                             @PathVariable Date fechaFin, @PathVariable int horaInicio,
//                                                             @PathVariable int horaFin);

    // quiero revisar todas las reservas de un usuario, como un historial
    @Query("SELECT r FROM Reserve r WHERE r.deleted = false and r.customer.person.dni = :dni")
    public List<Reserve> listaReservasPorUsuario(@PathVariable Long dni);

    // obtener una reserva por id
    @Query("SELECT r FROM Reserve r WHERE r.deleted = false and r.id = :id")
    public Reserve obtenerReservaPorId(@PathVariable Long id);
}
