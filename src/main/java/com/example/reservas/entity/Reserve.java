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
@Table(name = "p_reserve")
public class Reserve implements Serializable {

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
    private boolean def;

    @Column(name = "p_reserve_status")
    private Boolean status;

    @Column(name = "p_reserve_deleted")
    private Boolean deleted;

    @Column(name = "p_reserve_start_date")
    private Date startDate;

    @Column(name = "p_reserve_end_date")
    private Date endDate;

    @Column(name = "p_reserve_start_time")
    private int startTime;

    @Column(name = "p_reserve_end_time")
    private int endTime;
}
