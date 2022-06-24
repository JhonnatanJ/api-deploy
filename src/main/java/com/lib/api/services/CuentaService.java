package com.lib.api.services;

import com.lib.api.entities.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    public List<Cuenta> findAll() throws Exception;
    public Cuenta findById(Long id) throws Exception;
    public Optional<Cuenta> findByEmail(String email) throws Exception;
    public Cuenta save(Cuenta entity) throws Exception;
    public Cuenta update(Long id, Cuenta entity) throws Exception;
    public boolean delete(Long id) throws Exception;

}
