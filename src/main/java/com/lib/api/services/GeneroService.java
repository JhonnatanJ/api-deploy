package com.lib.api.services;


import com.lib.api.entities.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    public List<Genero> findAll() throws Exception;
    public Genero findById(Long id) throws Exception;
    public Genero findByNombre(String nombre) throws Exception;
}
