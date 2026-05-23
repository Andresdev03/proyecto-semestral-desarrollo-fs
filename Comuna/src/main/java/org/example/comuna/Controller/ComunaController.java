package org.example.comuna.Controller;

import org.example.comuna.Model.ComunaModel;
import org.example.comuna.Repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/comunas")
@CrossOrigin(origins = "*")
public class ComunaController {
    @Autowired
    private ComunaRepository comunaRepository;

    @GetMapping
    public List<ComunaModel> listarComunas(){
        return comunaRepository.findAll();
    }
    @GetMapping("/region/{idRegion}")
    public  List<ComunaModel>listarporRegion(@PathVariable Integer idRegion){
        return  comunaRepository.findByIdRegion(idRegion);
    }

    @PostMapping
    public ResponseEntity<ComunaModel> crearComuna(@RequestBody ComunaModel comuna){
        ComunaModel nuevaComuna=comunaRepository.save(comuna);
        return ResponseEntity.ok(nuevaComuna);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunaModel> actualizarComuna(@PathVariable Integer id, @RequestBody ComunaModel comunaDetalles) {
        return comunaRepository.findById(id).map(comunaExistente -> {
            comunaExistente.setNombreComuna(comunaDetalles.getNombreComuna());
            comunaExistente.setIdRegion(comunaDetalles.getIdRegion());

            ComunaModel comunaActualizada = comunaRepository.save(comunaExistente);
            return ResponseEntity.ok(comunaActualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComuna(@PathVariable Integer id){
        return comunaRepository.findById(id).map(comuna->{
            comunaRepository.delete(comuna);
            return ResponseEntity.ok().<Void>build();
        })
                .orElse(ResponseEntity.notFound().build());
    }
}
