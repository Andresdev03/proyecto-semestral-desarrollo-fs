package org.example.boleta.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @NotNull(message = "Debe incluir un ID.")
    @Schema(description = "ID de la boleta.", example = "1")
    @Column(name = "id_boleta")
    private Integer idBoleta;

    @NotNull(message = "Debe incluir una fecha de creacion")
    @Schema(description = "Fecha de creacion de la boleta.", example = "2026-06-11")
    @Column(name = "fec_creacion_boleta")
    private LocalDate fecCreacionBoleta;

    @NotNull(message = "Debe incluir un total en la boleta.")
    @Positive
    @Schema(description = "Valor total de la boleta.", example = "10300")
    @Column(name = "valor_total_boleta")
    private BigDecimal valorTotalBoleta;

    @NotBlank(message = "Debe incluir un método de pago")
    @Schema(description = "Describe el método de pago utilizado por el cliente en esta boleta.", example = "Efectivo")
    @Column(name = "metodo_pago_boleta")
    private String metodoPagoBoleta;

    @NotNull
    @Schema(description = "ID del pedido asociado a la boleta.", example = "1234")
    @Column(name = "id_pedido")
    private Integer idPedido;



}
