package com.lib.api.services;


import com.lib.api.entities.Editorial;

import java.util.List;

public interface EditorialService {

    public List<Editorial> findAll() throws Exception;
    public Editorial findById(Long id) throws Exception;
    public Editorial findByNombre(String nombre) throws Exception;
}
