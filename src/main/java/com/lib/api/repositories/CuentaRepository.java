package com.lib.api.repositories;

import com.lib.api.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    public Cuenta findByEmail(String email);
}
