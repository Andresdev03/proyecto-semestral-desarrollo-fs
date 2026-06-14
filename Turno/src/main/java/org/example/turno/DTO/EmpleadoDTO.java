package org.example.turno.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDTO {


    private String runEmpleado;

    private String dvRunEmpleado;

    private String correoEmpleado;

    private String telefonoEmpleado;

    private String pnombreEmpleado;

    private String snombreEmpleado;

    private String appaternoEmpleado;

    private String apmaternoEmpleado;

    private LocalDate fechaNacEmpleado;

}
