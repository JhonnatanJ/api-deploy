package com.lib.api.repositories;

import com.lib.api.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {

    @Query(
            value = "SELECT e from Editorial e WHERE  e.Nombre LIKE :nombre"
    )
    Optional<Editorial> findByNombre(@Param("nombre") String nombre);

}
