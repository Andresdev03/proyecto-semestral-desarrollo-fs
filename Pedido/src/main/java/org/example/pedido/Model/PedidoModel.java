package org.example.pedido.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;

    @Column(name = "fec_pedido", nullable = false)
    private LocalDate fecPedido;

    @Column(name = "estado_pedido", nullable = false, length = 20)
    private String estadoPedido;

    @Column(name = "tipo_pedido", nullable = false, length = 20)
    private String tipoPedido;

    @Column(name = "num_mesa")
    private Integer numMesa;

    @Column(name = "run_empleado", nullable = false)
    private String runTrabajador;

    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;
}
