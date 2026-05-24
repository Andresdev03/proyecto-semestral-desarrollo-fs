package org.example.producto.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class ProductoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private  Integer idProducto;
    @Column(name = "nombre_producto", nullable = false,length = 100)
    private  String nombreProducto;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "id_sucursal",nullable = false)
    private Integer idSucursal;
}
