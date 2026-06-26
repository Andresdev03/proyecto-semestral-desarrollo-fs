package org.example.proveedor.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private Integer id;
    @NotBlank
    @Size(min=9)
    @Column(name="rut_proveedor", nullable=false, unique=true, length=9)
    private String rutProveedor;

    @NotNull
    @Column(name="dv_proveedor", nullable=false, length=1, columnDefinition = "CHAR(1)")
    private Character dvProveedor;

    @Column(name="email_proveedor", nullable = false, length=50, unique=true)
    private String emailProveedor;

    @Column(name = "nombre_proveedor", nullable = false, length = 100)
    private String nombreProveedor;

    @Column(name = "direccion_proveedor", length = 50)
    private String direccionProveedor;

    @Column(name = "telefono_proveedor", length = 50)
    private String telefonoProveedor;

    @Column(name = "id_region", nullable = false)
    private Integer idRegion;

    @Column(name = "id_comuna", nullable = false)
    private Integer idComuna;
}
