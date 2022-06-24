package com.lib.api.repositories;

import com.api.lib.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query(
            value = "SELECT c from Cuenta c WHERE  c.Email LIKE :email"
    )
    Optional<Cuenta> findByEmail(@Param("email") String email);
}
