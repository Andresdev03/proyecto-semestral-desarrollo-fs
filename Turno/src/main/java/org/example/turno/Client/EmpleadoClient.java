package org.example.turno.Client;

import org.example.turno.DTO.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Empleado")
public interface EmpleadoClient {

    @GetMapping("/api/v1/empleados/{run}")
    EmpleadoDTO getEmpleado(@PathVariable String run);
}
