package com.lib.api.services;

import com.lib.api.entities.Imagen;
import com.lib.api.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl {

    @Autowired
    ImagenRepository imagenRepository;

    public List<Imagen> list() {
        return imagenRepository.findByOrderById();
    }

    public Optional<Imagen> getOne(Long id){
        return imagenRepository.findById(id);
    }

    public void save(Imagen imagen){
        imagenRepository. save(imagen);
    }

    public void delete(Long id) {
        imagenRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return imagenRepository.existsById(id);
    }

}
