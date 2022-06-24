package com.lib.api.services;


import com.lib.api.entities.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    public List<Autor> findAll() throws Exception;
    public Autor findById(Long id) throws Exception;
    public Optional<Autor> findByNombre(String nombre) throws Exception;
    public Autor save(Autor entity) throws Exception;
    public Autor update(Long id, Autor entity) throws Exception;

    public boolean delete(Long id) throws Exception;
}
