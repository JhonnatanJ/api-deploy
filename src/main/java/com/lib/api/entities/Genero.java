package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "genero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    @Id
    @Column(name = "id_genero", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGenero;

    @Column(name = "nombre", nullable = false, length = 50)
    private String Nombre;
}
