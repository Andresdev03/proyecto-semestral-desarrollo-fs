package org.example.proveedor.Repository;

import org.example.proveedor.Model.ProveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorModel, Integer> {
}
