package com.lib.api.repositories;

import com.lib.api.entities.NotaVenta;
import com.lib.api.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query(
            value = "SELECT r from Reserva r WHERE  r.fechaCreacion LIKE :fecha"
    )
    List<Reserva> findByDate(@Param("fecha") LocalDate fecha);

    @Query(
            value = "SELECT r from Reserva r WHERE  r.fechaAbono LIKE :fecha"
    )
    List<Reserva> findByDateAbono(@Param("fecha") LocalDate fecha);

    @Query(
            value = "SELECT r from Reserva r WHERE  r.fechaAbono LIKE :fecha AND r.saldo = 0"
    )
    List<Reserva> findByDateCompleto(@Param("fecha") LocalDate fecha);


}
