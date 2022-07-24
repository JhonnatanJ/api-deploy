package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "autor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor implements Serializable {

    @Id
    @Column(name = "id_autor", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}
