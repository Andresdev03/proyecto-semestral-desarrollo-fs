package org.example.factura.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer id;

    @Column(name = "fec_emision_factura", nullable = false)
    private LocalDate fecEmision;

    @Column(name = "rut_receptor", nullable = false, length = 12)
    private String rutReceptor;

    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "giro_receptor", length = 100)
    private String giroReceptor;

    @Column(name = "monto_neto", nullable = false)
    private BigDecimal montoNeto;

    @Column(name = "monto_iva", nullable = false)
    private BigDecimal montoIva;

    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_proveedor")
    private Integer idProveedor;
}
