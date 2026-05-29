package org.example.ingrediente.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<IngredienteModel> getIngredientes(){
        log.info("Obteniendo todos los ingredientes");
        return ingredienteRepository.findAll();
    }

    public IngredienteModel getIngredienteById(Integer id){
        log.info("Obteniendo ingrediente por id {}", id);
        return ingredienteRepository.findById(id).get();
    }

    public IngredienteModel addIngrediente(IngredienteModel newIngrediente){
        log.info("Creando nuevo ingrediente {}", newIngrediente);
        return ingredienteRepository.save(newIngrediente);
    }

    public boolean deleteIngrediente(Integer id){
        if(ingredienteRepository.existsById(id)){
            log.info("Eliminando ingrediente {}", id);
            ingredienteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
