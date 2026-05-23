package org.example.empleado.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "run_empleado")
    private String runEmpleado;
    @Column(name = "dv_runEmpleado")
    private String dvRunEmpleado;
    @Column(name = "correo_empleado")
    private String correoEmpleado;
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;
    @Column(name = "pnombre_empleado")
    private String pnombreEmpleado;
    @Column(name = "snombre_empleado")
    private String snombreEmpleado;
    @Column(name = "appaterno_empleado")
    private String appaternoEmpleado;
    @Column(name = "apmaterno_empleado")
    private String apmaternoEmpleado;
    @Column(name = "fecha_nac_empleado")
    private LocalDate fechaNacEmpleado;


}
