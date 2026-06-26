package org.example.ingrediente.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingrediente")
@Tag(name="API Ingrediente", description = "API para la gestión de ingredientes")
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    @Operation(summary = "Obetener ingredinte según ID",
            description = "Endpoint para obtener un ingrediente especifico mediante su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornado ingrediente")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no se ha encontrado el recurso")
    public ResponseEntity<IngredienteModel> getIngrediente(@PathVariable Integer id){
        IngredienteModel ingrediente = ingredienteService.getIngredienteById(id);
        if(ingrediente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingrediente);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los ingredientes",
            description = "Endpoint para consultar todos los ingredientes registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, retornados todos los ingredientes")
    @ApiResponse(responseCode = "404", description = "Consulta fallida, no hay ingredientes registrados")
    public ResponseEntity<List<IngredienteModel>> getAllIngredientes(){
        List<IngredienteModel> ingredientes = ingredienteService.getIngredientes();
        if (ingredientes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientes);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo ingrediente",
            description = "Endpoint para generar un nuevo ingrediente en la BBDD")
    @ApiResponse(responseCode = "201", description = "Registro exitoso, nuevo ingrediente agregado")
    @ApiResponse(responseCode = "403", description = "Registro fallido")
    public ResponseEntity<IngredienteModel> addIngrediente(@RequestBody IngredienteModel ingrediente){
        IngredienteModel newIngrediente = ingredienteService.addIngrediente(ingrediente);
        if(newIngrediente == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newIngrediente);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ingrediente por ID",
            description = "Endpoint para eliminar un ingrediente de la BBDD mediante su ID")
    public ResponseEntity<IngredienteModel> deleteIngrediente(@PathVariable Integer id){
        boolean result = ingredienteService.deleteIngrediente(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
