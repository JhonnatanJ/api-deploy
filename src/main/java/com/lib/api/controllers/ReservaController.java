package com.lib.api.controllers;

import com.lib.api.entities.Reserva;
import com.lib.api.services.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path = "/geolib/reservas")
public class ReservaController {

    @Autowired
    private ReservaServiceImpl reservaServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Reserva entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Reserva entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reservaServiceImpl.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }


    //---------------------------------------------------------- CONSULTAS

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> getByDate(@PathVariable String fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findByDate(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/abonos/{fecha}")
    public ResponseEntity<?> getByDateAbono(@PathVariable String fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findByDateAbono(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/liquidado/{fecha}")
    public ResponseEntity<?> getByDateCompleto(@PathVariable String fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findByDateCompleto(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

}
