package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @Column(name = "id_cuenta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;

    @Column(name = "contrasena", nullable = false, length = 30)
    private String contrasena;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ci")
    private Usuario usuario;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "cuenta_rol",
            joinColumns = @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    )
    private Rol rol;
}
