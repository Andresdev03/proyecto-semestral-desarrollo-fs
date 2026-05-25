package org.example.factura.Repository;

import org.example.factura.Model.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<FacturaModel, Integer> {

}
