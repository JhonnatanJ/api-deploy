package com.lib.api.services;


import com.lib.api.entities.Reserva;

import java.util.List;

public interface ReservaService {
    public List<Reserva> findAll() throws Exception;
    public Reserva findById(Long id) throws Exception;
    public Reserva save(Reserva entity) throws Exception;
    public Reserva update(Long id, Reserva entity) throws Exception;
    public boolean delete(Long id) throws Exception;
}
