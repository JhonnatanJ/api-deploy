package com.lib.api.repositories;

import com.lib.api.entities.Autor;
import com.lib.api.entities.NotaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotaVentaRepository extends JpaRepository<NotaVenta,Long> {

    @Query(
            value = "SELECT nv from NotaVenta nv WHERE  nv.FechaRegistro LIKE :fecha"
    )
    List<NotaVenta> findByDate(@Param("fecha")LocalDate fecha);


}
