package com.lib.api.services;

import com.lib.api.entities.Detalle;
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
                System.out.println(entity.getCuenta().getIdCuenta());
            entity.setCuenta(cuentaRepository.findById(entity.getCuenta().getIdCuenta()).get());
                System.out.println(entity.getCuenta().getIdCuenta()+" "+entity.getCuenta().getEmail());

            double valor_total = 0;

            for (Detalle detalle: entity.getDetalles() ) {
                System.out.println(detalle.getLibro().getISBN());
                System.out.println(libroRepository.findById(detalle.getLibro().getISBN()).get().getISBN());
                detalle.setLibro(libroRepository.findById(detalle.getLibro().getISBN()).get());
                System.out.println(detalle.getLibro().getISBN() + " " + detalle.getLibro().getTitulo());

                detalle.setSubtotal(detalle.getLibro().getPrecioUnitario() * detalle.getCantidad());
                System.out.println(detalle.getSubtotal());

                valor_total += detalle.getSubtotal();
            }
                System.out.println(valor_total);
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
