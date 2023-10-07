package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_person")
public class Person extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_person_id")
    private Long id;

    @Column(name = "p_person_name")
    private String name;

    @Column(name = "p_person_surname")
    private String surname;

    @Column(name = "p_person_email")
    private String email;

    @Column(name = "p_person_phone")
    private String phone;

    @Column(name = "p_person_dni")
    private String dni;

    @ManyToOne
    @JoinColumn(name = "p_person_user_id", referencedColumnName = "p_user_id")
    private User user;
}
