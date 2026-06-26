package org.example.factura.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.factura.Model.FacturaModel;
import org.example.factura.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
@Tag(name = "API Factura", description = "API para la gestión de facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener factura según ID",
            description = "Endpoint para obtener una factura específica mediante su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornada factura")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no se ha encontrado el recurso")
    public ResponseEntity<FacturaModel> getFactura(@PathVariable Integer id){
        FacturaModel factura =  facturaService.getFactura(id);
        if (factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las facturas",
            description = "Endpoint para consultar todas las facturas registradas")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornadas todas las facturas")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no hay facturas registradas")
    public ResponseEntity<List<FacturaModel>> getAllFacturas(){
        List<FacturaModel> listaFacturas = facturaService.getFacturas();
        if (listaFacturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaFacturas);
    }

    @PostMapping
    @Operation(summary = "Agregar una nueva factura",
            description = "Endpoint para generar una nueva factura en la BBDD")
    @ApiResponse(responseCode = "200", description = "Registro exitoso, nueva factura agregada")
    @ApiResponse(responseCode = "404", description = "Registro fallido, recurso no encontrado o datos inválidos")
    public ResponseEntity<FacturaModel> addFactura(@RequestBody FacturaModel factura){
        FacturaModel facturaModel = facturaService.addFactura(factura);
        if (facturaModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una factura por ID",
            description = "Endpoint para eliminar una factura de la BBDD mediante su ID")
    @ApiResponse(responseCode = "200", description = "Eliminación exitosa")
    @ApiResponse(responseCode = "404", description = "Eliminación fallida, no se ha encontrado el recurso")
    public ResponseEntity<FacturaModel> deleteFactura(@PathVariable Integer id){
        boolean cliente = facturaService.deleteFactura(id);
        if (cliente){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}