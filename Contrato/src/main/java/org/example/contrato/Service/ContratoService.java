package org.example.contrato.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.contrato.Client.EmpleadoClient;
import org.example.contrato.Client.SucusalClient;
import org.example.contrato.Model.Contrato;
import org.example.contrato.Repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private EmpleadoClient empleadoClient;

    @Autowired
    private SucusalClient sucusalClient;

    public Contrato registrar(Contrato contrato){
        try{
            log.info("Intentando registrar contrato para empleado {} y sucursal {}", contrato.getRunEmpleado(), contrato.getIdSucursal());

            empleadoClient.getEmpleado(contrato.getRunEmpleado());
            sucusalClient.getSucursal(contrato.getIdSucursal());

            Contrato contratoGuardado = contratoRepository.save(contrato);
            log.info("Contrato registrado correctamente con id {}", contratoGuardado.getIdContrato());
            return contratoGuardado;

        } catch (Exception e){
            log.error("Error al registrar contrato para empleado {} y sucursal {}", contrato.getRunEmpleado(), contrato.getIdSucursal(), e);
            return null;
        }
    }

    public List<Contrato> listarTodos(){
        try{
            log.info("Listando todos los contratos");
            List<Contrato> lista = contratoRepository.findAll();
            log.info("Se encontraron {} contratos", lista.size());
            return lista;

        } catch (Exception e){
            log.error("Error al listar contratos", e);
            return List.of();
        }
    }

    public List<Contrato> buscarPorRunEmpleado(String runEmpleado){
        try{
            log.info("Buscando contratos por runEmpleado {}", runEmpleado);

            empleadoClient.getEmpleado(runEmpleado);

            List<Contrato> lista = contratoRepository.findByRunEmpleado(runEmpleado);
            log.info("Se encontraron {} contratos para el empleado {}", lista.size(), runEmpleado);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar contratos por runEmpleado {}", runEmpleado, e);
            return List.of();
        }
    }

    public List<Contrato> buscarPorIdSucursal(Integer idSucursal){
        try{
            log.info("Buscando contratos por idSucursal {}", idSucursal);

            sucusalClient.getSucursal(idSucursal);

            List<Contrato> lista = contratoRepository.findByIdSucursal(idSucursal);
            log.info("Se encontraron {} contratos para la sucursal {}", lista.size(), idSucursal);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar contratos por idSucursal {}", idSucursal, e);
            return List.of();
        }
    }

    public List<Contrato> buscarPorSalario(BigDecimal salario){
        try{
            log.info("Buscando contratos por salario {}", salario);
            List<Contrato> lista = contratoRepository.findBySalarioContrato(salario);
            log.info("Se encontraron {} contratos con salario {}", lista.size(), salario);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar contratos por salario {}", salario, e);
            return List.of();
        }
    }

    public Contrato buscarPorId(Integer idContrato){
        try{
            log.info("Buscando contrato por id {}", idContrato);
            Contrato contrato = contratoRepository.findById(idContrato).orElse(null);

            if (contrato == null){
                log.warn("No se encontró contrato con id {}", idContrato);
            } else {
                log.info("Contrato encontrado con id {}", idContrato);
            }

            return contrato;

        } catch (Exception e){
            log.error("Error al buscar contrato por id {}", idContrato, e);
            return null;
        }
    }

    public Contrato modificarPorId(Integer idContrato, Contrato contratoModificado){
        try{
            log.info("Intentando modificar contrato con id {}", idContrato);

            Contrato contratoOriginal = buscarPorId(idContrato);
            if (contratoOriginal == null){
                log.warn("No se puede modificar. No existe contrato con id {}", idContrato);
                return null;
            }

            empleadoClient.getEmpleado(contratoModificado.getRunEmpleado());
            sucusalClient.getSucursal(contratoModificado.getIdSucursal());

            contratoOriginal.setTipoContrato(contratoModificado.getTipoContrato());
            contratoOriginal.setCargoContrato(contratoModificado.getCargoContrato());
            contratoOriginal.setFecIniContrato(contratoModificado.getFecIniContrato());
            contratoOriginal.setFecFinContrato(contratoModificado.getFecFinContrato());
            contratoOriginal.setSalarioContrato(contratoModificado.getSalarioContrato());
            contratoOriginal.setRunEmpleado(contratoModificado.getRunEmpleado());
            contratoOriginal.setIdSucursal(contratoModificado.getIdSucursal());

            Contrato contratoActualizado = contratoRepository.save(contratoOriginal);
            log.info("Contrato modificado correctamente con id {}", idContrato);
            return contratoActualizado;

        } catch (Exception e){
            log.error("Error al modificar contrato con id {}", idContrato, e);
            return null;
        }
    }

    public boolean eliminarPorId(Integer idContrato){
        try{
            log.info("Intentando eliminar contrato con id {}", idContrato);

            Contrato contrato = buscarPorId(idContrato);
            if (contrato == null){
                log.warn("No se puede eliminar. No existe contrato con id {}", idContrato);
                return false;
            }

            contratoRepository.deleteById(idContrato);
            log.info("Contrato eliminado correctamente con id {}", idContrato);
            return true;

        } catch (Exception e){
            log.error("Error al eliminar contrato con id {}", idContrato, e);
            return false;
        }
    }
}
