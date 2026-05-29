package org.example.guiadespacho.Client;

import org.example.guiadespacho.DTO.ProveedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Proveedor")
public interface ProveedorClient {
    @GetMapping("/api/proveedor/{id}")
    ProveedorDTO getProveedorById(@PathVariable Integer id);
}
