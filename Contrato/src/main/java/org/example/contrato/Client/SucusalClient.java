package org.example.contrato.Client;


import org.example.contrato.DTO.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Sucursal")
public interface SucusalClient {

    @GetMapping("/api/sucursal/{idSucursal}")
    SucursalDTO getSucursal(@PathVariable Integer idsucursal);
}
