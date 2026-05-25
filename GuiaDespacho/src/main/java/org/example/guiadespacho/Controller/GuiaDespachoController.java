package org.example.guiadespacho.Controller;

import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.example.guiadespacho.Service.GuiaDespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guia_despacho")
public class GuiaDespachoController {
    @Autowired
    private GuiaDespachoService guiaDespachoService;

    @GetMapping
    public ResponseEntity<List<GuiaDespachoModel>> findAll(){
        List<GuiaDespachoModel> guias = guiaDespachoService.findAll();
        if(guias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuiaDespachoModel> findById(@PathVariable Integer id){
        GuiaDespachoModel guia = guiaDespachoService.findById(id);
        if(guia == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guia);
    }

    @PostMapping
    public ResponseEntity<GuiaDespachoModel> addGuiaDespacho(@RequestBody GuiaDespachoModel guia){
        GuiaDespachoModel newGuia = guiaDespachoService.addGuia(guia);
        if(newGuia == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newGuia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuiaDespacho(@PathVariable Integer id){
        boolean result = guiaDespachoService.deleteById(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
