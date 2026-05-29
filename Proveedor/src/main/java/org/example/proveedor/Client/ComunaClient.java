package org.example.proveedor.Client;

import org.example.proveedor.DTO.ComunaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Comuna")
public interface ComunaClient {
    @GetMapping("api/comuna/{id}")
    ComunaDTO findById(@PathVariable Integer id);
}
