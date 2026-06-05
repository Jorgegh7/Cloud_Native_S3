package com.duoc.sistema_pedidos.service.impl;

import com.duoc.sistema_pedidos.model.Pedido;
import com.duoc.sistema_pedidos.model.Rol;
import com.duoc.sistema_pedidos.model.Usuario;
import com.duoc.sistema_pedidos.repository.PedidoRepository;
import com.duoc.sistema_pedidos.repository.UsuarioRepository;
import com.duoc.sistema_pedidos.service.contrato.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        if(!pedidoRepository.existsById(id)){
            throw new RuntimeException("Pedido no encontrado");
        }
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        Usuario remitente = usuarioRepository.findById(pedido.getRemitente().getId())
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(pedido.getDestinatario().getId())
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        if (remitente.getRol() != Rol.REMITENTE) {
            throw new RuntimeException("El usuario no tiene rol REMITENTE");
        }
        if (destinatario.getRol() != Rol.DESTINATARIO) {
            throw new RuntimeException("El usuario no tiene rol DESTINATARIO");
        }

        pedido.setRemitente(remitente);
        pedido.setDestinatario(destinatario);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> update(Long id, Pedido pedido) {
        if(!pedidoRepository.existsById(id)){
            throw new RuntimeException("Pedido no encontrado");
        }
        return pedidoRepository.findById(id).map(p -> {
            Usuario remitente = usuarioRepository.findById(pedido.getRemitente().getId())
                    .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
            Usuario destinatario = usuarioRepository.findById(pedido.getDestinatario().getId())
                    .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

            p.setDescripcion(pedido.getDescripcion());
            p.setRemitente(remitente);
            p.setDestinatario(destinatario);
            p.setDireccionOrigen(pedido.getDireccionOrigen());
            p.setDireccionDestino(pedido.getDireccionDestino());
            return pedidoRepository.save(p);
        });
    }

    @Override
    public Boolean delete(Long id) {
        if(!pedidoRepository.existsById(id)){
            throw new RuntimeException("Pedido no encontrado");
        }

        pedidoRepository.deleteById(id);
        return true;
    }
}
