package org.example.turno.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
    @NotNull
    @Column(name = "id_turno")
    private Integer idTurno;

    @NotNull(message = "Debe incluir una fecha de turno.")
    @Column(name = "fec_turno")
    private LocalDate fecTurno;

    @NotBlank(message = "Debe incluir una hora de inicio.")
    @Size(min = 5, max = 5)
    @Column(name = "hora_ini_turno")
    private String horaIniTurno;

    @NotBlank(message = "Debe incluir una hora de final.")
    @Size(min = 5, max = 5)
    @Column(name = "hora_fin_turno")
    private String horaFinTurno;

    @NotBlank(message = "Debe tener un tipo de turno.")
    @Pattern(regexp = "Mañana|Tarde|Noche")
    @Column(name = "tipo_turno")
    private String tipoTurno;

    @NotBlank
    @Size(min = 7, max = 8)
    @Column(name = "run_empleado")
    private String runEmpleado;

    @NotNull
    @Positive
    @Column(name = "id_sucursal")
    private Integer idSucursal;



}
