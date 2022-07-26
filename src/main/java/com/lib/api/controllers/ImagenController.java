package com.lib.api.controllers;

import com.lib.api.entities.Imagen;
import com.lib.api.services.CloudinaryService;
import com.lib.api.services.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/geolib/imagen")
public class ImagenController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenServiceImpl imagenServiceImpl;

    @GetMapping("")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenServiceImpl.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null){
            return new ResponseEntity("imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen = new Imagen((Long)result.get("id"),(String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id"));
        imagenServiceImpl.save(imagen);
        return new ResponseEntity("Imagen Guardada", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") Long id) throws IOException {

        if(!imagenServiceImpl.exists(id)){
            return new ResponseEntity("Imagen no existe", HttpStatus.NOT_FOUND);
        }
        Imagen imagen = imagenServiceImpl.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenServiceImpl.delete(id);
        return new ResponseEntity("Imagen Eliminada", HttpStatus.OK);
    }
}
