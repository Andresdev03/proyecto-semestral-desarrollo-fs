package org.example.empleado.Model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity



@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Debe incluir run.")
    @Size(min = 8, max = 9)
    @Schema(description = "Run del empleado, sin DV.", example = "20680568")
    @Column(name = "run_empleado")
    private String runEmpleado;

    @NotBlank(message = "Debe incluir número verificador.")
    @Size(min = 1, max = 1)
    @Schema(description = "Dígito verificador del RUN.", example = "k")
    @Column(name = "dv_run_empleado")
    private String dvRunEmpleado;

    @NotBlank(message = "Debe incluir un correo.")
    @Email(message = "Debe cumplir con el formato de un correo.")
    @Size(max = 100)
    @Schema(description = "Correo del empleado.", example = "sebastian.mautz@gmail.com")
    @Column(name = "correo_empleado", nullable = false, unique = true, length = 100)
    private String correoEmpleado;

    @NotBlank(message = "Debe incluir un teléfono.")
    @Size(max = 12)
    @Schema(description = "Número de contacto del empleado.", example = "+56923567809")
    @Column(name = "telefono_empleado", unique = true, length = 12)
    private String telefonoEmpleado;

    @NotBlank(message = "Debe incluir un nombre.")
    @Size(max = 50)
    @Schema(description = "Primer nombre del empleado.", example = "Sebastian")
    @Column(name = "pnombre_empleado")
    private String pnombreEmpleado;

    @Size(max = 50)
    @Schema(description = "Segundo nombre del empleado.", example = "Ignacio")
    @Column(name = "snombre_empleado")
    private String snombreEmpleado;

    @NotBlank(message = "Debe incluir un apellido paterno.")
    @Size(max = 50)
    @Schema(description = "Apellido paterno del empleado.", example = "Mautz")
    @Column(name = "appaterno_empleado")
    private String appaternoEmpleado;

    @Size(max = 50)
    @Schema(description = "Apellido materno del empleado.", example = "Parham")
    @Column(name = "apmaterno_empleado")
    private String apmaternoEmpleado;

    @NotNull(message = "Debe incluir una fecha de nacimiento.")
    @Schema(description = "Fecha de nacimiento del empleado.", example = "2001-09-13")
    @Column(name = "fecha_nac_empleado")
    private LocalDate fechaNacEmpleado;


}
