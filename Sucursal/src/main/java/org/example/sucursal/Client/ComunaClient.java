package org.example.sucursal.Client;

import org.example.sucursal.DTO.ComunaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-comuna",url = "http://localhost:8082/api/comunas")
public interface ComunaClient {
    @GetMapping("")
    List<ComunaDTO> obtenerTodasLasComunas();

    @GetMapping("/{id}")
    ComunaDTO obtenerComunaPorId(@PathVariable("id") Integer id);
}
