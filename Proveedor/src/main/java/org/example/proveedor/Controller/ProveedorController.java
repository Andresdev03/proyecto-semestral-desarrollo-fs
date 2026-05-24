package org.example.proveedor.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.proveedor.Model.ProveedorModel;
import org.example.proveedor.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorModel> findAll() {
        return proveedorService.getTodosProveedores();
    }

    @PostMapping
    public ResponseEntity<ProveedorModel> save(@Valid @RequestBody ProveedorModel proveedor) {
        ProveedorModel proveedorModel = proveedorService.createProveedor(proveedor);
        if (proveedorModel != null) {
            return ResponseEntity.ok(proveedorModel);
        }  else {
            return ResponseEntity.notFound().build();
        }
    }
}
