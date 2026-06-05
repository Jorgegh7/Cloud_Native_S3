package com.duoc.sistema_pedidos.service.impl;

import com.duoc.sistema_pedidos.model.Usuario;
import com.duoc.sistema_pedidos.repository.UsuarioRepository;
import com.duoc.sistema_pedidos.service.contrato.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByRut(usuario.getRut())) {
            throw new RuntimeException("Ya existe un usuario con ese RUT");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setCorreo(usuario.getCorreo());
            u.setRol(usuario.getRol());
            return usuarioRepository.save(u);
        });
    }

    @Override
    public Boolean delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}