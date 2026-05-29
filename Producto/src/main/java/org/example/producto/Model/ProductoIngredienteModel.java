package org.example.producto.Model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto_ingrediente")
public class ProductoIngredienteModel {
    @EmbeddedId
    private ProductoIngredienteId id;
    @Column(nullable = false,precision=10,scale=2)
    private BigDecimal cantidad;

}
