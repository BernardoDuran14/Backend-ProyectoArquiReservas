package com.example.reservas.repository;

import com.example.reservas.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {

    public Vehicles findByLicensePlate(String placa);

    @Query("SELECT v FROM Vehicles v WHERE v.deleted = false and v.licensePlate = :vehiclePlate")
    Vehicles findByPlate(String vehiclePlate);

    // encontrar todos los autos de un cliente segun su id
    @Query("SELECT v FROM Vehicles v WHERE v.deleted = false and v.customer.id = :id")
    public List<Vehicles> findAllByCustomer(Long id);
}
