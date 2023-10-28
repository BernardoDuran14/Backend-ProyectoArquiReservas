package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_admin")
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_admin_id")
    private Long id;

    @Column(name = "p_admin_deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "p_admin_person_id", referencedColumnName = "p_person_id")
    private Person person;
}
