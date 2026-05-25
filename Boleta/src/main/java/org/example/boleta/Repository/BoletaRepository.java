package org.example.boleta.Repository;

import org.example.boleta.Model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

    List<Boleta> findByFecCreacionBoleta (LocalDate fecCreacionBoleta);
    List<Boleta> findByMetodoPagoBoleta (String metodoPagoBoleta);
    List<Boleta> findByPedidoId (Integer idPedido);



}
