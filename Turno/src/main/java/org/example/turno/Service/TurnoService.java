package org.example.turno.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.turno.Client.EmpleadoClient;
import org.example.turno.Client.SucursalClient;
import org.example.turno.Model.Turno;
import org.example.turno.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private EmpleadoClient empleadoClient;

    @Autowired
    private SucursalClient sucursalClient;

    public Turno registrar(Turno turno){
        try{
            log.info("Intentando registrar turno para empleado {} y sucursal {}", turno.getRunEmpleado(), turno.getIdSucursal());

            empleadoClient.getEmpleado(turno.getRunEmpleado());
            sucursalClient.getSucursal(turno.getIdSucursal());

            Turno turnoGuardado = turnoRepository.save(turno);
            log.info("Turno registrado correctamente con id {}", turnoGuardado.getIdTurno());
            return turnoGuardado;

        } catch (Exception e){
            log.error("Error al registrar turno para empleado {} y sucursal {}", turno.getRunEmpleado(), turno.getIdSucursal(), e);
            return null;
        }
    }

    public List<Turno> listarTodos(){
        try{
            log.info("Listando todos los turnos");
            List<Turno> lista = turnoRepository.findAll();
            log.info("Se encontraron {} turnos", lista.size());
            return lista;

        } catch (Exception e){
            log.error("Error al listar turnos", e);
            return List.of();
        }
    }

    public List<Turno> buscarPorFecha(LocalDate fecTurno){
        try{
            log.info("Buscando turnos por fecha {}", fecTurno);
            List<Turno> lista = turnoRepository.findByFecTurno(fecTurno);
            log.info("Se encontraron {} turnos para la fecha {}", lista.size(), fecTurno);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar turnos por fecha {}", fecTurno, e);
            return List.of();
        }
    }

    public List<Turno> buscarPorTipo(String tipoTurno){
        try{
            log.info("Buscando turnos por tipo {}", tipoTurno);
            List<Turno> lista = turnoRepository.findByTipoTurno(tipoTurno);
            log.info("Se encontraron {} turnos de tipo {}", lista.size(), tipoTurno);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar turnos por tipo {}", tipoTurno, e);
            return List.of();
        }
    }

    public Turno buscarPorId(Integer idTurno){
        try{
            log.info("Buscando turno por id {}", idTurno);
            Turno turno = turnoRepository.findById(idTurno).orElse(null);

            if (turno == null){
                log.warn("No se encontró turno con id {}", idTurno);
            } else {
                log.info("Turno encontrado con id {}", idTurno);
            }

            return turno;

        } catch (Exception e){
            log.error("Error al buscar turno por id {}", idTurno, e);
            return null;
        }
    }

    public List<Turno> buscarPorRunEmpleado(String runEmpleado){
        try{
            log.info("Buscando turnos por runEmpleado {}", runEmpleado);

            empleadoClient.getEmpleado(runEmpleado);

            List<Turno> lista = turnoRepository.findByRunEmpleado(runEmpleado);
            log.info("Se encontraron {} turnos para el empleado {}", lista.size(), runEmpleado);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar turnos por runEmpleado {}", runEmpleado, e);
            return List.of();
        }
    }

    public List<Turno> buscarPorIdSucursal(Integer idSucursal){
        try{
            log.info("Buscando turnos por idSucursal {}", idSucursal);

            sucursalClient.getSucursal(idSucursal);

            List<Turno> lista = turnoRepository.findByIdSucursal(idSucursal);
            log.info("Se encontraron {} turnos para la sucursal {}", lista.size(), idSucursal);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar turnos por idSucursal {}", idSucursal, e);
            return List.of();
        }
    }

    public Turno modificarPorId(Integer idTurno, Turno turnoModificado){
        try{
            log.info("Intentando modificar turno con id {}", idTurno);

            Turno turnoOriginal = buscarPorId(idTurno);
            if (turnoOriginal == null){
                log.warn("No se puede modificar. No existe turno con id {}", idTurno);
                return null;
            }

            empleadoClient.getEmpleado(turnoModificado.getRunEmpleado());
            sucursalClient.getSucursal(turnoModificado.getIdSucursal());

            turnoOriginal.setFecTurno(turnoModificado.getFecTurno());
            turnoOriginal.setHoraIniTurno(turnoModificado.getHoraIniTurno());
            turnoOriginal.setHoraFinTurno(turnoModificado.getHoraFinTurno());
            turnoOriginal.setTipoTurno(turnoModificado.getTipoTurno());
            turnoOriginal.setRunEmpleado(turnoModificado.getRunEmpleado());
            turnoOriginal.setIdSucursal(turnoModificado.getIdSucursal());

            Turno turnoActualizado = turnoRepository.save(turnoOriginal);
            log.info("Turno modificado correctamente con id {}", idTurno);
            return turnoActualizado;

        } catch (Exception e){
            log.error("Error al modificar turno con id {}", idTurno, e);
            return null;
        }
    }

    public boolean eliminarPorId(Integer idTurno){
        try{
            log.info("Intentando eliminar turno con id {}", idTurno);

            Turno turno = buscarPorId(idTurno);
            if (turno == null){
                log.warn("No se puede eliminar. No existe turno con id {}", idTurno);
                return false;
            }

            turnoRepository.deleteById(idTurno);
            log.info("Turno eliminado correctamente con id {}", idTurno);
            return true;

        } catch (Exception e){
            log.error("Error al eliminar turno con id {}", idTurno, e);
            return false;
        }
    }
}
