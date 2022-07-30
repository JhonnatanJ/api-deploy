package com.lib.api.controllers.services;


import com.lib.api.entities.NotaVenta;
import com.lib.api.entities.Reserva;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {
    public List<Reserva> findAll() throws Exception;
    public Reserva findById(Long id) throws Exception;
    public Reserva save(Reserva entity) throws Exception;
    public Reserva update(Long id, Reserva entity) throws Exception;
    public boolean delete(Long id) throws Exception;

    //====================================================================== CONSULTAS
    public List<Reserva> findByDate(String fecha) throws Exception;
    public List<Reserva> findByDateAbono(String fecha) throws Exception;
    public List<Reserva> findByDateCompleto(String fecha) throws Exception;

}
