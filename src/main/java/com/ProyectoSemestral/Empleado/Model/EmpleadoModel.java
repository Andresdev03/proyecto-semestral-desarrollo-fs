package com.ProyectoSemestral.Empleado.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor



public class EmpleadoModel {
    @NotBlank(message = "El empleado debe tener run.")
    String run_empleado;
    @NotBlank(message = "El empleado debe tener numero verificador.")
    char dv_runEmpleado;
    @NotBlank(message = "El empleado debe tener correo.")
    String correoEmpleado;
    @NotBlank(message = "El empleado debe tener celular.")
    String telefonoEmpleado;
    @NotBlank(message = "El empleado debe tener primer nombre.")
    String pNombreEmpleado;
    // puede ser nulo
    String sNombreEmpleado;
    @NotBlank(message = "El empleado debe tener apellido paterno.")
    String apPaternoEmpleado;
    // puede ser nulo
    String apMaternoEmpleado;
    @NotBlank(message = "El empleado debe tener fecha de nacimiento.")
    Date fechaNacEmpleado;

}
