package com.example.reservas.service.inter;

import java.util.List;

import com.example.reservas.dto.EspaciosDisponiblesDto;
import com.example.reservas.dto.InitReservaDto;
import com.example.reservas.dto.ReserveDto;
import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.Reserve;
import com.example.reservas.entity.Space;

public interface ReserveService {
    public List<EspaciosDisponiblesDto> getAllReservesAvailables(InitReservaDto initReservaDto);
    public List<Space> getAllReservesNotAvailables(InitReservaDto initReservaDto);
    public List<Reserve> getReservesByUser(Long dni);
    public Reserve getReserveById(Long id);
    public void saveReserve(ReserveDto reserveDto);
    public void deleteReserve(Long id);
    public String sendNotification(String message);
}
