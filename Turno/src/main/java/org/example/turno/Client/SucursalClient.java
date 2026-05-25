package org.example.turno.Client;

import org.example.turno.DTO.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Sucursal")
public interface SucursalClient {


    @GetMapping("/api/sucursal/{idSucursal}")
    SucursalDTO getSucursal(@PathVariable Integer idsucursal);
}
