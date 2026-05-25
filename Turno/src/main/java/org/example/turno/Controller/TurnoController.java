package org.example.turno.Controller;

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

    @PostMapping
    public ResponseEntity<Turno> registrar (@Valid @RequestBody Turno turno){
        turnoService.registrar(turno);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        List<Turno> lista = turnoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @GetMapping("/fechaTurno/{fechaCreacionBoleta}")
    public ResponseEntity<List<Turno>> buscarPorFechaTurno(@PathVariable LocalDate fechaCreacionBoleta){
        List<Turno> lista = turnoService.buscarPorFecha(fechaCreacionBoleta);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/tipoTurno/{tipoTurno}")
    public ResponseEntity<List<Turno>> buscarPorTipoTurno (@PathVariable String tipoTurno){
        List<Turno> lista = turnoService.buscarPorTipo(tipoTurno);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> modificarPorId (@PathVariable Integer id, @Valid @RequestBody Turno turno){
        Turno turnoNuevo = turnoService.modificarPorId(id, turno);

        if (turnoNuevo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(turnoNuevo, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId (@PathVariable Integer id){
        Turno turno = turnoService.buscarPorId(id);

        if (turno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(turno, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId (@PathVariable Integer id){
        boolean eliminado = turnoService.eliminarPorId(id);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
