package org.example.contrato.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del contrato.", example = "1")
    @Column(name = "id_contrato", nullable = false)
    private Integer idContrato;

    @NotBlank(message = "El contrato debe tener un tipo.")
    @Pattern(
            regexp = "Indefinido|Plazo Fijo|Por Obra|Honorarios",
            message = "El tipo de contrato debe ser Indefinido, Plazo Fijo, Por Obra o Honorarios."
    )
    @Size(max = 50, message = "El tipo de contrato no puede superar los 50 caracteres.")
    @Schema(description = "Tipo de contrato", example = "Indefinido")
    @Column(name = "tipo_contrato", nullable = false, length = 50)
    private String tipoContrato;

    @NotBlank(message = "El contrato debe tener un cargo.")
    @Size(max = 50, message = "El cargo no puede superar los 50 caracteres.")
    @Schema(description = "El cargo del empleado que figura en el contrato.", example = "Cajero multifuncional.")
    @Column(name = "cargo_contrato", nullable = false, length = 50)
    private String cargoContrato;

    @NotNull(message = "El contrato debe tener fecha de inicio.")
    @Schema(description = "Fecha de inicio del contrato.", example = "2026-06-15")
    @Column(name = "fec_ini_contrato", nullable = false)
    private LocalDate fecIniContrato;

    @Schema(description = "Fecha de fin del contrato.", example = "2027-06-15")
    @Column(name = "fec_fin_contrato")
    private LocalDate fecFinContrato;

    @NotNull(message = "El contrato debe tener un salario.")
    @Positive(message = "El salario debe ser mayor a 0.")
    @Schema(description = "Salario del empleado asociado al contrato.", example = "555000")
    @Column(name = "salario_contrato", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioContrato;

    @NotBlank(message = "El contrato debe tener un RUN de empleado.")
    @Size(min = 7, max = 8, message = "El RUN del empleado debe tener entre 7 y 8 caracteres.")
    @Schema(description = "Run del empleado asociado al contrato.", example = "20680568")
    @Column(name = "run_empleado", nullable = false, length = 8)
    private String runEmpleado;

    @NotNull(message = "El contrato debe tener un ID de sucursal.")
    @Positive(message = "El ID de sucursal debe ser mayor a 0.")
    @Schema(description = "ID de la sucursal asociada al contrato.", example = "1")
    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;
}
