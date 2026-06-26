package org.example.region.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.region.Model.RegionModel;
import org.example.region.Service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/region")
@CrossOrigin(origins="*")
@Tag(name="Region",description = "Controlador principal para la gestion de las regiones geograficas")
public class RegionController {
    private static  final Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionService regionService;
    // obtener las regiones

    @GetMapping("")
    @Operation(summary = "Obtener todas las regiones", description = "Retorna una lista con todas las regiones registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de regiones obtenido con exito"),
            @ApiResponse(responseCode = "204", description = "No hay regiones almacenadas en la base de datos"),
            @ApiResponse(responseCode = "500", description = "Error interno al procesar el listado de regiones"),
    })
    public ResponseEntity<?> getAllRegiones(){
        logger.info("Iniciando consulta ");
        try {List<RegionModel> listado = regionService.listarRegiones();
            if(listado.isEmpty()){
                logger.warn("No se han encontrado regiones");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("Se encontraron {} regiones",listado.size());
                return new ResponseEntity<>(listado,HttpStatus.OK);
            }
        }catch (Exception e){
                logger.error("No se han encontrado regiones");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Error interno al procesar la solicitud");
        }
    }
   //Guardar una nueva region

    @GetMapping("/{id}")
    @Operation(summary = "Buscar region por ID", description = "Retorna los datos de una region especifica mediante su identificador unico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region encontrada con exito"),
            @ApiResponse(responseCode = "404", description = "No se encontro ninguna region con el ID provisto"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar la region"),
    })
    public ResponseEntity<?> getRegionById(@PathVariable Integer id){
        logger.info("Buscando region por id");
        try{RegionModel buscado= regionService.buscarPorId(id);
            if(buscado !=null){
                logger.info("Región con ID {} encontrada", id);
                return new ResponseEntity<>(buscado, HttpStatus.OK);
            }else{
                logger.warn("No se encontró la región");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error crítico al buscar región por ID {}: ", id, e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al buscar la región");
        }


    }

    @PostMapping("")
    @Operation(summary = "Crear una nueva región", description = "Crea una nueva region geografica a partir de los datos provistos en el cuerpo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Region creada con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud incorrectos o invalidos"),
            @ApiResponse(responseCode = "500", description = "Error interno al guardar la region"),
    })
   public ResponseEntity<?>crearRegion(@RequestBody RegionModel region){
        logger.info("Solicitud para crear nueva región recibida");
        try{RegionModel nuevaRegion = regionService.agregarRegion(region);
            if(nuevaRegion != null){
                logger.info("Región creada exitosamente");
                return new ResponseEntity<>(nuevaRegion, HttpStatus.CREATED);
            }else{
                logger.warn("No se pudo crear la región. Datos inválidos.");
                return new ResponseEntity<>("Datos de solicitud incorrectos",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error crítico al crear la región: ", e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la región");
        }

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una region", description = "Remueve una region de los regitros mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region eliminada con exito"),
            @ApiResponse(responseCode = "400", description = "No se pudo eliminar la region, es posible que no exista "),
            @ApiResponse(responseCode = "500", description = "Error interno al eliminar la region"),
    })
    public ResponseEntity<?> deleteRegion(@PathVariable Integer id){
        logger.info("Intentando eliminar región");
        try {
            boolean res = regionService.borrarRegion(id);
            if (res){
                logger.info("Región eliminada exitosamente");
                return  new ResponseEntity<>(HttpStatus.OK);
            }else{
                logger.warn("No se pudo eliminar la región con ID {}. Puede que no exista.", id);
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error crítico al eliminar la región con ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la eliminación");

        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una region existente", description = "Modifica los valores de una region en la base de datos usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region actualizada con exito"),
            @ApiResponse(responseCode = "400", description = "Region no encontrada o datos invalios para la actualizacion "),
            @ApiResponse(responseCode = "500", description = "Error al actualizar la region"),
    })
    public ResponseEntity<?> updateRegion(@PathVariable Integer id, @RequestBody RegionModel nuevo){
        logger.info("Intentando actualizar región");
        try {
            RegionModel actualizado = regionService.actualizarRegion(id, nuevo);
            if (actualizado != null) {
                logger.info("Región con ID {} actualizada correctamente", id);
                return new ResponseEntity<>(actualizado, HttpStatus.OK);
            } else {
                logger.warn("No se encontró la región");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            logger.error("Error crítico al actualizar la región");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al actualziar la region");
        }


    }

}
