package org.example.region.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @GetMapping("/saludo")
    public Map<String, Object> saludo() {
        return Map.of(
                "microservicio", "region-service",
                "estado", "activo",
                "mensaje", "Microservicio Region funcionando correctamente"
        );
    }
}
