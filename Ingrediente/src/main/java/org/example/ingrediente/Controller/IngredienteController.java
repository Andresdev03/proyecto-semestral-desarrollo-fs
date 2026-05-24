package org.example.ingrediente.Controller;

import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingrediente")
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteModel> getIngrediente(@PathVariable Integer id){
        IngredienteModel ingrediente = ingredienteService.getIngredienteById(id);
        if(ingrediente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingrediente);
    }

    @GetMapping
    public ResponseEntity<List<IngredienteModel>> getAllIngredientes(){
        List<IngredienteModel> ingredientes = ingredienteService.getIngredientes();
        if (ingredientes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientes);
    }

    @PostMapping
    public ResponseEntity<IngredienteModel> addIngrediente(@RequestBody IngredienteModel ingrediente){
        IngredienteModel newIngrediente = ingredienteService.addIngrediente(ingrediente);
        if(newIngrediente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newIngrediente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IngredienteModel> deleteIngrediente(@PathVariable Integer id){
        boolean result = ingredienteService.deleteIngrediente(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
