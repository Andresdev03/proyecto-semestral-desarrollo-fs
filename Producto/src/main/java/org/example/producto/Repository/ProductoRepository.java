package org.example.producto.Repository;

import org.example.producto.Model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {
}
