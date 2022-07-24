package com.lib.api.services;

import com.lib.api.entities.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public List<Libro> getAllByDateDESC() throws Exception;

    public List<Libro> getAllByDateASC() throws Exception;


    //------------------------------------------------------ PAGED

    public Page<Libro> findAll(Pageable pageable) throws Exception;
    public Page<Libro> findByGenero(String genero, Pageable pageable) throws Exception;

}
