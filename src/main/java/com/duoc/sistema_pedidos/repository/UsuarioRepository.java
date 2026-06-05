package com.duoc.sistema_pedidos.repository;

import com.duoc.sistema_pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByRut(String rut);
}
