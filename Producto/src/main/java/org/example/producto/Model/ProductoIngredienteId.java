package org.example.producto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductoIngredienteId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(name = "id_ingrediente")
    private Long idIngrediente;
}
