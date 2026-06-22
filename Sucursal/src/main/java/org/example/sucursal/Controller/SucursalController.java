package org.example.sucursal.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.sucursal.Client.ComunaClient;
import org.example.sucursal.DTO.ComunaDTO;
import org.example.sucursal.Model.SucursalModel;
import org.example.sucursal.Service.SucursalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sucursal")
@CrossOrigin(origins = "*")
@Tag(name ="Sucursal", description = "Controlador principal para la gestion de sucursal fisicas y conexion distribuida")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    private static  final Logger logger = LoggerFactory.getLogger(SucursalController.class);

    @Autowired
    private ComunaClient comunaClient;


    @GetMapping("/comuna-remota/{id}")
    @Operation(summary = "Obtener comuna remota via Feign",description = "Se comunica distributivamente con el microservicio de Comuna para traer los datos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Datos de la comuna remota obtenidos con exito"),
            @ApiResponse(responseCode = "500",description = "Error de comunicacion o caida en el microservicio remoto"),
    })
    public ResponseEntity<?> obtenerComunaRemota(@PathVariable Integer id){
        logger.info("Solicitando comuna remota con ID: {}",id);
        try{
            logger.info("Comuna encontrada exitosamente{}",id);

           ComunaDTO comuna= comunaClient.obtenerComunaPorId(id);
           return ResponseEntity.ok(comuna);
        }catch (Exception e){
            logger.error("Error al conectar con el microservicio de comuna para el ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al conectar con el microservicio: " + e.getMessage());
        }

    }

    @GetMapping("")
    @Operation(summary = "Listar todas las sucursales",description = "Retorna un listado completo de las sucursales en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de sucursales obtenido con exito"),
            @ApiResponse(responseCode = "204",description = "No exiten sucursales registradas en la base de datos"),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor")
    })
    public ResponseEntity<?> getAllSucursales(){
        logger.info("iniciando consulta");
        try{
            List<SucursalModel> listado= sucursalService.listarSucursales();
            if (listado.isEmpty()){
                logger.warn("No se encontraron sucursales");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Se encontraron {} sucursales exitosamente", listado.size());
            return   new ResponseEntity<>(listado,HttpStatus.OK);
        }catch ( Exception e){
            logger.error("Error al obtener el listado de sucursales: ",e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno");
        }
     }



    @GetMapping("/comuna/{idComuna}")
    @Operation(summary = "Filtrar sucursales por Comuna",description = "Retorna las sucursales asociadas a una comuna especifica usando su ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Sucursales de la comuna obtenidas con exito"),
            @ApiResponse(responseCode = "204",description = "No hay sucursales asociadas a esa comuna"),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor")
    })
    public ResponseEntity<?> listarPorComuna(@PathVariable Integer idComuna){
        logger.info("Buscando sucursales para la comuna ID:{}",idComuna);
        try{
            List<SucursalModel> listado = sucursalService.listarPorComuna(idComuna);
            if (listado.isEmpty()){
                logger.warn("No se encontraro sucursales");
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Se encontraro sucursales para la comuna {}",idComuna);
            return new ResponseEntity<>(listado,HttpStatus.OK);
            }catch (Exception e){
            logger.error("Error al listar sucursales por comuna para el ID{}",idComuna,e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al consultar las socursales");


        }

    }

    @PostMapping("/")
    @Operation(summary = "Registrar nueva sucursal",description = "Crea una sucursal en el sistema con los datos provistos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Sucursal registrada con exito"),
            @ApiResponse(responseCode = "400",description = "Parametros de solicitud incorrectos o invalidos"),
            @ApiResponse(responseCode = "500",description = "Error interno al guardar la sucursal")
    })
    public ResponseEntity<?>crearSucursal(@RequestBody SucursalModel sucursal){
        logger.info("Intentando crear una nueva sucursal: {}",sucursal.getNombreSucursal());
        try {SucursalModel nuevaSucursal =sucursalService.agregarSucursal(sucursal);
            if (nuevaSucursal !=null){
                logger.info("Intentando crear una nueva sucursal",sucursal.getNombreSucursal());
                return  new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
            }else{
                logger.warn("No se pudo crear la sucursal.Datos invalidos recibidos.");
                return  new ResponseEntity<>("Datos de solicitud incorrectos",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("Error al crear la sucursal",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar");
        }





    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar suscursal existente",description = "Modifica los valores de una sucursal usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Sucursal actualizada de manera exitosa"),
            @ApiResponse(responseCode = "400",description = "Sucursal no encontrada o datos invalidos"),
            @ApiResponse(responseCode = "500",description = "Error interno al actualizar")
    })
    public  ResponseEntity<?> actualizarSucursal(@PathVariable Integer id,@RequestBody SucursalModel nuevo){
        logger.info("Intentando actualizar sucursal ID: {}",id);
        try {
            SucursalModel actualizado= sucursalService.actualizarSucursal(id, nuevo);
            if(actualizado!=null){
                logger.info("Sucursal actualizada correctamente");
                return  new ResponseEntity<>(actualizado,HttpStatus.OK);
            }else{
                logger.warn("No se encontro la sucursal para actualizar");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
                logger.error("Error al actualizar la sucursal",e);
                return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al acuralizar");
        }

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sucursal",description = "Remueve la sucursal de los registros mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Sucursal eliminada con exito"),
            @ApiResponse(responseCode = "400",description = "No se pudo eliminar la sucursal, es posible que no exista"),
            @ApiResponse(responseCode = "500",description = "Error interno al procesar la eliminacion")
    })
    public ResponseEntity<?> eliminarSucursal(@PathVariable Integer id){
        logger.info("Solicitud recibida para eliminar sucursal con ID: {}",id);
        try{boolean res= sucursalService.borrarSucursal(id);

            if (res){
                logger.info("Intentando eliminar sucursal: {}",id);
                return  new ResponseEntity<>(HttpStatus.OK);
            }else{
                logger.warn("No se pudo eliminar la sucursal");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            logger.error("Error la eliminar la sucursal",e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar");

        }

    }
}
