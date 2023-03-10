package com.lib.api.controllers;

import com.lib.api.entities.NotaVenta;
import com.lib.api.services.NotaVentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geolib/notasventas")
public class NotaVentaController {

    @Autowired
    private NotaVentaServiceImpl notaVentaServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> getOne(@PathVariable String fecha) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.findByDate(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fecha/{fechainicio}/{fechafin}")
    public ResponseEntity<?> getOne(@PathVariable String fechainicio, @PathVariable String fechafin) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.findByDate2(fechainicio, fechafin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody NotaVenta entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody NotaVenta entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(notaVentaServiceImpl.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(notaVentaServiceImpl.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
