package com.lib.api.services;

import com.lib.api.entities.NotaVenta;

import java.util.List;

public interface NotaVentaService {

    public List<NotaVenta> findAll() throws Exception;
    public NotaVenta findById(Long id) throws Exception;
    public List<NotaVenta> findByDate(String fecha) throws Exception;
    public NotaVenta save(NotaVenta entity) throws Exception;
    public NotaVenta update(Long id, NotaVenta entity) throws Exception;
    public boolean delete(Long id) throws Exception;
}
