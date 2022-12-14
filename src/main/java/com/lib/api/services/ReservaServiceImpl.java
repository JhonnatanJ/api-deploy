package com.lib.api.services;

import com.lib.api.entities.*;
import com.lib.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private NotaVentaRepository notaVentaRepository;
    @Autowired
    private NotaVentaServiceImpl notaVentaService;

    @Override
    @Transactional
    public List<Reserva> findAll() throws Exception {
        try{
            return reservaRepository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reserva findById(Long id) throws Exception {
        try{
            Optional<Reserva> optionalEntitie = reservaRepository.findById(id);
            return optionalEntitie.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reserva save(Reserva entity) throws Exception {
        try {
            if (entity.getIdReserva()!=null && (entity.getAbono() <= entity.getValorTotal() && entity.getAbono() > 0)) {
                double saldo2 = Math.round((entity.getValorTotal() - entity.getAbono()) * 100.0) / 100.0;
                entity.setSaldo(saldo2);
                entity = reservaRepository.save(entity);
                if(saldo2 == 0){
                    NotaVenta notaVenta = new NotaVenta();
                    notaVenta.setFechaRegistro(entity.getFechaAbono());
                    notaVenta.setCuenta(entity.getCuenta());
                    List<Detalle> listaDetalle = new ArrayList<>();
                    for (DetalleReserva detalleReserva: entity.getDetalleReservas()) {
                        Detalle detalle = new Detalle();
                        detalle.setCantidad(detalleReserva.getCantidad());
                        detalle.setLibro(detalleReserva.getLibro());
                        listaDetalle.add(detalle);
                    }
                    notaVenta.setDetalles(listaDetalle);
                    notaVentaService.save(notaVenta);
                    delete(entity.getIdReserva());
                }
                return entity;
            }
            entity.setFechaCreacion(LocalDate.now());
            entity.setFechaAbono(LocalDate.now());
            entity.setCuenta(cuentaRepository.findById(entity.getCuenta().getIdCuenta()).get());

            if(usuarioRepository.findById(entity.getUsuario().getCi()).isPresent()){
                entity.setUsuario(usuarioRepository.findById(entity.getUsuario().getCi()).get());
            }

            if(entity.getIdReserva()==null){
                double valor_total = 0;
                for(DetalleReserva detalleReserva: entity.getDetalleReservas()){
                    Libro libro = libroRepository.findById(detalleReserva.getLibro().getISBN()).get();
                    libro.RemoveStock(detalleReserva.getCantidad());
                    libroRepository.save(libro);
                    detalleReserva.setLibro(libro);
                    detalleReserva.setSubtotal(Math.round((detalleReserva.getLibro().getPrecioUnitario() * detalleReserva.getCantidad())*100.0)/100.0);
                    valor_total += detalleReserva.getSubtotal();
                }
                valor_total = Math.round(valor_total*100.0)/100.0;
                double saldo = Math.round((valor_total - entity.getAbono())*100.0)/100.0;
                entity.setValorTotal(valor_total);
                entity.setSaldo(saldo);
            }

            entity = reservaRepository.save(entity);
            return entity;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reserva update(Long id, Reserva entity) throws Exception {
        try {
            if(reservaRepository.findById(id).isPresent()){
                save(entity);
                return entity;
            }
            else{
                throw new Exception();
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
            if(reservaRepository.existsById(id)){
                reservaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public List<Reserva> findByDate(String fecha) throws Exception {
        try{
            LocalDate localDate = LocalDate.parse(fecha);
            return reservaRepository.findByDate(localDate);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public List<Reserva> findByDateAbono(String fecha) throws Exception{
        try{
            LocalDate localDate = LocalDate.parse(fecha);
            return reservaRepository.findByDateAbono(localDate);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public List<Reserva> findByDateCompleto(String fecha) throws Exception{
        try{
            LocalDate localDate = LocalDate.parse(fecha);
            return reservaRepository.findByDateCompleto(localDate);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
