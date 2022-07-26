package com.lib.api.repositories;

import com.lib.api.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository  extends JpaRepository<Imagen, Long> {
    List<Imagen> findByOrderById();
}
