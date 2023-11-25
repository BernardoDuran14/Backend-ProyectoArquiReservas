package com.example.reservas.service.impl;

import com.example.reservas.dto.*;
import com.example.reservas.entity.Customer;
import com.example.reservas.entity.Person;
import com.example.reservas.entity.Vehicles;
import com.example.reservas.repository.VehiclesRepository;
import com.example.reservas.service.inter.CustomerService;
import com.example.reservas.service.inter.KeycloakService;
import com.example.reservas.service.inter.VehiclesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehiclesImpl implements VehiclesService {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KeycloakService keycloakService;

    @Value("${token.resource-id}")
    private String keycloakClient;

    @Value("${token.private-key}")
    private String keycloakPrivateKey;

    @Override
    public List<VehiclesDto> getAllVehicles() {
        List<Vehicles> vehicles = vehiclesRepository.findAll();
        // utilizando stream para convertir la lista de objetos a lista de dto, utilizando mapToDto
        return vehicles.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public VehiclesDto mapToDto(Vehicles vehicles) {
        VehiclesDto vehiclesDto = new VehiclesDto();
        BeanUtils.copyProperties(vehicles, vehiclesDto);
        vehiclesDto.setCustomer(vehicles.getCustomer().getId());
        return vehiclesDto;
    }

    @Override
    public Vehicles getVehicleById(Long id) {
        Vehicles vehicles = vehiclesRepository.findById(id).get();
        return vehicles;
    }

    @Override
    public List<Vehicles> getVehiclesByCustomer(Long id) {
        List<Vehicles> vehicles = vehiclesRepository.findAllByCustomer(id);
        return vehicles;
    }

    @Override
    public void saveVehicles(VehiclesDto vehiclesDto) {
        Vehicles vehicles = new Vehicles();
        BeanUtils.copyProperties(vehiclesDto, vehicles);
        Map<String, String> data = Map.of(
                "grant_type", "client_credentials",
                "client_id", keycloakClient,
                "client_secret", keycloakPrivateKey);
        String token = "Bearer " + keycloakService.getToken(data).get("access_token");
        ResponseEntity<Customer> customer = customerService.getCustomerById(token,vehiclesDto.getCustomer());
        vehicles.setCustomer(customer.getBody());
        vehiclesRepository.save(vehicles);
    }

    public void saveVehiclesAndCustomer(VehiclesDtoSpecial vehicleDto) {
        Vehicles vehicle1 = new Vehicles();
        CustomerDtoSpecial customerDto = new CustomerDtoSpecial();
        PersonDto person1 = new PersonDto();
        // guardar el person que se encuentra en el dto
        person1.setName(vehicleDto.getCustomer().getPerson().getName());
        person1.setSurname(vehicleDto.getCustomer().getPerson().getSurname());
        person1.setDni(vehicleDto.getCustomer().getPerson().getDni());
        person1.setEmail(vehicleDto.getCustomer().getPerson().getEmail());
        person1.setPhone(vehicleDto.getCustomer().getPerson().getPhone());
        person1.setUser(vehicleDto.getCustomer().getPerson().getUser());
        customerDto.setPerson(person1);
        log.info("Llave keycloak: " + keycloakPrivateKey);

        Map<String, String> data = Map.of(
                "grant_type", "client_credentials",
                "client_id", keycloakClient,
                "client_secret", keycloakPrivateKey);
        String token = "Bearer " + keycloakService.getToken(data).get("access_token");

        ResponseEntity<Customer> customerResponseEntity = customerService.save(token, customerDto);

        // ahora el vehiculo
        vehicle1.setColor(vehicleDto.getColor());
        vehicle1.setBrand(vehicleDto.getBrand());
        vehicle1.setLicensePlate(vehicleDto.getLicensePlate());
        vehicle1.setEtag(vehicleDto.getEtag());
        vehicle1.setCustomer(customerResponseEntity.getBody());
        vehiclesRepository.save(vehicle1);
    }



    @Override
    public void updateVehicles(VehiclesDto vehiclesDto) {

    }

    @Override
    public void deleteVehicles(Long id) {

    }
}
