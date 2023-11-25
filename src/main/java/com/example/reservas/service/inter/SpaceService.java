package com.example.reservas.service.inter;

import com.example.reservas.dto.SpaceDto;

import java.util.List;

public interface SpaceService {

    public List<SpaceDto> getAllSpaces();
    public List<SpaceDto> getAllSpacesByFloor(Long id);
    public SpaceDto getSpaceById(Long id);

    public void saveSpace(SpaceDto spaceDto);

    public void updateSpace(SpaceDto spaceDto);

    public void deleteSpace(Long id);

    void saveSpaceByFloor(SpaceDto spaceDto, Long id);
}
