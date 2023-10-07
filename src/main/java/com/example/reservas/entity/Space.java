package com.example.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_space")
public class Space extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_space_id")
    private Long id;

    @Column(name = "p_space_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "p_space_floor_id", referencedColumnName = "p_floor_id")
    private Floor floor;
}
