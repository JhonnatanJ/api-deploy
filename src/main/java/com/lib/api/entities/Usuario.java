package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "ci", nullable = false, unique = true, length = 10)
    private String CI;

    @Column(name = "nombres", nullable = false, length = 60)
    private String Nombres;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String Apellidos;

    @Column(name = "telefono", length = 10)
    private String Telefono;

}
