package com.lib.api.repositories;

import com.lib.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query(
            value = "SELECT u from Usuario u WHERE  u.nombres LIKE %:nombre% OR u.apellidos LIKE %:nombre%"
    )
    Optional<Usuario> findByNombreApellido(@Param("nombre") String nombre);
}
