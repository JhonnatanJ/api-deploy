package com.lib.api.repositories;

import com.lib.api.entities.Imagen;
import com.lib.api.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImagenRepository  extends JpaRepository<Imagen, Long> {
    List<Imagen> findByOrderById();

    @Query(
            value = "SELECT i FROM Imagen i WHERE i.nombre LIKE :nombre"
    )
    Optional<Imagen> findByNombre(String nombre);
}
