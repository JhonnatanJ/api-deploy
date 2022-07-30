package com.lib.api.controllers.services;


import com.lib.api.entities.Genero;

import java.util.List;
public interface GeneroService {
    public List<Genero> findAll() throws Exception;
    public Genero findById(Long id) throws Exception;
    public Genero findByNombre(String nombre) throws Exception;
}
