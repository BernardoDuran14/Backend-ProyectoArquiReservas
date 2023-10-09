package com.example.reservas.service.impl;

import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.Floor;
import com.example.reservas.entity.Space;
import com.example.reservas.repository.FloorRepository;
import com.example.reservas.repository.SpaceRepository;
import com.example.reservas.service.inter.SpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceImpl implements SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Override
    public List<SpaceDto> getAllSpaces() {
        List<Space> spaces = spaceRepository.findAllByNotDeleted();
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return spaces.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<SpaceDto> getAllSpacesByFloor(Long id) {
        List<Space> spaces = spaceRepository.findAllByFloorId(id);
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return spaces.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public SpaceDto mapToDto(Space space) {
        SpaceDto spaceDto = new SpaceDto();
        BeanUtils.copyProperties(space, spaceDto);
        spaceDto.setFloor(space.getFloor().getId());
        return spaceDto;
    }

    @Override
    public SpaceDto getSpaceById(Long id) {
        Space space = spaceRepository.findById(id).get();
        return mapToDto(space);
    }

    @Override
    public void saveSpace(SpaceDto spaceDto) {
        Space space = new Space();
        Floor floor = floorRepository.findById(spaceDto.getFloor()).get();
        BeanUtils.copyProperties(spaceDto, space);
        space.setFloor(floor);
        spaceRepository.save(space);
    }

    @Override
    public void updateSpace(SpaceDto spaceDto) {
        Space space = spaceRepository.findById(spaceDto.getId()).get();
        Floor floor = floorRepository.findById(spaceDto.getFloor()).get();
        BeanUtils.copyProperties(spaceDto, space);
        space.setFloor(floor);
        spaceRepository.save(space);
    }

    @Override
    public void deleteSpace(Long id) {
        Space space = spaceRepository.findById(id).get();
        space.setDeleted(true);
        spaceRepository.save(space);
    }
}
