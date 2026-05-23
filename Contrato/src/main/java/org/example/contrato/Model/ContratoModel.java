package org.example.contrato.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor

public class ContratoModel {
    @NotNull(message = "El contrato debe tener ID.")
    Integer idContrato;
    @NotBlank(message = "El contrato debe tener un tipo.")
    String tipoContrato;
    @NotBlank(message = "El contrato debe tener un cargo.")
    String cargoContrato;
    @NotNull(message = "El contrato debe tener fecha de inicio.")
    Date fecIniContrato;
    // nullable
    Date fecFinContrato;
    @NotNull(message = "El contrato debe tener un salario.")
    BigDecimal salarioContrato;
    @NotBlank(message = "El contrato debe tener un rut de empleado.")
    String runEmpleado;
    @NotNull(message = "El contrato debe tener un ID de Sucursal.")
    Integer idSucursal;

}
