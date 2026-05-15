package com.ProyectoSemestral.Contrato.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ContratoModel {
    @NotNull
    int id_contrato;
    @NotBlank(message = "El contrato debe tener un tipo.")
    String tipoContrato;
    @NotBlank(message = "El contrato debe tener un cargo.")
    String cargoContrato;
    @NotNull(message = "El contrato debe tener fecha de inicio.")
    Date fecIniContrato;
    // nullable
    Date fecFinContrato;
    int salarioContrato;
    // run emp
    // id sucursal


}
