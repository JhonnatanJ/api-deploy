package com.lib.api.services;

import com.lib.api.entities.Autor;
import com.lib.api.entities.Editorial;
import com.lib.api.entities.Genero;
import com.lib.api.entities.Libro;
import com.lib.api.repositories.AutorRepository;
import com.lib.api.repositories.EditorialRepository;
import com.lib.api.repositories.GeneroRepository;
import com.lib.api.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
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
                //libroRepository.quitarRelacionAutor(id);
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
    public Libro findByNombre(String nombre) throws Exception {
        try{
            Libro entity = libroRepository.findByNombre(nombre);
            return entity;
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

}
