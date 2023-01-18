package com.lib.api.controllers;

import com.lib.api.entities.Libro;
import com.lib.api.services.LibroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/geolib/libros")
public class LibroController {
    @Autowired
    private LibroServiceImpl libroServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Libro entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Libro entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(libroServiceImpl.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//------------------------------------------------------------------------------------------------- CONSULTAS NEGOCIO -----------------
@GetMapping("/id/{id}")
public ResponseEntity<?> getOne(@PathVariable String id) {
    try{
        return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findById(id));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getNombre(@PathVariable String nombre) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/autor/{autor}")
    public ResponseEntity<?> getAutor(@PathVariable String autor) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByAutor(autor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> getGenero(@PathVariable String genero) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByGenero(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/editorial/{editorial}")
    public ResponseEntity<?> getEditorial(@PathVariable String editorial) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByEditorial(editorial));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<?> getStock(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByStockExist());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/stockempty")
    public ResponseEntity<?> getStockEmpty(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByStockEmpty());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> getByDateSave(@PathVariable String fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByDateSave(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fecha/{fechai}/{fechaf}")
    public ResponseEntity<?> getByDateSave2(@PathVariable String fechai, @PathVariable String fechaf){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByDateSave2(fechai, fechaf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping("/novedades")
    public ResponseEntity<?> getNovedades() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.getAllByDateDESC());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/recomendados")
    public ResponseEntity<?> getRecomendados() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.getAllByDateASC());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    //--------------------------------------------------------------------- PAGED ----------------------------------------------------------

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/paged/genero/{genero}")
    public ResponseEntity<?> getGenero(@PathVariable String genero, Pageable pageable) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByGenero(genero, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/paged/titulo/{titulo}")
    public ResponseEntity<?> getTitulo(@PathVariable String titulo, Pageable pageable) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(libroServiceImpl.findByTitulo(titulo, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
