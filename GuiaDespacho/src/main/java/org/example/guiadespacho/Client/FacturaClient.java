package org.example.guiadespacho.Client;

import org.example.guiadespacho.DTO.FacturaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Factura")
public interface FacturaClient {
    @GetMapping("/api/factura/{id}")
    FacturaDTO getFacturaById(@PathVariable Integer id);
}
