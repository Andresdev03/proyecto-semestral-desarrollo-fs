package org.example.sucursal.Controller;

import org.example.sucursal.Client.ComunaClient;
import org.example.sucursal.DTO.ComunaDTO;
import org.example.sucursal.Model.SucursalModel;
import org.example.sucursal.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
@CrossOrigin(origins = "*")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private ComunaClient comunaClient;


    @GetMapping("/comuna-remota/{id}")
    public ResponseEntity<?> obtenerComunaRemota(@PathVariable Integer id){
        try{
           ComunaDTO comuna= comunaClient.obtenerComunaPorId(id);
            return ResponseEntity.ok(comuna);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al conectar con el microservicio: " + e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<List<SucursalModel>> getAllSucursales(){
        List<SucursalModel> listado= sucursalService.listarSucursales();
        if (listado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(listado,HttpStatus.OK);
        }
    }


    @GetMapping("/comuna/{idComuna}")
    public ResponseEntity<List<SucursalModel>> listarPorComuna(@PathVariable Integer idComuna){
        List<SucursalModel> listado = sucursalService.listarPorComuna(idComuna);
        if (listado.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return  new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<SucursalModel>crearSucursal(@RequestBody SucursalModel sucursal){
        SucursalModel nuevaSucursal =sucursalService.agregarSucursal(sucursal);
        if (nuevaSucursal !=null){
            return  new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
        }else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public  ResponseEntity<SucursalModel> actualizarSucursal(@PathVariable Integer id,@RequestBody SucursalModel nuevo){
        SucursalModel actualizado= sucursalService.actualizarSucursal(id, nuevo);
        if(actualizado!=null){
            return  new ResponseEntity<>(actualizado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id){
       boolean res= sucursalService.borrarSucursal(id);
       if (res){
           return  new ResponseEntity<>(HttpStatus.OK);
       }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
}
