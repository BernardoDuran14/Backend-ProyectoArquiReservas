package com.example.reservas.repository;

import com.example.reservas.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustumerRepository extends JpaRepository<Customer, Long> {
}
