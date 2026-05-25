package org.example.comuna.Client;

import org.example.comuna.DTO.RegionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-region", url = "http://localhost:8081/api/region")
public interface RegionClient {
    @GetMapping("/{id}")
    RegionDTO obtenerRegionPorId(@PathVariable("id")Integer id);
}
