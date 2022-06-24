package com.lib.api.repositories;

import com.lib.api.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    @Query(
            value = "SELECT g from Genero g WHERE  g.Nombre LIKE :nombre"
    )
    Optional<Genero> findByNombre(@Param("nombre") String nombre);
}
