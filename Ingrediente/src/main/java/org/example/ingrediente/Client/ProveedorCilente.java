package org.example.ingrediente.Client;

import org.example.ingrediente.DTO.ProveedorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProveedorCilente {
    @GetMapping("api/proveedor/{id}")
    ProveedorDTO getProveedorById(@PathVariable Integer id);
}
