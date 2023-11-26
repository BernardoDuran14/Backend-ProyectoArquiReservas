package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_bill_id")
    private Long id;

    @Column(name = "p_bill_nit")
    private String nit;

    @Column(name = "p_bill_name")
    private String name;

    @Column(name = "p_bill_date")
    private Date date;

    @Column(name = "p_bill_total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "p_bill_customer_id", referencedColumnName = "p_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "p_bill_employee_id", referencedColumnName = "p_employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "p_bill_reserve_id", referencedColumnName = "p_reserve_id")
    private Reserve reserve;
}
