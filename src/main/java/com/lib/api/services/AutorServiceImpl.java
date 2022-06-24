package com.lib.api.services;

import com.lib.api.entities.Autor;
import com.lib.api.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    @Transactional
    public List<Autor> findAll() throws Exception {
        try{
            List<Autor> entities = autorRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor findById(Long id) throws Exception {
        try{
            Optional<Autor> entityOptional = autorRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<Autor> findByNombre(String nombre) throws Exception {
        try{
            Optional<Autor> optionalEntitie = autorRepository.findByNombre(nombre);
            return optionalEntitie;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor save(Autor entity) throws Exception {
        try{
            if(!autorRepository.findByNombre(entity.getNombre()).isEmpty()) {
                Optional<Autor> optionalEntity = autorRepository.findByNombre(entity.getNombre());
                Autor autorAux = optionalEntity.get();
                return autorAux;
            }
            else{
                entity = autorRepository.save(entity);
                return entity;
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor update(Long id, Autor entity) throws Exception {
        try{
            Optional<Autor> entityOptional = autorRepository.findById(id);
            Autor autor = entityOptional.get();
            autor = autorRepository.save(entity);
            return autor;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(autorRepository.existsById(id)){
                autorRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
