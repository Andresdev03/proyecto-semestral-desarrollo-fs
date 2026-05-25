package org.example.turno.Service;

import org.example.turno.Model.Turno;
import org.example.turno.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;


    public Turno registrar(Turno turno){
        return turnoRepository.save(turno);
    }

    public List<Turno> listarTodos(){
        return turnoRepository.findAll();
    }

    public List<Turno> buscarPorFecha(LocalDate fecIniTurno){
        return turnoRepository.findByFecTurno(fecIniTurno);
    }

    public List<Turno> buscarPorTipo(String tipoTurno){
        return turnoRepository.findByTipoTurno(tipoTurno);
    }

    public Turno buscarPorId(Integer idTurno){
        return turnoRepository.findById(idTurno).orElse(null);
    }

    public Turno modificarPorId(Integer idTurno, Turno turnoModificado){
        Turno turnoOriginal = buscarPorId(idTurno);

        if (turnoOriginal == null){
            return null;
        }

        turnoOriginal.setFecTurno(turnoModificado.getFecTurno());
        turnoOriginal.setTipoTurno(turnoModificado.getTipoTurno());
        turnoOriginal.setHoraIniTurno(turnoModificado.getHoraIniTurno());
        turnoOriginal.setHoraFinTurno(turnoModificado.getHoraFinTurno());
        return turnoRepository.save(turnoOriginal);
    }

    public boolean eliminarPorId(Integer idTurno){
        Turno turno = buscarPorId(idTurno);

        if (turno == null){
            return false;
        }

        turnoRepository.deleteById(idTurno);
        return true;
    }



}
