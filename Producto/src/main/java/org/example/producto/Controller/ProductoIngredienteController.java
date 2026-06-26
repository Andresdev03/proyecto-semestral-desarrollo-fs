package org.example.producto.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.producto.Model.ProductoIngredienteId;
import org.example.producto.Model.ProductoIngredienteModel;
import org.example.producto.Service.ProductoIngredienteService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto-ingrediente")
@RequiredArgsConstructor
public class ProductoIngredienteController {
    private  static  final Logger logger =LoggerFactory.getLogger(ProductoIngredienteController.class);
    private final ProductoIngredienteService service;

    @GetMapping
    @Operation(summary = "Listar todas las asociaciones",description = "Retorna una lista completa con todas las relaciones producto-ingrediente existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de asociaciones obtenido con exito"),
            @ApiResponse(responseCode = "204", description = "No hay registros en la tabla intermedia"),
            @ApiResponse(responseCode = "500", description = "Error interno al procesar el listado")
    })
    public ResponseEntity <?> listar(){
        logger.info("Iniciando consulta de todas las asociaciones producto-ingediente");
        try{
            List<ProductoIngredienteModel> lista = service.listar();
            if(lista.isEmpty()){
                logger.info("Iniciando consulta");
                logger.warn("No se");
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Se encontraron {} asociaciones exitosamente",lista.size());
            return ResponseEntity.ok(lista);
        }catch (Exception e){
            logger.error("Error critico al listar las asociaciones producto-ingrediente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar el listado de asociaciones");

        }


    }

    @GetMapping("/{idProducto}/{idIngrediente}")
    @Operation(summary = "Buscar asociación por clave compuesta",description = "Retorna una relacion especifica usando el ID del producto y el ID del ingrediente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asociación encontrada con éxito"),
            @ApiResponse(responseCode = "404", description = "Asociación no encontrada para los IDs provistos"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar la asociación")
    })
    public ResponseEntity<?> buscarPorId(
            @PathVariable Long idProducto,
            @PathVariable Long idIngrediente){
            logger.info("Buscando asociación para Producto ID: {} e Ingrediente ID: {}", idProducto, idIngrediente);

    try {
        ProductoIngredienteId id = new  ProductoIngredienteId(idProducto,idIngrediente);
        ProductoIngredienteModel buscado = service.findById(id);
        if(buscado != null){
            logger.info("Asociacion encontrada con exito");
            return ResponseEntity.ok(buscado);
        }else{
            logger.warn("No se encontró la asociacion para los IDs provistos");
            return new ResponseEntity<>("Asociacion no encontrada",HttpStatus.NOT_FOUND);
        }
    }catch (Exception e){
        logger.error("Error critico al buscar asociacion por ID compuesto",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al buscar la asociacion");
            }


    }

    @PostMapping
    @Operation(summary = "Asociar un  ingrediente a un producto",description = "Crea un nuevo registro en la tabla intermedia a partir del cuerpo de la solicitud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asociación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud  incorrectos o inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno al guardar la asociación")
    })
    public  ResponseEntity<?> guardar(@RequestBody ProductoIngredienteModel productoIngredienteModel){
        logger.info("Solicitud recibida para guardar asociación producto-ingrediente");
        try {
            ProductoIngredienteModel guardado = service.guardar(productoIngredienteModel);
            if (guardado !=null){
                logger.info("Asociación guardada exitosamente");
                return  ResponseEntity.status(HttpStatus.CREATED).body(guardado);
            }else{
                logger.warn("No se pudo guardar la asociación. Datos inválidos.");
                return new ResponseEntity<>("Datos de solicitud incorrectos",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error critico al guardar la asociación producto-ingrediente: ",e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al guardas la association");

        }

    }


    @DeleteMapping ("/{idProducto}/{idIngrediente}")
    @Operation(summary = "Eliminar una asociacion especifica",description = "Remueve permanentemente el registro intermedio usando el ID del producto y del ingrediente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asociación eliminada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al procesar la eliminación")
    })
    public ResponseEntity<?> eliminar(
            @PathVariable Long idProducto,
            @PathVariable Long idIngrediente ){
        logger.info("Intentando eliminar asociación de Producto ID: {} e Ingrediente ID: {}", idProducto, idIngrediente);
        try{
            ProductoIngredienteId id = new ProductoIngredienteId(idProducto,idIngrediente);
            service.eliminar(id);
            logger.info("Asociación eliminada exitosamente");
            return  ResponseEntity.noContent().build();

        }catch (Exception e){
            logger.error("Error crítico al eliminar la asociación producto-ingrediente:",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la eliminación");

        }

    }

}
