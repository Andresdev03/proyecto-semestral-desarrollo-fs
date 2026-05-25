package org.example.ingrediente.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "ingrediente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Integer id;

    @Column(name = "nombre_ingrediente", nullable = false, length = 100)
    private String nombreIngrediente;

    @Column(name = "descripcion_ingrediente", length = 300)
    private String descripcionIngrediente;

    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida;

    @Column(name = "stock_ingrediente", nullable = false)
    private BigDecimal stockIngrediente;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "id_proveedor", nullable = false)
    private Integer idProveedor;
}
