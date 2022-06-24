package com.lib.api.services;

import com.lib.api.entities.Editorial;
import com.lib.api.repositories.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    @Transactional
    public List<Editorial> findAll() throws Exception {
        try{
            List<Editorial> entities = editorialRepository.findAll();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Editorial findById(Long id) throws Exception {
        try{
            Optional<Editorial> entityOptional = editorialRepository.findById(id);
            return entityOptional.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Editorial findByNombre(String nombre) throws Exception {
        try{
            Optional<Editorial> entityOptional = editorialRepository.findByNombre(nombre);
            return entityOptional.get();
        }
        catch (Exception e){
        throw new Exception(e.getMessage());
        }
    }
}
