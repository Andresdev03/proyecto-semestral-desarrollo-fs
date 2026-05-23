package org.example.comuna.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Comuna")
public class ComunaModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idComuna")
    private Integer idComuna;
    @Column(name = "id_region", nullable = false)
    private Integer idRegion;
    @Column(name = "nombre_comuna",nullable = false, length = 100)
    private String nombreComuna;
}
