package org.example.contrato.Repository;


import org.example.contrato.Model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;






public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    List<Contrato> findByRunEmpleado(String runEmpleado);
    List<Contrato> findBySalarioContrato (BigDecimal salarioContrato);


}
