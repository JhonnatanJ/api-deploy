package com.lib.api.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lib.api.entities.Imagen;
import com.lib.api.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ImagenServiceImpl implements ImagenService {

    @Autowired
    ImagenRepository imagenRepository;
    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();
    public ImagenServiceImpl(){
        valuesMap.put("cloud_name", "drt1ea5my");
        valuesMap.put("api_key", "318594348978517");
        valuesMap.put("api_secret", "MX2eqiEhxtJYpJLSW5ZAZVGVUAk");
        cloudinary = new Cloudinary(valuesMap);
    }

    public List<Imagen> list() {
        return imagenRepository.findByOrderById();
    }

    public Optional<Imagen> getOne(Long id){
        return imagenRepository.findById(id);
    }


    public Optional<Imagen> findByNombre(String isbn) {
        return imagenRepository.findByNombre(isbn);
    }

    public Imagen save(MultipartFile multipartFile, String isbn) throws IOException {
        try {
            if(!imagenRepository.findByNombre(isbn).isPresent()) {
                File file = convert(multipartFile);
                Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                file.delete();

                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    System.out.println("ERROR EN TIPO DE ARCHIVO");
                    throw new IOException();
                }
                Imagen imagen = new Imagen((Long) result.get("id"),
                        isbn,
                        (String) result.get("url"),
                        (String) result.get("public_id"));
                imagenRepository.save(imagen);
                return imagen;
            }
            else{
                throw new IOException();
            }
        }
        catch (IOException e){
            throw  new IOException(e.getMessage());
        }
    }

    public Imagen update(MultipartFile multipartFile, String isbn) throws Exception {
        try {
            if (imagenRepository.findByNombre(isbn).isPresent()) {
                Imagen imagen = imagenRepository.findByNombre(isbn).get();
                cloudinary.uploader().destroy(imagen.getImagenId(), ObjectUtils.emptyMap());
                File file = convert(multipartFile);
                Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                file.delete();

                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    System.out.println("ERROR EN TIPO DE ARCHIVO");
                    throw new IOException();
                }
                Imagen auxImagen = new Imagen((Long) result.get("id"),
                        isbn,
                        (String) result.get("url"),
                        (String) result.get("public_id"));
                imagen.setImagenId(auxImagen.getImagenId());
                imagen.setImagenUrl(auxImagen.getImagenUrl());
                imagenRepository.save(imagen);
                return  imagen;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
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
