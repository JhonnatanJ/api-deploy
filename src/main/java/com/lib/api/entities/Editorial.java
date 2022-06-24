package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "editorial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {

    @Id
    @Column(name = "id_editorial", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdEditorial;

    @Column(name = "nombre", nullable = false, length = 60)
    private String Nombre;
}
