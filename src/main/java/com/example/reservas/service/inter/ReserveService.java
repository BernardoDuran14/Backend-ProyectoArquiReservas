package com.example.reservas.service.inter;

import java.util.List;
import com.example.reservas.dto.ReserveDto;
import com.example.reservas.dto.SpaceDto;

public interface ReserveService {
    public List<SpaceDto> getAllReservesAvailables();
    public List<SpaceDto> getAllReservesByFloor(Long id);
    public ReserveDto getReserveById(Long id);
    public void saveReserve(ReserveDto reserveDto);
    public void updateReserve(ReserveDto reserveDto);
    public void deleteReserve(Long id);
}
