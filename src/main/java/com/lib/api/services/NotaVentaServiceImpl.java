package com.lib.api.controllers.services;

import com.lib.api.entities.Detalle;
import com.lib.api.entities.Libro;
import com.lib.api.entities.NotaVenta;
import com.lib.api.repositories.CuentaRepository;
import com.lib.api.repositories.LibroRepository;
import com.lib.api.repositories.NotaVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotaVentaServiceImpl implements NotaVentaService {

    @Autowired
    private NotaVentaRepository notaVentaRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private LibroService libroService;
    @Override
    @Transactional
    public List<NotaVenta> findAll() throws Exception {
        try{
            return notaVentaRepository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public NotaVenta findById(Long id) throws Exception {
        try{
            Optional<NotaVenta> optionalEntitie = notaVentaRepository.findById(id);
            return optionalEntitie.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NotaVenta> findByDate(String fecha) throws Exception {
        try{
            LocalDate localDate = LocalDate.parse(fecha);
            return notaVentaRepository.findByDate(localDate);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public NotaVenta save(NotaVenta entity) throws Exception {
        try{
            entity.setFechaRegistro(LocalDate.now());
            entity.setCuenta(cuentaRepository.findById(entity.getCuenta().getIdCuenta()).get());
            double valor_total = 0;
            for (Detalle detalle: entity.getDetalles() ) {
                Libro libro = libroRepository.findById(detalle.getLibro().getISBN()).get();
                libro.RemoveStock(detalle.getCantidad());
                libroRepository.save(libro);
                detalle.setLibro(libro);
                detalle.setSubtotal(Math.round((detalle.getLibro().getPrecioUnitario() * detalle.getCantidad())*100.0)/100.0);
                valor_total += detalle.getSubtotal();
            }
            valor_total = Math.round(valor_total*100.0)/100.0;
            entity.setValorTotal(valor_total);
            entity = notaVentaRepository.save(entity);
            return entity;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public NotaVenta update(Long id, NotaVenta entity) throws Exception {
        try {
            if (notaVentaRepository.existsById(id)) {
                save(entity);
                return entity;
            } else {
                return null;
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(notaVentaRepository.existsById(id)){
                notaVentaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
