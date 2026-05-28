package org.example.boleta.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta", nullable = false)
    private Integer idBoleta;

    @NotNull(message = "Debe incluir una fecha de creación.")
    @Column(name = "fec_creacion_boleta", nullable = false)
    private LocalDate fecCreacionBoleta;

    @NotNull(message = "Debe incluir un total en la boleta.")
    @Positive(message = "El valor total de la boleta debe ser mayor a 0.")
    @Column(name = "valor_total_boleta", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotalBoleta;

    @NotBlank(message = "Debe incluir un método de pago.")
    @Size(max = 50, message = "El método de pago no puede superar los 50 caracteres.")
    @Column(name = "metodo_pago_boleta", nullable = false, length = 50)
    private String metodoPagoBoleta;

    @NotNull(message = "Debe incluir el id del pedido.")
    @Column(name = "id_pedido", nullable = false, unique = true)
    private Integer idPedido;
}