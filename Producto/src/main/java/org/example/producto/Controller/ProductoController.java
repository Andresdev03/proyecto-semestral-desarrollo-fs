package org.example.producto.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.producto.Model.ProductoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.producto.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Producto",description = "Controlador principal para la gestion del catalogo de productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("")
    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista con todos los productos regisrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Catalogo de productos obtenidos con exito"),
            @ApiResponse(responseCode = "204",description = "No hay productos registrados en el sistema"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar el listado de productos")
    })
    public ResponseEntity<?> getAllProductos(){
        logger.info("Iniciando consulta global de productos");
        try{
            List<ProductoModel>listado = productoService.listarProductos();
            if (listado.isEmpty()){
                logger.warn("No se encontraron productos en la base de datos");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("Se encontraron {} productos con éxito", listado.size());
                return  new ResponseEntity<>(listado,HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error("Error crítico al obtener el listado de productos: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar el listado de productos");
        }

    }


    @PostMapping
    @Operation(summary = "Registrar un nuevo producto", description = "Crear un nuevo producto en el catalogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400",description = "Datos de solicitud incorrectos o invalidos"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar el listado de productos")
    })
    public ResponseEntity<?>crearProducto(@RequestBody ProductoModel producto){
        logger.info("Solicitud recibida para registrar un nuevo producto.");
        try {
            ProductoModel nuevoProducto=productoService.agregarProducto(producto);
            if (nuevoProducto !=null){
                logger.info("Producto creado exitosamente");
                return  new ResponseEntity<>(nuevoProducto,HttpStatus.CREATED);
            }else{
                logger.warn("No se pudo crear el producto. Parámetros inválidos.");
                return new ResponseEntity<>("Datos de solicitud incorrectos",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error crítico al intentar guardar el producto: ", e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar el producto");

        }


    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente", description = "Modifica los datos de un producto existente utilizando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400",description = "Producto no encontrado o datos invalidos"),
            @ApiResponse(responseCode = "500",description = "Error critico al actualizar el producto")
    })
    public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody ProductoModel nuevo){
        logger.info("Solicitud recibida para actualizar el producto con ID: {}",id);
        try{
            ProductoModel actualizado = productoService.actualizarProducto(id, nuevo);
            if (actualizado !=null){
                logger.info("Producto actualizado correctamente");
                return new ResponseEntity<>(actualizado,HttpStatus.OK);
            }else{
                logger.warn("No se encontró el producto para actualizar");
                return  new ResponseEntity<>("Producto no encontrado o datos inválidos",HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            logger.error("Error crítico al actualizar el producto");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al modificar la información del producto");

        }



    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Remueve permanentemente un producto de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto eliminado de manera exitosa"),
            @ApiResponse(responseCode = "400",description = "No se pudo eliminar el producto, es posible que no exista"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar la eliminacion")
    })
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id){
        logger.info("Solicitud para eliminar producto con ID: {}", id);
        try{
            boolean res= productoService.borrarProducto(id);
            if (res){
                logger.info("Producto eliminado correctamente");
                return  new ResponseEntity<>(HttpStatus.OK);
            }else{
                logger.warn("No se pudo eliminar el producto es posible que no exista.");
                return new ResponseEntity<>("No se pudo eliminar el producto",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error crítico al intentar borrar el producto.");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la eliminación del producto");
        }

    }
}
