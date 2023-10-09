package com.example.reservas.service.impl;

import com.example.reservas.controller.SpaceController;
import com.example.reservas.dto.ReserveDto;
import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.Reserve;
import com.example.reservas.entity.Space;
import com.example.reservas.repository.CustumerRepository;
import com.example.reservas.repository.EmployeeRepository;
import com.example.reservas.repository.ReserveRepository;
import com.example.reservas.service.inter.ReserveService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReserveImpl implements ReserveService {

    @Autowired
    private CustumerRepository custumerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @Override
    public List<SpaceDto> getAllReservesAvailables() {
        List<Space> reserves = reserveRepository.listaEspaciosDisponibles();
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return reserves.stream().map(this::mapToSpace).collect(Collectors.toList());
    }

    public ReserveDto mapToDto(Reserve reserve) {
        ReserveDto reserveDto = new ReserveDto();
        BeanUtils.copyProperties(reserve, reserveDto);
        return reserveDto;
    }

    public SpaceDto mapToSpace(Space space) {
        SpaceDto spaceDto = new SpaceDto();
        BeanUtils.copyProperties(space, spaceDto);
        return spaceDto;
    }

    @Override
    public List<SpaceDto> getAllReservesByFloor(Long id) {
        List<Space> reserves = reserveRepository.listaEspaciosDisponiblesPorPiso(id);
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return reserves.stream().map(this::mapToSpace).collect(Collectors.toList());
    }

    @Override
    public ReserveDto getReserveById(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        return mapToDto(reserve);
    }

    @Override
    public void saveReserve(ReserveDto reserveDto) {
        Reserve reserve = new Reserve();
        BeanUtils.copyProperties(reserveDto, reserve);
        reserve.setCustomer(custumerRepository.findById(reserveDto.getCustomer()).get());
        reserve.setEmployee(employeeRepository.findById(reserveDto.getEmployee()).get());
        reserveRepository.save(reserve);
    }

    @Override
    public void updateReserve(ReserveDto reserveDto) {

    }

    @Override
    public void deleteReserve(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        reserve.setDeleted(true);
        reserveRepository.save(reserve);
    }
}
