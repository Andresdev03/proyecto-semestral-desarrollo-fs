package org.example.comuna.Controller;

import org.example.comuna.Model.ComunaModel;
import org.example.comuna.Repository.ComunaRepository;
import org.example.comuna.Service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/comuna")
@CrossOrigin(origins = "*")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping("")
    public ResponseEntity<List<ComunaModel>> listarComunas(){
        List<ComunaModel> listado = comunaService.listarComunas();
        if(listado.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(listado,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaModel> getComunaById(@PathVariable Integer id){
        ComunaModel buscado = comunaService.buscarPorId(id);
        if(buscado !=null){
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/region/{idRegion}")
    public  ResponseEntity<List<ComunaModel>> listarporRegion(@PathVariable Integer idRegion){
        List<ComunaModel> listado = comunaService.listarPorRegion(idRegion);
        if (listado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ComunaModel> crearComuna(@RequestBody ComunaModel comuna){
        ComunaModel nuevaComuna=comunaService.agregarComuna(comuna);
        if(nuevaComuna !=null){
            return new ResponseEntity<>(nuevaComuna, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunaModel> actualizarComuna(@PathVariable Integer id, @RequestBody ComunaModel nuevo) {
        ComunaModel actualizado = comunaService.actualizarComuna(id,nuevo);
        if(actualizado != null){
            return  new ResponseEntity<>(actualizado,HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComuna(@PathVariable Integer id){
      boolean res= comunaService.borrarComuna(id);
      if (res){
          return new ResponseEntity<>(HttpStatus.OK);
      }else{
          return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }
}
