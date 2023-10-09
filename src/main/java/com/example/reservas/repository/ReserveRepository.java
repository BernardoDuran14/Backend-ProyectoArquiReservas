package com.example.reservas.repository;

import com.example.reservas.entity.Reserve;
import com.example.reservas.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("SELECT r FROM Reserve r WHERE r.deleted = false and r.status = false and r.space.floor.id = :id")
    public List<Space> listaEspaciosDisponiblesPorPiso(@PathVariable Long id);

    @Query("SELECT r FROM Reserve r WHERE r.deleted = false and r.status = false")
    public List<Space> listaEspaciosDisponibles();
}
