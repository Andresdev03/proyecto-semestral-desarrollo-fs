package org.example.region.Model;

import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.AliasFor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Region")
public class RegionModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id_region")
    private Integer idRegion;
    @Column(name = "nombre_region", nullable = false, length = 100)
    private String nombreRegion;
}
