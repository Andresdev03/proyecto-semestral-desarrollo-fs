package org.example.contrato.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Empleado", description = "Operaciones asociadas a empleado.")

public class ContratoController {


    @Autowired
    private ContratoService contratoService;

    @Operation(summary = "Registrar un contrato nuevo.", description = "Registra el contrato y lo retorna.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato registrado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Contrato no registrado por datos inválidos.")
    })
    @PostMapping
    public ResponseEntity<Contrato> registrar(@Valid @RequestBody Contrato contrato){
        contratoService.registrar(contrato);
        return new ResponseEntity<>(contrato, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los contratos.", description = "Retorna una lista de todos contratos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa.")
    })
    @GetMapping
    public ResponseEntity<List<Contrato>> listarTodos(){
        List<Contrato> lista = contratoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar contratos por run de empleados.", description = "Retorna una lista de contratos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato encontrado."),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado.")
    })
    @GetMapping("/run/{runEmpleado}")
    public ResponseEntity<List<Contrato>> buscarPorRun(@PathVariable String runEmpleado){
        List<Contrato> listaEncontrados = contratoService.buscarPorRunEmpleado(runEmpleado);
        return new ResponseEntity<>(listaEncontrados, HttpStatus.OK);
    }

    @Operation(summary = "Buscar contratos por salario de empleados.", description = "Retorna una lista de contratos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato encontrado."),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado.")
    })
    @GetMapping("/salario/{salario}")
    public ResponseEntity<List<Contrato>> buscarPorSalario(@PathVariable BigDecimal salario){
        List<Contrato> listaEncontrados = contratoService.buscarPorSalario(salario);
        return new ResponseEntity<>(listaEncontrados, HttpStatus.OK);
    }

    @Operation(summary = "Modificar contratos por su ID.", description = "Modifica el contrato y luego lo retorna.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato modificado."),
            @ApiResponse(responseCode = "404", description = "Contrato no modificado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Contrato> modificarPorId(@PathVariable Integer id, @Valid @RequestBody Contrato contrato){
        Contrato contratoNuevo = contratoService.modificarPorId(id, contrato);

        if (contratoNuevo== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contratoNuevo, HttpStatus.OK);


    }

    @Operation(summary = "Buscar contratos por ID.", description = "Retorna un contrato según ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato encontrado."),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscarPorId(@PathVariable Integer id){
        Contrato contrato = contratoService.buscarPorId(id);
        if (contrato == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contrato, HttpStatus.OK);
    }

    @Operation(summary = "Elimina el contrato según su ID.", description = "Si elimina el contrato, retorna no_content, si no lo elimina, retorna not_found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contrato eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id){
        boolean eliminado = contratoService.eliminarPorId(id);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
