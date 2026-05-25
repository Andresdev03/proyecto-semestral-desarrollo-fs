package org.example.sucursal.Model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursal")
public class SucursalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Column(name = "nombre_sucursal",nullable = false,length = 100)
    private  String nombreSucursal;

    @Column(name = "direccion_sucursal",nullable = false,length = 255)
    private String direccion;

    @Column(name = "id_comuna",nullable = false)
    private Integer idComuna;

    @Column(name = "telefono_sucursal",nullable = false,unique = true,length = 12 )
    private  String telefonoSucursal;

    @Column(name = "fec_apertura_sucursal",nullable = false)
    private LocalDate fecAperturaSucursal;

    @Column(name = "email_sucursal",nullable = false,unique = true,length = 100)
    private String emailSucursal;

}
