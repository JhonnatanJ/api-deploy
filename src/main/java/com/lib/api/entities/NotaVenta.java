package com.lib.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "notaventa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaVenta {

    @Id
    @Column(name = "id_notaventa", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotaventa;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @Column(name = "valor_total", nullable = false, scale = 2)
    private Double valorTotal;

    //--------------------------------------------------------------------------------------------------------

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "notaventa_detalle",
            joinColumns = @JoinColumn(name = "id_notaventa"),
            inverseJoinColumns = @JoinColumn(name = "id_detalle")
    )
    private List<Detalle> detalles;
}
