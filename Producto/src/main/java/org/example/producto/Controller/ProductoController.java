package org.example.producto.Controller;

import org.apache.coyote.Response;
import org.example.producto.Model.ProductoModel;
import org.example.producto.Repository.ProductoRepository;
import org.example.producto.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<ProductoModel>> getAllProductos(){
        List<ProductoModel>listado = productoService.listarProductos();
        if (listado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(listado,HttpStatus.OK);
        }
    }

    @GetMapping ("/sucursal/{idSucursal}")
    public ResponseEntity<List<ProductoModel>> listarPorSucursal(@PathVariable Integer idSucursal){
        List<ProductoModel>listado = productoService.listarPorSucursal(idSucursal);
        if (listado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(listado,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<ProductoModel>crearProducto(@RequestBody ProductoModel producto){
      ProductoModel nuevoProducto=productoService.agregarProducto(producto);
      if (nuevoProducto !=null){
          return  new ResponseEntity<>(nuevoProducto,HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> updateProducto(@PathVariable Integer id, @RequestBody ProductoModel nuevo){
    ProductoModel actualizado = productoService.actualizarProducto(id, nuevo);
    if (actualizado !=null){
        return new ResponseEntity<>(actualizado,HttpStatus.OK);
    }else{
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id){
        boolean res= productoService.borrarProducto(id);
        if (res){
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
