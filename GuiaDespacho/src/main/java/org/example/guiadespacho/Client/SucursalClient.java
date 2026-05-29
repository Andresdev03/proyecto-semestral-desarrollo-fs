package org.example.guiadespacho.Client;

import org.example.guiadespacho.DTO.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "Sucursal")
public interface SucursalClient {
    @GetMapping("api/sucursal")
    List<SucursalDTO> getSucursales();
}
