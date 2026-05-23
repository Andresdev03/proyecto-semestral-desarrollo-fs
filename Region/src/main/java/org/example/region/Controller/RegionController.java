package org.example.region.Controller;

import com.netflix.discovery.converters.Auto;
import org.example.region.Model.RegionModel;
import org.example.region.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/region")
@CrossOrigin(origins="*")
public class RegionController {
    @Autowired
    private RegionRepository regionRepository;
    // obtener las regiones
    @GetMapping
    public List<RegionModel>listarRegiones(){
        return regionRepository.findAll();
    }
   //Guardar una nueva region
   @PostMapping
   public ResponseEntity<RegionModel>crearRegion(@RequestBody RegionModel region){
       RegionModel nuevaRegion = regionRepository.save(region);
       return ResponseEntity.ok(nuevaRegion);
   }


}
