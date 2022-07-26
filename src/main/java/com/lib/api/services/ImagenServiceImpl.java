package com.lib.api.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lib.api.entities.Imagen;
import com.lib.api.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ImagenServiceImpl {

    Cloudinary cloudinary;
    private Map<String, String> valuesMap = new HashMap<>();
    public ImagenServiceImpl(){
        valuesMap.put("cloud_name", "drt1ea5my");
        valuesMap.put("api_key", "318594348978517");
        valuesMap.put("api_secret", "MX2eqiEhxtJYpJLSW5ZAZVGVUAk");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Autowired
    ImagenRepository imagenRepository;

    public List<Imagen> list() {
        return imagenRepository.findByOrderById();
    }

    public Optional<Imagen> getOne(Long id){
        return imagenRepository.findById(id);
    }

    public Map save(MultipartFile multipartFile) throws IOException {
        try {
            File file = convert(multipartFile);
            Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            file.delete();

            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                throw new IOException();
            }
            Imagen imagen = new Imagen((Long) result.get("id"), (String) result.get("original_filename"),
                    (String) result.get("url"),
                    (String) result.get("public_id"));
            imagenRepository.save(imagen);
            return result;
        }
        catch (IOException e){
            throw  new IOException(e.getMessage());
        }
    }


    public boolean delete(Long id) throws IOException {
        try {
            if (exists(id)) {
                Imagen imagen = imagenRepository.findById(id).get();
                cloudinary.uploader().destroy(imagen.getImagenId(), ObjectUtils.emptyMap());
                imagenRepository.deleteById(id);
                return true;
            }
            else{
                throw new IOException();
            }
        }
        catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public boolean exists(Long id){
        return imagenRepository.existsById(id);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
