package org.example.turno.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.turno.Model.Turno;
import org.example.turno.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Operation(summary = "Registrar un turno nuevo.", description = "Registra el turno y lo retorna.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno registrado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Turno no registrado por datos inválidos.")
    })
    @PostMapping
    public ResponseEntity<Turno> registrar (@Valid @RequestBody Turno turno){
        turnoService.registrar(turno);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los turnos.", description = "Retorna la lista completa de turnos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa.")
    })
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        List<Turno> lista = turnoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar turnos por fecha de turno.", description = "Retorna la lista de turnos según fecha de turno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado."),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @GetMapping("/fechaTurno/{fechaTurno}")
    public ResponseEntity<List<Turno>> buscarPorFechaTurno(@PathVariable LocalDate fechaTurno){
        List<Turno> lista = turnoService.buscarPorFecha(fechaTurno);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar turnos por tipo de turno.", description = "Retorna la lista de turnos según el tipo de turno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado."),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @GetMapping("/tipoTurno/{tipoTurno}")
    public ResponseEntity<List<Turno>> buscarPorTipoTurno (@PathVariable String tipoTurno){
        List<Turno> lista = turnoService.buscarPorTipo(tipoTurno);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Modificar turnos por su ID.", description = "Modifica al turno, identificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno modificado."),
            @ApiResponse(responseCode = "404", description = "Turno no modificado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Turno> modificarPorId (@PathVariable Integer id, @Valid @RequestBody Turno turno){
        Turno turnoNuevo = turnoService.modificarPorId(id, turno);

        if (turnoNuevo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(turnoNuevo, HttpStatus.OK);

    }

    @Operation(summary = "Buscar turnos por su ID.", description = "Retorna la lista de turnos segun ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado."),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId (@PathVariable Integer id){
        Turno turno = turnoService.buscarPorId(id);

        if (turno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(turno, HttpStatus.OK);

    }

    @Operation(summary = "Eliminar turnos por su ID.", description = "Elimina un turno segun ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId (@PathVariable Integer id){
        boolean eliminado = turnoService.eliminarPorId(id);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
