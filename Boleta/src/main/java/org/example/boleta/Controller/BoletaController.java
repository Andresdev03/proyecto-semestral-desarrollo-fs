package org.example.boleta.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Registrar una boleta nuevo.", description = "Registra la boleta y la retorna.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta registrada exitosamente."),
            @ApiResponse(responseCode = "400", description = "Boleta no registrada por datos inválidos.")
    })
    @PostMapping
    public ResponseEntity<Boleta> registrar (@Valid @RequestBody Boleta boleta){
        boletaService.registrar(boleta);
        return new ResponseEntity<>(boleta, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las boletas", description = "Retorna la lista completa de boletas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa.")
    })
    @GetMapping
    public ResponseEntity<List<Boleta>> listarTodas(){
        List<Boleta> lista = boletaService.listarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar boletas por su fecha de creación.", description = "Retorna la lista de boletas segun fecha de creación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta encontrada."),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada.")
    })
    @GetMapping("/fechaCreacion/{fechaCreacionBoleta}")
    public ResponseEntity<List<Boleta>> buscarPorFechaCreacion(@PathVariable LocalDate fechaCreacionBoleta){
        List<Boleta> lista = boletaService.buscarPorFecha(fechaCreacionBoleta);
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }
    @Operation(summary = "Buscar boletas por su método de pago.", description = "Retorna la lista de boletas segun método de pago.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta encontrada."),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada.")
    })
    @GetMapping("/metodoPago/{metodoPagoBoleta}")
    public ResponseEntity<List<Boleta>> buscarPorMetodoPago(@PathVariable String metodoPagoBoleta){
        List<Boleta> lista = boletaService.buscarPorMetodoPago(metodoPagoBoleta);
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @Operation(summary = "Modificar boletas por su ID.", description = "Modifica la boleta, identificada por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta modificada."),
            @ApiResponse(responseCode = "404", description = "Boleta no modificada.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Boleta> modificarPorId(@PathVariable Integer id, @Valid @RequestBody Boleta boleta){
        Boleta boletaNueva = boletaService.modificarPorId(id, boleta);

        if (boletaNueva == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(boletaNueva, HttpStatus.OK);


    }

    @Operation(summary = "Buscar boleta por su ID.", description = "Retorna la lista de boletas segun ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta encontrada."),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Boleta> buscarPorId(@PathVariable Integer id){
        Boleta boleta = boletaService.buscarPorId(id);
        if (boleta == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(boleta, HttpStatus.OK);

    }

    @Operation(summary = "Eliminar boeltas por su ID.", description = "Elimina una boleta segun ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Boleta eliminada exitosamente."),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id){
        boolean eliminada = boletaService.eliminarPorId(id);
        if (!eliminada){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
