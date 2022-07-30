package com.lib.api.services;

import com.lib.api.entities.Cuenta;
import com.lib.api.entities.Rol;
import com.lib.api.repositories.CuentaRepository;
import com.lib.api.repositories.RolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Logger logger = LoggerFactory.getLogger(CuentaServiceImpl.class);
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RolRepository rolRepository;
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cuenta cuenta = cuentaRepository.findByEmail(email);

        if(email == null){
            logger.error("Error en el login: no existe el usuario '"+ email +"' en el sistema");
            throw  new UsernameNotFoundException("Error en el login: no existe el usuario '"+ email +"' en el sistema");
        }

        List<GrantedAuthority> authorities = cuenta.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(cuenta.getEmail(), cuenta.getContrasena(), cuenta.isEnabled(), true, true, true, authorities);
    }
    @Override
    @Transactional
    public List<Cuenta> findAll() throws Exception {
        try{
            return cuentaRepository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Cuenta findById(Long id) throws Exception {
        try{
            Optional<Cuenta> optionalEntitie = cuentaRepository.findById(id);
            return optionalEntitie.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
    @Override
    @Transactional
    public Cuenta findByEmail(String email) throws Exception {
        try{
            return cuentaRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
    @Override
    @Transactional
    public Cuenta save(Cuenta entity) throws Exception {
        try{
            entity.setFechaCreacion(LocalDate.now());
            entity.setContrasena(bCryptPasswordEncoder.encode(entity.getContrasena()));
            List<Rol> roles = new ArrayList<>();
            for(Rol rol: entity.getRoles()){
                roles.add(rolRepository.findByNombre(rol.getNombre()).get());
            }
            entity.setRoles(roles);
            return cuentaRepository.save(entity);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cuenta update(Long id, Cuenta entity) throws Exception {
        try {
            if (cuentaRepository.existsById(id)) {
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
            if(cuentaRepository.existsById(id)){
                cuentaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
