package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro implements Serializable {
    @Id
    @Column(name = "ISBN", nullable = false, length = 13)
    private String ISBN;

    @Column(name = "titulo", nullable = false, length = 600)
    private String titulo;

    @Column(name = "descripcion",length = 5000)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "precio_unitario", nullable = false, scale = 2)
    private Double precioUnitario;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    private List<Autor> autores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libros_generos",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private List<Genero> generos;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libros_editoriales",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id_editorial")
    )
    private List<Editorial> editoriales;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "libros_cuenta",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id_cuenta", updatable = false)
    )
    private Cuenta cuenta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Imagen imagen;

//-------------------------------------- MÉTODOS ---------------------
    //MÉTODO AGREGAR AUTORES A LA ENTIDAD
    public void AddAutores(List<Autor> newAutores){
        autores.clear();
        for (Autor autor: newAutores) {
            autores.add(autor);
        }
    }

    //MÉTODO AGREGAR GENEROS A LA ENTIDAD
    public void AddGeneros(List<Genero> newGeneros){
        generos.clear();
        for (Genero genero: newGeneros) {
            generos.add(genero);
        }
    }
    //MÉTODO AGREGAR EDITORIALES A LA ENTIDAD
    public void AddEditoriales(List<Editorial> newEditoriales){
        editoriales.clear();
        for (Editorial editorial: newEditoriales) {
            editoriales.add(editorial);
        }
    }
}
