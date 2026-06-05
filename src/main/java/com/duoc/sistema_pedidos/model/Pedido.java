package com.duoc.sistema_pedidos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String direccionOrigen;

    @Column(nullable = false)
    private String direccionDestino;

    @Column(nullable = false)
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }
}
