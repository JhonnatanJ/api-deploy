package com.lib.api.controllers.services;

import com.lib.api.entities.Genero;
import com.lib.api.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public List<Genero> findAll() throws Exception {
        try{
            List<Genero> entities = generoRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Genero findById(Long id) throws Exception {
        try{
            Optional<Genero> entityOptional = generoRepository.findById(id);
            return entityOptional.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Genero findByNombre(String nombre) throws Exception {
        try{
            Optional<Genero> entityOptional = generoRepository.findByNombre(nombre);
            return entityOptional.get();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
