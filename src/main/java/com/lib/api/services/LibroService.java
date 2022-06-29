package com.lib.api.services;

import com.lib.api.entities.Libro;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroService {
    public List<Libro> findAll() throws Exception;
    public Libro findById(String id) throws Exception;
    public Libro save(Libro entity) throws Exception;
    public Libro update(String id, Libro entity) throws Exception;
    public boolean delete(String id) throws Exception;


    public List<Libro> findByNombre(String nombre) throws Exception;
    public List<Libro> findByAutor(String autor) throws Exception;
    public List<Libro> findByGenero(String genero) throws Exception;
    public List<Libro> findByEditorial(String editorial) throws Exception;
}
