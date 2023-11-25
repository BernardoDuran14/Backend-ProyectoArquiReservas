package com.example.reservas.service.impl;

import com.example.reservas.controller.SpaceController;
import com.example.reservas.dto.InitReservaDto;
import com.example.reservas.dto.ReserveDto;
import com.example.reservas.dto.SpaceDto;
import com.example.reservas.entity.*;
import com.example.reservas.repository.*;
import com.example.reservas.service.inter.CustomerService;
import com.example.reservas.service.inter.KeycloakService;
import com.example.reservas.service.inter.ReserveService;
import com.example.reservas.service.inter.SpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Space> getAllReservesAvailables(InitReservaDto initReservaDto) {
        List<Space> reserves = reserveRepository.listaEspaciosDisponiblesPorPisoYFecha
                (initReservaDto.getName(),
                initReservaDto.getFechaInicio(), initReservaDto.getFechaFin(),
                initReservaDto.getHoraInicio(), initReservaDto.getHoraFin());

        return reserves;
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
    public ReserveDto getReserveById(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        return mapToDto(reserve);
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

        BeanUtils.copyProperties(reserveDto, reserve);

        reserve.setEmployee(employee);
        reserve.setCustomer(customer);
        reserve.setVehicles(vehicle);
        reserve.setSpace(space);

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
