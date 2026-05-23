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



}
