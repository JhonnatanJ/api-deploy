package com.lib.api.services;

import com.lib.api.entities.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ImagenService {

    public List<Imagen> list();
    public Optional<Imagen> getOne(Long id);
    public Map save(MultipartFile multipartFile) throws IOException;
    public boolean delete(Long id) throws IOException;
    public boolean exists(Long id);
}
