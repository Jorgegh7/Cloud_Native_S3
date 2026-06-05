package com.duoc.sistema_pedidos.controller;

import com.duoc.sistema_pedidos.model.Usuario;
import com.duoc.sistema_pedidos.service.contrato.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sistema-pedidos/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuario(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(usuarioService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario){
        try{
            return ResponseEntity.status(201).body(usuarioService.save(usuario));
        }catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        try{
            return ResponseEntity.ok(usuarioService.delete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

}