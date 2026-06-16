package org.example.turno.Model;

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

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del turno.", example = "1")
    @Column(name = "id_turno", nullable = false)
    private Integer idTurno;

    @NotNull(message = "Debe incluir una fecha de turno.")
    @Schema(description = "Fecha del turno.", example = "2026-06-16")
    @Column(name = "fec_turno", nullable = false)
    private LocalDate fecTurno;

    @NotBlank(message = "Debe incluir una hora de inicio.")
    @Size(min = 5, max = 5, message = "La hora de inicio debe tener formato HH:mm.")
    @Schema(description = "Hora de inicio del turno.", example = "13:55")
    @Column(name = "hora_ini_turno", nullable = false, length = 5)
    private String horaIniTurno;

    @NotBlank(message = "Debe incluir una hora de fin.")
    @Size(min = 5, max = 5, message = "La hora de fin debe tener formato HH:mm.")
    @Schema(description = "Hora de término del turno.", example = "21:55")
    @Column(name = "hora_fin_turno", nullable = false, length = 5)
    private String horaFinTurno;

    @NotBlank(message = "Debe incluir un tipo de turno.")
    @Pattern(regexp = "Mañana|Tarde|Noche", message = "El tipo de turno debe ser Mañana, Tarde o Noche.")
    @Schema(description = "Tipo de jornada del turno.", example = "Tarde")
    @Column(name = "tipo_turno", nullable = false, length = 20)
    private String tipoTurno;

    @NotBlank(message = "Debe incluir el RUN del empleado.")
    @Size(min = 7, max = 8, message = "El RUN del empleado debe tener entre 7 y 8 caracteres.")
    @Schema(description = "Run del empleado, sin DV.", example = "20680568")
    @Column(name = "run_empleado", nullable = false, length = 8)
    private String runEmpleado;

    @NotNull(message = "Debe incluir el id de la sucursal.")
    @Positive(message = "El id de la sucursal debe ser mayor a 0.")
    @Schema(description = "ID de la sucursal.", example = "1")
    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;
}
