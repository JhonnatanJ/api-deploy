package com.lib.api.repositories;

import com.lib.api.entities.Libro;
import com.lib.api.entities.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    @Query(
            value = "SELECT l FROM Libro l WHERE l.titulo LIKE %:nombre%"
    )
    List<Libro> findByNombre(@Param("nombre")  String nombre);

    @Query(
            value = "SELECT l FROM Libro l JOIN l.generos g WHERE g.nombre LIKE %:genero%"
    )
    List<Libro> findByGenero(@Param("genero") String genero);

    @Query(
            value = "SELECT l FROM Libro l JOIN l.autores a WHERE a.nombre LIKE %:autor%"
    )
    List<Libro> findByAutor(@Param("autor") String autor);

    @Query(
            value = "SELECT l FROM Libro l JOIN l.editoriales ed WHERE ed.nombre LIKE %:editorial%"
    )
    List<Libro> findByEditorial(@Param("editorial") String editorial);

    @Query(
            value = "SELECT l FROM Libro l WHERE l.stock > 0"
    )
    List<Libro> findByStockExist();

    @Query(
            value = "SELECT l FROM Libro l WHERE l.stock = 0"
    )
    List<Libro> findByStockEmpty();

    @Query(
            value = "SELECT l FROM Libro l ORDER BY FechaRegistro DESC"
    )
    List<Libro> getAllByDateDESC();

    @Query(
            value = "SELECT l FROM Libro l ORDER BY FechaRegistro ASC"
    )
    List<Libro> getAllByDateASC();

    @Query(
            value = "SELECT l FROM Libro l WHERE  l.fechaRegistro LIKE :fecha"
    )
    List<Libro> findByDateSave(@Param("fecha") LocalDate fecha);


    //-------------------------------------------------------------- PAGINADO

    @Query(
            value = "SELECT l FROM Libro l JOIN l.generos g WHERE g.nombre LIKE %:genero%"
    )
    Page<Libro> findByGenero(@Param("genero") String genero, Pageable pageable);
}
