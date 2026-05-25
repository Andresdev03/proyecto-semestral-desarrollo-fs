package org.example.guiadespacho.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "guia_despacho")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiaDespachoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guia")
    private Integer id;

    @Column(name = "fec_emision_guia")
    private LocalDate fecEmisionGuia;

    @Column(name = "descripcion_guia")
    private String descripcionGuia;

    @Column(name = "estado_guia", nullable = false)
    private String estadoGuia;

    @Column(name = "id_proveedor", nullable = false)
    private Integer idProveedor;

    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;

    @Column(name = "id_factura")
    private Integer idFactura;
}
