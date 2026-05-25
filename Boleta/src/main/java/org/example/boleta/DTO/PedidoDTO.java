package org.example.boleta.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoDTO {

    private Integer id;

    private LocalDate fecPedido;

    private String estadoPedido;

    private String tipoPedido;

    private Integer numMesa;

    private String runEmpleado;

    private Integer idSurcusal;


}
