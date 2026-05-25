package org.example.sucursal.Repository;

import org.example.sucursal.Model.SucursalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalModel ,Integer> {
    List<SucursalModel>findByIdComuna(Integer idComuna);
}
