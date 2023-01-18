package com.lib.api.services;

import com.lib.api.entities.Libro;
import com.lib.api.entities.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

    public List<Libro> findByStockExist()throws Exception;
    public List<Libro> findByStockEmpty() throws Exception;

    public List<Libro> findByDateSave(String fecha) throws Exception;
    public List<Libro> findByDateSave2(String fechaI, String fechaF) throws Exception;

    public List<Libro> getAllByDateDESC() throws Exception;

    public List<Libro> getAllByDateASC() throws Exception;


    //------------------------------------------------------ PAGED

    public Page<Libro> findAll(Pageable pageable) throws Exception;
    public Page<Libro> findByGenero(String genero, Pageable pageable) throws Exception;
    public Page<Libro> findByTitulo(String titulo, Pageable pageable) throws Exception;

}
