package org.example.comuna.Service;

import org.example.comuna.Model.ComunaModel;
import org.example.comuna.Repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaModel>listarComunas(){
        return comunaRepository.findAll();
    }

    public ComunaModel buscarPorId(Integer id){
        return comunaRepository.findById(id).orElse(null);
    }

    public ComunaModel agregarComuna(ComunaModel nuevo){
        return comunaRepository.save(nuevo);
    }
    public boolean borrarComuna (Integer id){
        if (comunaRepository.existsById(id)){
            comunaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public ComunaModel actualizarComuna(Integer id,ComunaModel nuevo){
        if(comunaRepository.existsById(id)){
            ComunaModel comuna= comunaRepository.findById(id).orElse(null);
            if (comuna!=null){
                comuna.setNombreComuna(nuevo.getNombreComuna());
                comuna.setIdRegion(nuevo.getIdRegion());
                return comunaRepository.save(comuna);
            }
        }
        return null;
    }
    public  List<ComunaModel> listarPorRegion(Integer idRegion){
        return comunaRepository.findByIdRegion(idRegion);
    }
}
