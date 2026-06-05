package com.duoc.sistema_pedidos.service.contrato;

import com.duoc.sistema_pedidos.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    Optional<Pedido> update(Long id, Pedido pedido);
    Boolean delete(Long id);

}
