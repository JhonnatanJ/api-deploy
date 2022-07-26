package com.lib.api.controllers;

import com.lib.api.entities.Imagen;
import com.lib.api.services.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/geolib/imagenes")
public class ImagenController {
    @Autowired
    ImagenServiceImpl imagenServiceImpl;

    @GetMapping("")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenServiceImpl.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(imagenServiceImpl.save(multipartFile));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {

        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(imagenServiceImpl.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
