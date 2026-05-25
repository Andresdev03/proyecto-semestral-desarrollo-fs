package org.example.contrato.Service;


import org.example.contrato.Model.Contrato;
import org.example.contrato.Repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;


    public Contrato registrar(Contrato contrato){
        return contratoRepository.save(contrato);

    }


    public List<Contrato> listarTodos(){
        return contratoRepository.findAll();
    }

    public List<Contrato> buscarPorRun(String runEmpleado){
        return contratoRepository.findByRunEmpleado(runEmpleado);
    }

    public List<Contrato> buscarPorSalario(BigDecimal salario){
        return contratoRepository.findBySalarioContrato(salario);
    }

    public Contrato buscarPorId(Integer idContrato){
        return contratoRepository.findById(idContrato).orElse(null);
    }


    public Contrato modificarPorId(Integer idContrato, Contrato contratoModificado){
        Contrato contratoOriginal = buscarPorId(idContrato);
        if (contratoOriginal == null){
            return null;
        }
        contratoOriginal.setTipoContrato(contratoModificado.getTipoContrato());
        contratoOriginal.setCargoContrato(contratoModificado.getCargoContrato());
        contratoOriginal.setFecIniContrato(contratoModificado.getFecIniContrato());
        contratoOriginal.setFecFinContrato(contratoModificado.getFecFinContrato());
        contratoOriginal.setSalarioContrato(contratoModificado.getSalarioContrato());
        contratoOriginal.setRunEmpleado(contratoModificado.getRunEmpleado());
        contratoOriginal.setIdSucursal(contratoModificado.getIdSucursal());
        return contratoRepository.save(contratoOriginal);

    }

    public boolean eliminarPorId(Integer idContrato){
        Contrato contrato = buscarPorId(idContrato);

        if (contrato == null){
            return false;
        }

        contratoRepository.deleteById(idContrato);
        return true;
    }




}
