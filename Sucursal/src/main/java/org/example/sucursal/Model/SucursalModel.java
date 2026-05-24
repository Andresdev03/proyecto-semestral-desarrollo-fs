package org.example.sucursal.Model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "Sucursal",nullable = false,length = 100)
    private  String nombreSucursal;

    @Column(name = "direccion",nullable = false,length = 255)
    private String direccion;

    @Column(name = "id_comuna",nullable = false)
    private Integer idComuna;

}
