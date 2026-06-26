package org.example.pedido.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.pedido.Model.PedidoModel;
import org.example.pedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@Tag(name = "API Pedido", description = "API para la gestión de pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos",
            description = "Endpoint para consultar todos los pedidos registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornados todos los pedidos")
    @ApiResponse(responseCode = "204", description = "Consulta exitosa, pero no hay pedidos registrados")
    public ResponseEntity<List<PedidoModel>> findAll(){
        List<PedidoModel> pedidos = pedidoService.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido según ID",
            description = "Endpoint para obtener un pedido específico mediante su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornado pedido")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no se ha encontrado el recurso")
    public ResponseEntity<PedidoModel> findById(@PathVariable Integer id){
        PedidoModel pedido = pedidoService.findById(id);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo pedido",
            description = "Endpoint para generar un nuevo pedido en la BBDD")
    @ApiResponse(responseCode = "200", description = "Registro exitoso, nuevo pedido agregado")
    @ApiResponse(responseCode = "400", description = "Registro fallido, datos de la solicitud incorrectos")
    public ResponseEntity<PedidoModel> save(@RequestBody PedidoModel pedido){
        PedidoModel newPedido = pedidoService.addPedido(pedido);
        if(newPedido == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPedido);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido por ID",
            description = "Endpoint para eliminar un pedido de la BBDD mediante su ID")
    @ApiResponse(responseCode = "200", description = "Eliminación exitosa")
    @ApiResponse(responseCode = "404", description = "Eliminación fallida, no se ha encontrado el recurso")
    public ResponseEntity<PedidoModel> delete(@PathVariable Integer id){
        boolean result = pedidoService.deleteById(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
