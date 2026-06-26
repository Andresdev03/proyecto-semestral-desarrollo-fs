package org.example.proveedor.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.proveedor.Model.ProveedorModel;
import org.example.proveedor.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
@Tag(name = "API Proveedor", description = "API para la gestión de proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores",
            description = "Endpoint para consultar todos los proveedores registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornados todos los proveedores")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no hay proveedores registrados")
    public ResponseEntity<List<ProveedorModel>> findAll() {
        List<ProveedorModel> proveedores = proveedorService.getTodosProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor según ID",
            description = "Endpoint para obtener un proveedor específico mediante su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornado proveedor")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no se ha encontrado el recurso")
    public ResponseEntity<ProveedorModel> findById(@PathVariable Integer id) {
        ProveedorModel proveedor =  proveedorService.getProveedorById(id);
        if(proveedor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo proveedor",
            description = "Endpoint para generar un nuevo proveedor en la BBDD")
    @ApiResponse(responseCode = "200", description = "Registro exitoso, nuevo proveedor agregado")
    @ApiResponse(responseCode = "404", description = "Registro fallido, recurso no encontrado o datos inválidos")
    public ResponseEntity<ProveedorModel> save(@Valid @RequestBody ProveedorModel proveedor) {
        ProveedorModel proveedorModel = proveedorService.createProveedor(proveedor);
        if (proveedorModel != null) {
            return ResponseEntity.ok(proveedorModel);
        }  else {
            return ResponseEntity.notFound().build();
        }
    }
}
