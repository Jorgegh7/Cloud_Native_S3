package com.duoc.sistema_pedidos.controller;

import com.duoc.sistema_pedidos.model.GuiaDespacho;
import com.duoc.sistema_pedidos.service.contrato.GuiaDespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sistema-pedidos/guias")
public class GuiaDespachoController {

    private final GuiaDespachoService guiaDespachoService;

    @Autowired
    public GuiaDespachoController(GuiaDespachoService guiaDespachoService) {
        this.guiaDespachoService = guiaDespachoService;
    }

    @GetMapping
    public ResponseEntity<List<GuiaDespacho>> listaGuias() {
        return ResponseEntity.ok(guiaDespachoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(guiaDespachoService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarGuia(@RequestBody GuiaDespacho guiaDespacho) {
        try {
            return ResponseEntity.status(201).body(guiaDespachoService.save(guiaDespacho));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGuia(@PathVariable Long id, @RequestBody GuiaDespacho guiaDespacho) {
        try {
            return ResponseEntity.ok(guiaDespachoService.update(id, guiaDespacho));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGuia(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(guiaDespachoService.delete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
