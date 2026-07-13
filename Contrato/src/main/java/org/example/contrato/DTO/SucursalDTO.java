package org.example.contrato.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SucursalDTO {
    private Integer idSucursal;

    private String nombreSucursal;

    private String direccion;

    private Integer idComuna;

    private LocalDate fecAperturaSucursal;

    private String emailSucursal;
}
