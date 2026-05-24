package org.example.turno.Repository;

import org.example.turno.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    List<Turno> findByFecTurno(LocalDate fecTurno);

    List<Turno> findByTipoTurno(String tipoTurno);



}
