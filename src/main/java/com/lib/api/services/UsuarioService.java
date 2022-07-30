package com.lib.api.controllers.services;

import com.lib.api.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario> findAll() throws Exception;
    public Usuario findById(String id) throws Exception;
    public Optional<Usuario> findByNombreApellido(String email) throws Exception;
    public Usuario save(Usuario entity) throws Exception;
    public Usuario update(String id, Usuario entity) throws Exception;

    public boolean delete(String id) throws Exception;
}
