package org.example.ingrediente.Service;

import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<IngredienteModel> getIngredientes(){
        return ingredienteRepository.findAll();
    }

    public IngredienteModel getIngredienteById(Integer id){
        return ingredienteRepository.findById(id).get();
    }

    public IngredienteModel addIngrediente(IngredienteModel newIngrediente){
        return ingredienteRepository.save(newIngrediente);
    }

    public boolean deleteIngrediente(Integer id){
        if(ingredienteRepository.existsById(id)){
            ingredienteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
