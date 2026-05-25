package org.example.region.Controller;

import com.netflix.discovery.converters.Auto;
import org.example.region.Model.RegionModel;
import org.example.region.Repository.RegionRepository;
import org.example.region.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/region")
@CrossOrigin(origins="*")
public class RegionController {
    @Autowired
    private RegionService regionService;
    // obtener las regiones

    @GetMapping("")
    public ResponseEntity<List<RegionModel>> getAllRegiones(){
        List<RegionModel> listado = regionService.listarRegiones();
        if(listado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(listado,HttpStatus.OK);
        }
    }
   //Guardar una nueva region
    @GetMapping("/{id}")
    public ResponseEntity<RegionModel> getRegionById(@PathVariable Integer id){
        RegionModel buscado= regionService.buscarPorId(id);
        if(buscado !=null){
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
   public ResponseEntity<RegionModel>crearRegion(@RequestBody RegionModel region){
       RegionModel nuevaRegion = regionService.agregarRegion(region);
       if(nuevaRegion != null){
           return new ResponseEntity<>(nuevaRegion, HttpStatus.CREATED);
       }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id){
        boolean res = regionService.borrarRegion(id);
        if (res){
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionModel> updateRegion(@PathVariable Integer id, @RequestBody RegionModel nuevo){
            RegionModel actualizado = regionService.actualizarRegion(id,nuevo);
            if(actualizado !=null){
                return  new ResponseEntity<>(actualizado,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

}
