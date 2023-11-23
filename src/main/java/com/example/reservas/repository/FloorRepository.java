package com.example.reservas.repository;

import com.example.reservas.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("SELECT f FROM Floor f WHERE f.deleted = false or f.deleted is null")
    public List<Floor> findAllByDeletedFalse();
}
