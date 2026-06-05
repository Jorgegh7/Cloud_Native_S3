package com.duoc.sistema_pedidos.controller;

import com.duoc.sistema_pedidos.model.Pedido;
import com.duoc.sistema_pedidos.service.contrato.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sistema-pedidos/pedidos")
public class PedidoController {


    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listaPedidos(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pedidoService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido){
        return ResponseEntity.status(201).body(pedidoService.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido){
        try {
            return ResponseEntity.ok(pedidoService.update(id, pedido));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pedidoService.delete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
