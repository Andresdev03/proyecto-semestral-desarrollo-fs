package org.example.producto.Controller;

import org.apache.coyote.Response;
import org.example.producto.Model.ProductoModel;
import org.example.producto.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<ProductoModel> listarProductos(){
        return productoRepository.findAll();
    }
    @GetMapping ("/sucursal/{idSucursal}")
    public List<ProductoModel>listarPorSucursal(@PathVariable Integer idSucursal){
        return  productoRepository.findByIdSucursal(idSucursal);
    }
    @PostMapping
    public ResponseEntity<ProductoModel>crearProducto(@RequestBody ProductoModel producto){
      ProductoModel nuevoProducto=productoRepository.save(producto);
      return ResponseEntity.ok(nuevoProducto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoModel productoDetalles){
      return productoRepository.findById(id)
              .map(productoExistente ->{
                  productoExistente.setNombreProducto(productoDetalles.getNombreProducto());
                  productoExistente.setPrecio(productoDetalles.getPrecio());
                  productoExistente.setIdSucursal(productoDetalles.getIdSucursal());
                  ProductoModel productoActualizado = productoRepository.save(productoExistente);
                  return ResponseEntity.ok(productoActualizado);
              })
              .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminarProducto(@PathVariable Integer id){
        return productoRepository.findById(id)
                .map(producto ->{
                    productoRepository.delete(producto);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
