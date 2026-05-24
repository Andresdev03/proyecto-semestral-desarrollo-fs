package org.example.sucursal.Controller;

import org.example.sucursal.Model.SucursalModel;
import org.example.sucursal.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {
    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping
    public List<SucursalModel> listarSucursales(){
        return sucursalRepository.findAll();
    }

    @GetMapping("/comuna/{idComuna}")
    public List<SucursalModel>listarPorComuna(@PathVariable Integer idComuna){
        return  sucursalRepository.findByIdComuna(idComuna);
    }

    @PostMapping
    public ResponseEntity<SucursalModel>crearSucursal(@RequestBody SucursalModel sucursal){
        SucursalModel nuevaSucursal = sucursalRepository.save(sucursal);
        return  ResponseEntity.ok(nuevaSucursal);

    }
    @PutMapping("/{id}")
    public  ResponseEntity<SucursalModel> actualizarSucursal(@PathVariable Integer id,@RequestBody SucursalModel sucursalDetalles){
        return  sucursalRepository.findById(id)
                .map(sucursalExistente ->{
                    sucursalExistente.setNombreSucursal((sucursalDetalles.getNombreSucursal()));
                    sucursalExistente.setDireccion(sucursalDetalles.getDireccion());
                    sucursalExistente.setIdComuna(sucursalDetalles.getIdComuna());
                    SucursalModel sucursalActualizada = sucursalRepository.save(sucursalExistente);
                    return ResponseEntity.ok(sucursalActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id){
        return sucursalRepository.findById(id)
                .map(sucursal->{
                    sucursalRepository.delete(sucursal);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
