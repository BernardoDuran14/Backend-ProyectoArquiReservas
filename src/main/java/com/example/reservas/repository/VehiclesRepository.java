package com.example.reservas.repository;

import com.example.reservas.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {

    public Vehicles findByLicensePlate(String placa);

}
