package org.example.contrato.Controller;


import jakarta.validation.Valid;
import org.example.contrato.Model.Contrato;
import org.example.contrato.Service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos")


public class ContratoController {


    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Contrato> registrar(@Valid @RequestBody Contrato contrato){
        contratoService.registrar(contrato);
        return new ResponseEntity<>(contrato, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Contrato>> listarTodos(){
        List<Contrato> lista = contratoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/run/{runEmpleado}")
    public ResponseEntity<List<Contrato>> buscarPorRun(@PathVariable String runEmpleado){
        List<Contrato> listaEncontrados = contratoService.buscarPorRun(runEmpleado);
        return new ResponseEntity<>(listaEncontrados, HttpStatus.OK);
    }

    @GetMapping("/salario/{salario}")
    public ResponseEntity<List<Contrato>> buscarPorSalario(@PathVariable BigDecimal salario){
        List<Contrato> listaEncontrados = contratoService.buscarPorSalario(salario);
        return new ResponseEntity<>(listaEncontrados, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> modificarPorId(@PathVariable Integer id, @Valid @RequestBody Contrato contrato){
        Contrato contratoNuevo = contratoService.modificarPorId(id, contrato);

        if (contratoNuevo== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contratoNuevo, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscarPorId(@PathVariable Integer id){
        Contrato contrato = contratoService.buscarPorId(id);
        if (contrato == null){
            return new ResponseEntity<>(contrato, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contrato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id){
        boolean eliminado = contratoService.eliminarPorId(id);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
