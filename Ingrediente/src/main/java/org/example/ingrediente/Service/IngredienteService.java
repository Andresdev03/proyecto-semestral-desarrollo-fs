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
        try {
            log.info("Obteniendo todos los ingredientes");
            return ingredienteRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public IngredienteModel getIngredienteById(Integer id){
        try {
            log.info("Obteniendo ingrediente por id {}", id);
            return ingredienteRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public IngredienteModel addIngrediente(IngredienteModel newIngrediente){
        try {
            log.info("Creando nuevo ingrediente {}", newIngrediente);
            return ingredienteRepository.save(newIngrediente);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean deleteIngrediente(Integer id){

        try{
            log.info("Eliminando ingrediente {}", id);
            ingredienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
