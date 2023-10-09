package com.example.reservas.repository;

import com.example.reservas.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    @Query("SELECT s FROM Space s WHERE s.deleted = false")
    public List<Space> findAllByNotDeleted();

    @Query("SELECT s FROM Space s WHERE s.floor.id = :id AND s.deleted = false")
    public List<Space> findAllByFloorId(@PathVariable Long id);
}
