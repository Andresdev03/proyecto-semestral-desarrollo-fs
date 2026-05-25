package org.example.ingrediente.Repository;

import org.example.ingrediente.Model.IngredienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<IngredienteModel, Integer> {

}
