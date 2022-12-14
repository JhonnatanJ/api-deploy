package com.lib.api.services;

import com.lib.api.entities.*;
import com.lib.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private ImagenServiceImpl imagenServiceImpl;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    @Transactional
    public List<Libro> findAll() throws Exception {
        try{
            List<Libro> entities = libroRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public Libro findById(String id) throws Exception {
        try{
            Optional<Libro> entityOptional = libroRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Libro save(Libro entity) throws Exception {

        try{
            if(entity.getStock() <=0){
                throw new Exception();
            }

            List<Autor> autoresVerif = new ArrayList<>();
            List<Autor> autoresEntity = entity.getAutores();
            for (Autor autor: autoresEntity) {
                autor.setIdAutor(null);
                if(!autorRepository.findByNombre(autor.getNombre()).isEmpty()){
                    autoresVerif.add(autorRepository.findByNombre(autor.getNombre()).get());
                }
                else{
                    autoresVerif.add(autor);
                }
            }
            List<Genero> generosVerif = new ArrayList<>();
            List<Genero> generosEntity = entity.getGeneros();
            for (Genero genero: generosEntity) {
                genero.setIdGenero(null);
                if(!generoRepository.findByNombre(genero.getNombre()).isEmpty()){
                    generosVerif.add(generoRepository.findByNombre(genero.getNombre()).get());
                }
                else{
                    generosVerif.add(genero);
                }
            }

            List<Editorial> editorialesVerif = new ArrayList<>();
            List<Editorial> editorialesEntity = entity.getEditoriales();
            for (Editorial editorial: editorialesEntity) {
                editorial.setIdEditorial(null);
                if(!editorialRepository.findByNombre(editorial.getNombre()).isEmpty()){
                    editorialesVerif.add(editorialRepository.findByNombre(editorial.getNombre()).get());
                }
                else{
                    editorialesVerif.add(editorial);
                }
            }

            if(!cuentaRepository.findById(entity.getCuenta().getIdCuenta()).isEmpty()){
                entity.setCuenta(cuentaRepository.findById(entity.getCuenta().getIdCuenta()).get());
            }
            else{
                throw new Exception();
            }
            if(imagenServiceImpl.findByNombre(entity.getISBN()).isPresent()) {
                Imagen imagen = imagenServiceImpl.findByNombre(entity.getISBN()).get();
                entity.setImagen(imagen);
            }
            else{
                System.out.println("ERROR BUSCANDO IMAGEN \n");
                throw new Exception();
            }

            entity.setFechaRegistro(LocalDate.now());
            entity.AddEditoriales(editorialesVerif);
            entity.AddAutores(autoresVerif);
            entity.AddGeneros(generosVerif);

            entity = libroRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Libro update(String id, Libro entity) throws Exception {
        try {
            if (!libroRepository.findById(id).isEmpty()) {
                save(entity);
                return  entity;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(String id) throws Exception {
        try{
            if(libroRepository.existsById(id)){
                imagenServiceImpl.delete(libroRepository.findById(id).get().getImagen().getId());
                libroRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    // -------------------------------------------- MÉTODOS ADICIONALES -------------------------------------------------------------------------------------
    @Override
    @Transactional
    public List<Libro> findByNombre(String nombre) throws Exception {
        try{
            List<Libro> entities = libroRepository.findByNombre(nombre);
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByAutor(String autor) throws Exception {
        try{
            List<Libro> entities = libroRepository.findByAutor(autor);
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByGenero(String genero) throws Exception {
        try{
            List<Libro> entities = libroRepository.findByGenero(genero);
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByEditorial(String editorial) throws Exception {
        try{
            List<Libro> entities = libroRepository.findByEditorial(editorial);
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByDateSave(String fecha) throws Exception {
        try{
            LocalDate localDate = LocalDate.parse(fecha);
            return libroRepository.findByDateSave(localDate);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByStockExist() throws Exception {
        try{
            List<Libro> entities = libroRepository.findByStockExist();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findByStockEmpty() throws Exception {
        try{
            List<Libro> entities = libroRepository.findByStockEmpty();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> getAllByDateDESC() throws Exception {
        try{
            List<Libro> entities = libroRepository.getAllByDateDESC();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> getAllByDateASC() throws Exception {
        try{
            List<Libro> entities = libroRepository.getAllByDateASC();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }



    //--------------------------------------------------------------- MÉTODOS PAGINADOS

    @Override
    @Transactional
    public Page<Libro> findAll(Pageable pageable) throws Exception{
        try{
            Page<Libro> entities = libroRepository.findAll(pageable);
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Libro> findByGenero(String genero, Pageable pageable) throws Exception{
        try{
            Page<Libro> entities = libroRepository.findByGenero(genero, pageable);
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Libro> findByTitulo(String titulo, Pageable pageable) throws Exception{
        try{
            Page<Libro> entities = libroRepository.findByTitulo(titulo, pageable);
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
