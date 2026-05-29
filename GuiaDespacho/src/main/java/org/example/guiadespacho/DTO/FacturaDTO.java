package org.example.guiadespacho.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FacturaDTO {
    private Integer idFactura;
    private LocalDate fedEmision;
    private String rutReceptor;
    private String razonSocial;
    private String giroReceptor;
    private BigDecimal montoNeto;
    private BigDecimal montoIva;
    private BigDecimal montoTotal;
    private Integer idPedido;
    private Integer idProveedor;
}
