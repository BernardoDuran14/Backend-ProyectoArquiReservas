package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_reserve")
public class Reserve extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_reserve_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "p_reserve_customer_id", referencedColumnName = "p_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "p_reserve_space_id", referencedColumnName = "p_space_id")
    private Space space;

    @ManyToOne
    @JoinColumn(name = "p_reserve_employee_id", referencedColumnName = "p_employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "p_reserve_vehicles_id", referencedColumnName = "p_vehicles_id")
    private Vehicles vehicles;

    @Column(name = "p_reserve_def")
    private Boolean def;

    @Column(name = "p_reserve_start_date")
    private String startDate;

    @Column(name = "p_reserve_end_date")
    private String endDate;

    @Column(name = "p_reserve_start_time")
    private Date startTime;

    @Column(name = "p_reserve_end_time")
    private Date endTime;
}
