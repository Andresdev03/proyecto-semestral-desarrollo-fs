package org.example.empleado.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @NotBlank(message = "Debe incluir run.")
    @Size(min = 7, max = 8)
    @Column(name = "run_empleado")
    private String runEmpleado;

    @NotBlank(message = "Debe incluir número verificador.")
    @Size(min = 1, max = 1)
    @Column(name = "dv_run_empleado")
    private String dvRunEmpleado;

    @NotBlank(message = "Debe incluir un correo.")
    @Email(message = "Debe cumplir con el formato de un correo.")
    @Size(max = 100)
    @Column(name = "correo_empleado")
    private String correoEmpleado;

    @NotBlank(message = "Debe incluir un teléfono.")
    @Size(max = 12)
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;

    @NotBlank(message = "Debe incluir un nombre.")
    @Size(max = 50)
    @Column(name = "pnombre_empleado")
    private String pnombreEmpleado;

    @Size(max = 50)
    @Column(name = "snombre_empleado")
    private String snombreEmpleado;

    @NotBlank(message = "Debe incluir un apellido paterno.")
    @Size(max = 50)
    @Column(name = "appaterno_empleado")
    private String appaternoEmpleado;

    @Size(max = 50)
    @Column(name = "apmaterno_empleado")
    private String apmaternoEmpleado;

    @NotNull(message = "Debe incluir una fecha de nacimiento.")
    @Column(name = "fecha_nac_empleado")
    private LocalDate fechaNacEmpleado;


}
