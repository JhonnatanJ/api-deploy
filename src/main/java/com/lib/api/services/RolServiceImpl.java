package com.lib.api.controllers.services;

import com.lib.api.entities.Rol;
import com.lib.api.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional
    public List<Rol> findAll() throws Exception {
        try{
            List<Rol> entities = rolRepository.findAll();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rol findById(Long id) throws Exception {
        try{
            Optional<Rol> optionalEntitie = rolRepository.findById(id);
            return optionalEntitie.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<Rol> findByNombre(String nombre) throws Exception {
        try{
            Optional<Rol> optionalEntitie = rolRepository.findByNombre(nombre);
            return optionalEntitie;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rol save(Rol entity) throws Exception {
        try{
            if(rolRepository.findByNombre(entity.getNombre()).isPresent()) {
                entity = rolRepository.findByNombre(entity.getNombre()).get();
                entity = rolRepository.save(entity);
                return entity;
            }else{
                entity = rolRepository.save(entity);
                return entity;
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rol update(Long id, Rol entity) throws Exception {
        try {
            if (rolRepository.existsById(id)) {
                rolRepository.save(entity);
                return entity;
            } else {
                return null;
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(rolRepository.existsById(id)){
                rolRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
