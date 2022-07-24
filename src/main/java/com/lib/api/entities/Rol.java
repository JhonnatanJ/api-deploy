package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @Column(name = "id_rol", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(name = "nombre", nullable = false, length = 20, unique = true)
    private String nombre;
}
