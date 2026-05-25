package org.example.region.Service;

import org.example.region.Model.RegionModel;
import org.example.region.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionModel>listarRegiones(){
        return  regionRepository.findAll();
    }

    public RegionModel buscarPorId(Integer id){
        return  regionRepository.findById(id).orElse(null);
    }
    public RegionModel agregarRegion(RegionModel nuevo){
        return regionRepository.save(nuevo);
    }
    public boolean borrarRegion(Integer id){
        if (regionRepository.existsById(id)) {
            regionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public RegionModel actualizarRegion(Integer id, RegionModel nuevo){
        if (regionRepository.existsById(id)){
            RegionModel region = regionRepository.findById(id).orElse(null);
            if(region !=null){
                region.setNombreRegion(nuevo.getNombreRegion());
                return  regionRepository.save(region);
            }
        }
        return null;
    }
}
