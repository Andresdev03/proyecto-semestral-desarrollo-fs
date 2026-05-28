package org.example.producto.Controller;

import lombok.RequiredArgsConstructor;
import org.example.producto.Model.ProductoIngredienteId;
import org.example.producto.Model.ProductoIngredienteModel;
import org.example.producto.Service.ProductoIngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto-ingrediente")
@RequiredArgsConstructor
public class ProductoIngredienteController {
    private final ProductoIngredienteService service;

    @GetMapping
    public ResponseEntity <List<ProductoIngredienteModel>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{idProducto}/{idIngrediente}")
    public ResponseEntity<ProductoIngredienteModel> buscarPorId(
            @PathVariable Long idProducto,
            @PathVariable Long idIngrediente){
        ProductoIngredienteId id = new  ProductoIngredienteId(idProducto,idIngrediente);
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    public  ResponseEntity<ProductoIngredienteModel> guardar(@RequestBody ProductoIngredienteModel productoIngredienteModel){
        return ResponseEntity.ok(service.guardar(productoIngredienteModel));
    }
    @DeleteMapping ("/{idProducto}/{idIngrediente}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long idProducto,
            @PathVariable Long idIngrediente ){
        service.eliminar(new ProductoIngredienteId(idProducto,idIngrediente));
        return ResponseEntity.noContent().build();
    }

}
