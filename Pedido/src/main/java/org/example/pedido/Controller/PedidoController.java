package org.example.pedido.Controller;

import org.example.pedido.Model.PedidoModel;
import org.example.pedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoModel>> findAll(){
        List<PedidoModel> pedidos = pedidoService.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> findById(@PathVariable Integer id){
        PedidoModel pedido = pedidoService.findById(id);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoModel> save(@RequestBody PedidoModel pedido){
        PedidoModel newPedido = pedidoService.addPedido(pedido);
        if(newPedido == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoModel> delete(@PathVariable Integer id){
        boolean result = pedidoService.deleteById(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
