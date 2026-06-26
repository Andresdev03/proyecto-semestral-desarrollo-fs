package org.example.guiadespacho.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.example.guiadespacho.Service.GuiaDespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guia_despacho")
@Tag(name = "API Guía de Despacho", description = "API para la gestión de guías de despacho")
public class GuiaDespachoController {
    @Autowired
    private GuiaDespachoService guiaDespachoService;

    @GetMapping
    @Operation(summary = "Obtener todas las guías de despacho",
            description = "Endpoint para consultar todas las guías de despacho registradas")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornadas todas las guías")
    @ApiResponse(responseCode = "204", description = "Consulta exitosa, pero no hay guías de despacho registradas")
    public ResponseEntity<List<GuiaDespachoModel>> findAll(){
        List<GuiaDespachoModel> guias = guiaDespachoService.findAll();
        if(guias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener guía de despacho según ID",
            description = "Endpoint para obtener una guía de despacho específica mediante su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornada guía de despacho")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no se ha encontrado el recurso")
    public ResponseEntity<GuiaDespachoModel> findById(@PathVariable Integer id){
        GuiaDespachoModel guia = guiaDespachoService.findById(id);
        if(guia == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guia);
    }

    @PostMapping
    @Operation(summary = "Agregar una nueva guía de despacho",
            description = "Endpoint para generar una nueva guía de despacho en la BBDD")
    @ApiResponse(responseCode = "200", description = "Registro exitoso, nueva guía agregada")
    @ApiResponse(responseCode = "400", description = "Registro fallido, datos de la solicitud incorrectos")
    public ResponseEntity<GuiaDespachoModel> addGuiaDespacho(@RequestBody GuiaDespachoModel guia){
        GuiaDespachoModel newGuia = guiaDespachoService.addGuia(guia);
        if(newGuia == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newGuia);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una guía de despacho por ID",
            description = "Endpoint para eliminar una guía de despacho de la BBDD mediante su ID")
    @ApiResponse(responseCode = "200", description = "Eliminación exitosa")
    @ApiResponse(responseCode = "404", description = "Eliminación fallida, no se ha encontrado el recurso")
    public ResponseEntity<Void> deleteGuiaDespacho(@PathVariable Integer id){
        boolean result = guiaDespachoService.deleteById(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
