package com.example.reservas.service.inter;

import com.example.reservas.dto.CustomerDtoSpecial;
import com.example.reservas.entity.Customer;
import com.example.reservas.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient("users")
public interface CustomerService {

    @PostMapping("/api/customer/save")
    public ResponseEntity<Customer> save(@RequestHeader("Authorization") String token,@RequestBody CustomerDtoSpecial customerDto);

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@RequestHeader("Authorization") String token ,@PathVariable Long id);

    @GetMapping("/api/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
