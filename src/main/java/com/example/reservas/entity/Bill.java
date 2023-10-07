package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_bill")
public class Bill extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_bill_id")
    private Long id;

    @Column(name = "p_bill_total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "p_bill_customer_id", referencedColumnName = "p_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "p_bill_employee_id", referencedColumnName = "p_employee_id")
    private Employee employee;
}
