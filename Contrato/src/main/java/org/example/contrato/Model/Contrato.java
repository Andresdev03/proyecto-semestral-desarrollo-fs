package org.example.contrato.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table (name= "contrato")
public class Contrato {

    @NotNull(message = "El contrato debe tener ID.")
    @Id
    @Column(name = "id_contrato")
    private Integer idContrato;

    @NotBlank(message = "El contrato debe tener un tipo.")
    @Column(name = "tipo_contrato")
    private String tipoContrato;

    @NotBlank(message = "El contrato debe tener un cargo.")
    @Column(name = "cargo_contrato")
    private String cargoContrato;

    @NotNull(message = "El contrato debe tener fecha de inicio.")
    @Column(name = "fec_ini_contrato")
    private LocalDate fecIniContrato;

    // nullable
    @Column(name = "fec_fin_contrato")
    private LocalDate fecFinContrato;

    @NotNull(message = "El contrato debe tener un salario.")
    @Column(name = "salario_contrato")
    private BigDecimal salarioContrato;

    @NotBlank(message = "El contrato debe tener un rut de empleado.")
    @Column(name = "run_empleado")
    private String runEmpleado;

    @NotNull(message = "El contrato debe tener un ID de Sucursal.")
    @Column(name = "id_sucursal")
    private Integer idSucursal;

}
