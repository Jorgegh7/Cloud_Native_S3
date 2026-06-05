package com.duoc.sistema_pedidos.repository;

import com.duoc.sistema_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
