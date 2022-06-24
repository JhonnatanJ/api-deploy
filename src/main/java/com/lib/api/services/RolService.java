package com.lib.api.services;


import com.lib.api.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    public List<Rol> findAll() throws Exception;
    public Rol findById(Long id) throws Exception;
    public Optional<Rol> findByNombre(String nombre) throws Exception;
    public Rol save(Rol entity) throws Exception;
    public Rol update(Long id, Rol entity) throws Exception;
    public boolean delete(Long id) throws Exception;
}
