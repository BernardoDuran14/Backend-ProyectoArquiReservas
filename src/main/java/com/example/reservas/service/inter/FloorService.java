package com.example.reservas.service.inter;

import com.example.reservas.dto.FloorDto;
import java.util.List;

public interface FloorService
{
    public List<FloorDto> getAllFloors();

    public FloorDto getFloorById(Long id);

    public void saveFloor(FloorDto floorDto);

    public void updateFloor(FloorDto floorDto);

    public void deleteFloor(Long id);
}
