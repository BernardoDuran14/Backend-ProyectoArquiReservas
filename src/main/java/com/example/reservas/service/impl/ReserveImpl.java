package com.example.reservas.service.impl;

import com.example.reservas.dto.*;
import com.example.reservas.entity.*;
import com.example.reservas.repository.*;
import com.example.reservas.service.inter.CustomerService;
import com.example.reservas.service.inter.KeycloakService;
import com.example.reservas.service.inter.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReserveImpl implements ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Value("${token.resource-id}")
    private String keycloakClient;

    @Value("${token.private-key}")
    private String keycloakPrivateKey;

    @Override
    public List<EspaciosDisponiblesDto> getAllReservesAvailables(InitReservaDto initReservaDto) {
         Date fechaInicio = new Date();
         Date fechaFin = new Date();
         // utilizar la clase DateUtil para convertir la fecha, de String a Date
         fechaInicio = DateUtil.toDate(DateUtil.FORMAT_DATE, initReservaDto.getFechaInicio());
         fechaFin = DateUtil.toDate(DateUtil.FORMAT_DATE, initReservaDto.getFechaFin());
         log.info("fechaInicio: " + fechaInicio);
         log.info("fechaFin: " + fechaFin);
         int horaInicio = Integer.parseInt(initReservaDto.getHoraInicio());
         int horaFin = Integer.parseInt(initReservaDto.getHoraFin());
            // obtener la lista de espacios disponibles por piso y fecha
         List<Space> spaces = reserveRepository.listaEspaciosNoDisponiblesPorPisoYFecha
                (initReservaDto.getName(), fechaInicio, horaInicio);
         // mapear la lista de espacios disponibles a una lista de dto
         List<EspaciosDisponiblesDto> espaciosDisponiblesLista = mapearEspaciosDisponibles(spaces, initReservaDto.getName());
         return espaciosDisponiblesLista;
    }

    public List<EspaciosDisponiblesDto> mapearEspaciosDisponibles(List<Space> spaces, String nameFloor) {
        List<EspaciosDisponiblesDto> espaciosDisponiblesLista = null;
        List<Space> spacesAll = spaceRepository.findAllByFloorNameAndDeletedFalse(nameFloor);

        // comparar con un for si el espacio de la lista de todos los espacios esta en la lista de espacios totales
        // si es asi poner disponible = true, sino poner disponible = false
        for(Space space : spacesAll) {
            EspaciosDisponiblesDto espacioDisponibleDto = new EspaciosDisponiblesDto();
            espacioDisponibleDto.setNombreEspacio(space.getName());
            espacioDisponibleDto.setDisponible(false);
            for(Space space1 : spaces) {
                if(space.getId() == space1.getId()) {
                    espacioDisponibleDto.setDisponible(true);
                }else{
                    espacioDisponibleDto.setDisponible(false);
                }

            }
            espaciosDisponiblesLista.add(espacioDisponibleDto);
        }
        return espaciosDisponiblesLista;
    }

    @Override
    public List<Space> getAllReservesNotAvailables(InitReservaDto initReservaDto) {
        return null;
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
    public List<Reserve> getReservesByUser(Long dni) {
        List<Reserve> reserves = reserveRepository.listaReservasPorUsuario(dni);
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return reserves;
    }

    @Override
    public Reserve getReserveById(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        return reserve;
    }

    @Override
    public void saveReserve(ReserveDto reserveDto) {
        Reserve reserve = new Reserve();
        Vehicles vehicle = vehiclesRepository.findByPlate(reserveDto.getVehiclePlate());
        Customer customer = new Customer();
        Employee employee = new Employee();
        Space space = new Space();
        Map<String, String> data = Map.of(
                "grant_type", "client_credentials",
                "client_id", keycloakClient,
                "client_secret", keycloakPrivateKey);
        String token = "Bearer " + keycloakService.getToken(data).get("access_token");

        if(reserveDto.getEmployeeId() != null) {
            // utilizar el microservicio de EmployeeService
            employee = customerService.getEmployeeById(token, reserveDto.getEmployeeId()).getBody();
            // la entrada al parqueo se hizo por un empleado
            reserve.setDef("Normal");
        }else {
            employee = customerService.getEmployeeById(token,1L).getBody();
            // la entrada al parqueo se hizo por un cliente
            reserve.setDef("Reserva");
        }
        if(reserveDto.getCustomerId() != null) {
            // utilizar el microservicio de CustomerService
            customer = customerService.getCustomerById(token,reserveDto.getCustomerId()).getBody();
        }
        space = spaceRepository.findWithId(reserveDto.getSpaceId());

        Date fechaInicio = new Date();
        // utilizar la clase DateUtil para convertir la fecha, de String a Date
        fechaInicio = DateUtil.toDate(DateUtil.FORMAT_DATE, reserveDto.getStartDate());

        BeanUtils.copyProperties(reserveDto, reserve);
        reserve.setStartDate(fechaInicio);

        reserve.setEmployee(employee);
        reserve.setCustomer(customer);
        reserve.setVehicles(vehicle);
        reserve.setSpace(space);
        reserve.setStartTime(Integer.parseInt(reserveDto.getStartTime()));

        reserve.setStatus(false); // false = no pagado, true = pagado
        reserve.setDeleted(false);
        reserveRepository.save(reserve);
    }

    @Override
    public void deleteReserve(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        reserve.setDeleted(true);
        reserveRepository.save(reserve);
    }
}
