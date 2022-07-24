package com.lib.api.controllers;

import com.lib.api.services.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/geolib/generos")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoServiceImpl.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoServiceImpl.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getAll(@PathVariable String nombre) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoServiceImpl.findByNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
}
