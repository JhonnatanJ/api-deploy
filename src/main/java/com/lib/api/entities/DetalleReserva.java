package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "detalle_reserva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReserva {

    @Id
    @Column(name = "id_detalle_reserva", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idDetalleReserva;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "subtotal", nullable = false, scale = 2)
    private double subtotal;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "isbn")
    private Libro libro;
}
