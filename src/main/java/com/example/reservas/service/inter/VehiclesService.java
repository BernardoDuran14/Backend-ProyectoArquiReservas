package com.example.reservas.service.inter;

import com.example.reservas.dto.VehiclesDto;
import com.example.reservas.entity.Vehicles;

import java.util.List;

public interface VehiclesService {

    public List<VehiclesDto> getAllVehicles();

    public Vehicles getVehiclesById(Long id);

    public void saveVehicles(VehiclesDto vehiclesDto);

    public void updateVehicles(VehiclesDto vehiclesDto);

    public void deleteVehicles(Long id);
}
