package org.example.comuna.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.comuna.Client.RegionClient;
import org.example.comuna.DTO.RegionDTO;
import org.example.comuna.Model.ComunaModel;
import org.example.comuna.Service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping ("/api/comunas")
@CrossOrigin(origins = "*")
@Tag(name = "Comuna",description = "Controlador principal para la gestion geografica de comunas y comunicacion remota")
public class ComunaController {
    private static final Logger logger =LoggerFactory.getLogger(ComunaController.class);
    @Autowired
    private ComunaService comunaService;

    @Autowired
    private RegionClient regionClient;
    
    @GetMapping("/region-remota/{id}")
    @Operation(summary = "Obtener region remota",description = "Se comunica de manera distribuida con el microservicio de Region para recuperar los datos correspondientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Datos de la region remota recuperados con exito"),
            @ApiResponse(responseCode = "500",description = "Error critico de comunicacion entre microservicios")
    })
    public ResponseEntity<?> obtenerComunaRemota(@PathVariable Integer id){
        logger.info("Solicitando región remota");
        try{
           RegionDTO region= regionClient.obtenerRegionPorId(id);
           logger.info("Región remota con ID {} recuperada exitosamente", id);
            return ResponseEntity.ok(region);
        }catch (Exception e){
            logger.error("Error crítico al conectar con el microservicio",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al conectar con el microservicio: " + e.getMessage());
        }

    }

    @GetMapping("")
    @Operation(summary = "Obtener todas las comunas",description = "Retorna una lista con todas las comunas registradas en la base de datos local")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de comunas obtenido con exito"),
            @ApiResponse(responseCode = "204",description = "No hay comunas registradas en el sistema"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar el listado de comunas")
    })
    public ResponseEntity<?> listarComunas(){
        logger.info("Iniciando consulta de todas las comunas");
        try{
            List<ComunaModel> listado = comunaService.listarComunas();
            if(listado.isEmpty()){
                logger.warn("No se encontraron comunas en la base de datos");
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("Se encontraron {} comunas", listado.size());
                return new ResponseEntity<>(listado,HttpStatus.OK);
            }} catch (Exception e) {
            logger.error("Error al obtener el listado de comunas: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la solicitud");
        }

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una comuna por ID",description = "Retorna los datos de una comuna especifica mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Comuna encontrada con exito"),
            @ApiResponse(responseCode = "404",description = "No se encontro ninguna comuna con el ID provisto"),
            @ApiResponse(responseCode = "500",description = "Error interno al buscar la comuna")
    })
    public ResponseEntity<?> getComunaById(@PathVariable Integer id){
        logger.info("Buscando comuna");
        try {
            ComunaModel buscado = comunaService.buscarPorId(id);
            if(buscado !=null){
                logger.info("Comuna encontrada");
                return new ResponseEntity<>(buscado, HttpStatus.OK);
            }else {
                logger.warn("No se encontro la comuna");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            logger.error("Error al buscar la comuna");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al buscar la comuna");
        }


    }

    @GetMapping("/region/{idRegion}")
    @Operation(summary = "Listar comunas por región",description = "Retorna todas las comunas pertenecientes a un ID de region especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de comunas por region obtenido  con exito"),
            @ApiResponse(responseCode = "204",description = "No existen comunas asociadas a dicha region"),
            @ApiResponse(responseCode = "500",description = "Error interno al filtrar comunas por region")
    })
    public  ResponseEntity<?> listarporRegion(@PathVariable Integer idRegion){
        logger.info("Buscando comunas asignadas a la región");
        try{
            List<ComunaModel> listado = comunaService.listarPorRegion(idRegion);
            if (listado.isEmpty()){
                logger.warn("No se encontraron comunas para la región ID: {}", idRegion);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("Se encontraron {} comunas para la región ID: {}", listado.size(), idRegion);
                return new ResponseEntity<>(listado, HttpStatus.OK);
            }
        }catch (Exception e){
           logger.error("Error al listar comunas por región");
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }

    }

    @PostMapping("/")
    @Operation(summary = "Registrar una nueva comuna",description = "Crea una comuna en el sistema a partir del JSON enviado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Comuna creada con exito"),
            @ApiResponse(responseCode = "400",description = "Datos de solicitud incorrectos o invalidos"),
            @ApiResponse(responseCode = "500",description = "Error interno al guardar la comuna")
    })
    public ResponseEntity<?> crearComuna(@RequestBody ComunaModel comuna){
        logger.info("Solicitud para crear una nueva comuna recibida");
        try{ComunaModel nuevaComuna=comunaService.agregarComuna(comuna);
            if(nuevaComuna !=null){
                logger.info("Comuna creada exitosamente");
                return new ResponseEntity<>(nuevaComuna, HttpStatus.CREATED);
            }else {
                logger.warn("No se pudo crear la comuna. Datos inválidos.");
                return new ResponseEntity<>("Datos de solicitud incorrectos",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error crítico al crear la comuna: ", e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la comuna");

        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una comuna existente",description = "Modifica los datos de una comuna guardada usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Comuna actualizada con exito"),
            @ApiResponse(responseCode = "400",description = "Comuna no encontrada o parametros invalidos"),
            @ApiResponse(responseCode = "500",description = "Error interno al actualizar la comuna")
    })
    public ResponseEntity<?> actualizarComuna(@PathVariable Integer id, @RequestBody ComunaModel nuevo) {
        logger.info("Intentando actualizar comuna");
        try{
            ComunaModel actualizado = comunaService.actualizarComuna(id,nuevo);
            if(actualizado != null){
                logger.info("Comuna actualizada correctamente");
                return  new ResponseEntity<>(actualizado,HttpStatus.OK);
            }else{
                logger.warn("No se encontró la comuna para actualizar");
                return  new ResponseEntity<>("Comuna no encontrada o datos inválidos",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error crítico al crear la comuna: ", e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al guardar la comuna");

        }

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una comuna",description = "Remueve la comuna de los registros mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Comuna eliminada con exito"),
            @ApiResponse(responseCode = "400",description = "No se pudo eliminar la comuna, es posible que no exista"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar la eliminacion")
    })
    public ResponseEntity<?> eliminarComuna(@PathVariable Integer id){
        logger.info("Intentando eliminar comuna");
        try{

            boolean res= comunaService.borrarComuna(id);
            if (res){
                logger.info("Comuna con ID {} eliminada exitosamente", id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                logger.warn("No se pudo eliminar la comuna. Puede que no exista.");
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error crítico al eliminar la comuna");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la eliminacion");

        }

    }
}
