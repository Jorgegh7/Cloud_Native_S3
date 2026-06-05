package com.duoc.sistema_pedidos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "guias_despacho")
@Getter
@Setter
@NoArgsConstructor
public class GuiaDespacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroGuia;

    @ManyToOne
    @JoinColumn(name = "transportista_id", nullable = false)
    private Usuario transportista;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
        if (this.estado == null) {
            this.estado = "PENDIENTE";
        }
    }
}