package com.duoc.sistema_pedidos.service.contrato;

import com.duoc.sistema_pedidos.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Optional<Usuario> update(Long id, Usuario usuario);
    Boolean delete(Long id);

}
