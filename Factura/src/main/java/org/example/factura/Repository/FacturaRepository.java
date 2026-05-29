package org.example.factura.Repository;

import org.example.factura.Model.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaModel, Integer> {

    List<FacturaModel> findByRutReceptor(String rutReceptor);
}
