package com.example.reservas.service.inter;

import com.example.reservas.dto.CustomerDtoSpecial;
import com.example.reservas.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("users")
public interface CustomerService {

    @PostMapping("/api/customer/save")
    public ResponseEntity<Customer> save(@RequestBody CustomerDtoSpecial customerDto);

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id);
}
