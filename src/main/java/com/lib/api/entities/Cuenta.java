package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "contrasena", nullable = false, length = 70)
    private String contrasena;

    private boolean enabled;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ci")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cuenta_rol",
            joinColumns = @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_cuenta","id_rol"})}
    )
    private List<Rol> roles;
}
