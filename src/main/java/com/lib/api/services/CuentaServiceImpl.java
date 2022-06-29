package com.lib.api.services;

import com.lib.api.entities.Cuenta;
import com.lib.api.repositories.CuentaRepository;
import com.lib.api.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional
    public List<Cuenta> findAll() throws Exception {
        try{
            return cuentaRepository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cuenta findById(Long id) throws Exception {
        try{
            Optional<Cuenta> optionalEntitie = cuentaRepository.findById(id);
            return optionalEntitie.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Optional<Cuenta> findByEmail(String email) throws Exception {
        try{
            return cuentaRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Cuenta save(Cuenta entity) throws Exception {
        try{
            entity.setFechaCreacion(LocalDate.now());
            if(rolRepository.findByNombre(entity.getRol().getNombre()).isPresent()) {
                entity.setRol(rolRepository.findByNombre(entity.getRol().getNombre()).get());
                entity = cuentaRepository.save(entity);
                return entity;
            }
            else{
                throw new Exception();
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cuenta update(Long id, Cuenta entity) throws Exception {
        try {
            if (cuentaRepository.existsById(id)) {
                cuentaRepository.save(entity);
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
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(cuentaRepository.existsById(id)){
                cuentaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean login(String email, String contrasena) throws Exception {
        try{
            if(cuentaRepository.login(email, contrasena).isPresent()){
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
