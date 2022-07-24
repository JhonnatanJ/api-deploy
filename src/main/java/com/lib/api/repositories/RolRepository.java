package com.lib.api.repositories;

import com.lib.api.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query(
            value = "SELECT r from Rol r WHERE r.nombre LIKE :nombre"
    )
    Optional<Rol> findByNombre(@Param("nombre") String nombre);
}
