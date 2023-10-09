package com.example.reservas.service.impl;

import com.example.reservas.dto.FloorDto;
import com.example.reservas.entity.Floor;
import com.example.reservas.repository.FloorRepository;
import com.example.reservas.service.inter.FloorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorImpl implements FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Override
    public List<FloorDto> getAllFloors() {
        List<Floor> floors = floorRepository.findAllByDeletedFalse();
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return floors.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public FloorDto mapToDto(Floor floor) {
        FloorDto floorDto = new FloorDto();
        BeanUtils.copyProperties(floor, floorDto);
        return floorDto;
    }

    @Override
    public FloorDto getFloorById(Long id) {
        Floor floor = floorRepository.findById(id).get();
        return mapToDto(floor);
    }

    @Override
    public void saveFloor(FloorDto floorDto) {
        Floor floor = new Floor();
        BeanUtils.copyProperties(floorDto, floor);
        floorRepository.save(floor);
    }

    @Override
    public void updateFloor(FloorDto floorDto) {
        Floor floor = floorRepository.findById(floorDto.getId()).get();
        BeanUtils.copyProperties(floorDto, floor);
        floorRepository.save(floor);
    }

    @Override
    public void deleteFloor(Long id) {
        Floor floor = floorRepository.findById(id).get();
        floor.setDeleted(true);
        floorRepository.save(floor);

    }
}
