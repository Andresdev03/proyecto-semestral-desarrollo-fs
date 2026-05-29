package org.example.boleta.Controller;

import jakarta.validation.Valid;
import org.example.boleta.Model.Boleta;
import org.example.boleta.Service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @PostMapping
    public ResponseEntity<Boleta> registrar (@Valid @RequestBody Boleta boleta){
        boletaService.registrar(boleta);
        return new ResponseEntity<>(boleta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> listarTodas(){
        List<Boleta> lista = boletaService.listarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/fechaCreacion/{fechaCreacionBoleta}")
    public ResponseEntity<List<Boleta>> buscarPorFechaCreacion(@PathVariable LocalDate fechaCreacionBoleta){
        List<Boleta> lista = boletaService.buscarPorFecha(fechaCreacionBoleta);
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }
    @GetMapping("/metodoPago/{metodoPagoBoleta}")
    public ResponseEntity<List<Boleta>> buscarPorMetodoPago(@PathVariable String metodoPagoBoleta){
        List<Boleta> lista = boletaService.buscarPorMetodoPago(metodoPagoBoleta);
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> modificarPorId(@PathVariable Integer id, @Valid @RequestBody Boleta boleta){
        Boleta boletaNueva = boletaService.modificarPorId(id, boleta);

        if (boletaNueva == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(boletaNueva, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> buscarPorId(@PathVariable Integer id){
        Boleta boleta = boletaService.buscarPorId(id);
        if (boleta == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(boleta, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id){
        boolean eliminada = boletaService.eliminarPorId(id);
        if (!eliminada){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
