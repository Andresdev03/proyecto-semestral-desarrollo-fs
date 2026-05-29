package org.example.producto.Repository;
import org.example.producto.Model.ProductoIngredienteModel;
import org.example.producto.Model.ProductoIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoIngredienteRepository extends JpaRepository<ProductoIngredienteModel,ProductoIngredienteId> {

}
