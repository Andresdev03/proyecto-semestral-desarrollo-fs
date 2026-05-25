package org.example.guiadespacho.Service;

import com.netflix.discovery.converters.Auto;
import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.example.guiadespacho.Repository.GuiaDespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuiaDespachoService {
    @Autowired
    private GuiaDespachoRepository guiaDespachoRepository;

    public List<GuiaDespachoModel> findAll(){
        return guiaDespachoRepository.findAll();
    }

    public GuiaDespachoModel findById(Integer id){
        return guiaDespachoRepository.findById(id).get();
    }

    public GuiaDespachoModel addGuia(GuiaDespachoModel guia){
        return guiaDespachoRepository.save(guia);
    }

    public boolean deleteById(Integer id){
        if(guiaDespachoRepository.existsById(id)){
            guiaDespachoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
