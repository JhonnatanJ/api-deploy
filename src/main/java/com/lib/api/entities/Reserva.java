package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @Column(name = "id_reserva", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdReserva;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate FechaCreacion;

    @Column(name = "fecha_abono", nullable = false)
    private LocalDate FechaAbono;

    @Column(name = "valor_total", nullable = false, scale = 2)
    private Double ValorTotal;

    @Column(name = "abono", nullable = false, scale = 2)
    private Double Abono;

    @Column(name = "saldo", nullable = false, scale = 2)
    private Double Saldo;

    //------------------------------------------------------------------------------------------------------------------------------

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cuenta", updatable = false)
    private Cuenta cuenta;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ci")
    private Usuario usuario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "reserva_detalle",
            joinColumns =@JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_detalle_reserva")
    )
    private List<DetalleReserva> detalleReservas;
}
