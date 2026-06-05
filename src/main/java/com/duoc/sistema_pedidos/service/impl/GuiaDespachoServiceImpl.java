package com.duoc.sistema_pedidos.service.impl;

import com.duoc.sistema_pedidos.model.GuiaDespacho;
import com.duoc.sistema_pedidos.model.Rol;
import com.duoc.sistema_pedidos.model.Usuario;
import com.duoc.sistema_pedidos.repository.GuiaDespachoRepository;
import com.duoc.sistema_pedidos.repository.UsuarioRepository;
import com.duoc.sistema_pedidos.service.contrato.GuiaDespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuiaDespachoServiceImpl implements GuiaDespachoService {

    private final GuiaDespachoRepository guiaDespachoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public GuiaDespachoServiceImpl(GuiaDespachoRepository guiaDespachoRepository, UsuarioRepository usuarioRepository) {
        this.guiaDespachoRepository = guiaDespachoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<GuiaDespacho> findAll() {
        return guiaDespachoRepository.findAll();
    }

    @Override
    public Optional<GuiaDespacho> findById(Long id) {
        if(!guiaDespachoRepository.existsById(id)){
            throw new RuntimeException("Guia de Despacho no encontrado");
        }
        return guiaDespachoRepository.findById(id);
    }

    @Override
    public GuiaDespacho save(GuiaDespacho guiaDespacho) {
        Usuario transportista = usuarioRepository.findById(guiaDespacho.getTransportista().getId())
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

        if (transportista.getRol() != Rol.TRANSPORTISTA) {
            throw new RuntimeException("El usuario no tiene rol TRANSPORTISTA");
        }

        return guiaDespachoRepository.save(guiaDespacho);
    }

    @Override
    public Optional<GuiaDespacho> update(Long id, GuiaDespacho guiaDespacho) {
        if(!guiaDespachoRepository.existsById(guiaDespacho.getId())){
            throw new RuntimeException("Guia de Despacho no encontrado");
        }
        
        return guiaDespachoRepository.findById(guiaDespacho.getId()).map(g ->{
            Usuario transportista = usuarioRepository.findById(guiaDespacho.getTransportista().getId())
                    .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

            if (transportista.getRol() != Rol.TRANSPORTISTA) {
                throw new RuntimeException("El usuario no tiene rol TRANSPORTISTA");
            }

            g.setTransportista(transportista);
            g.setEstado(guiaDespacho.getEstado());
            g.setNumeroGuia(guiaDespacho.getNumeroGuia());
            return guiaDespachoRepository.save(g);

        });
    }

    @Override
    public Boolean delete(Long id) {
        if(!guiaDespachoRepository.existsById(id)){
            throw new RuntimeException("Guia de Despacho no encontrado");
        }
        guiaDespachoRepository.deleteById(id);
        return true;
    }
}
