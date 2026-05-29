package org.example.guiadespacho.Service;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.example.guiadespacho.Repository.GuiaDespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GuiaDespachoService {
    @Autowired
    private GuiaDespachoRepository guiaDespachoRepository;

    public List<GuiaDespachoModel> findAll(){
        log.info("Obteniendo todas las guias de despacho");
        return guiaDespachoRepository.findAll();
    }

    public GuiaDespachoModel findById(Integer id){
        log.info("Obteniendo guia de despacho por id");
        return guiaDespachoRepository.findById(id).get();
    }

    public GuiaDespachoModel addGuia(GuiaDespachoModel guia){
        log.info("Añadiendo guia de despacho");
        return guiaDespachoRepository.save(guia);
    }

    public boolean deleteById(Integer id){
        if(guiaDespachoRepository.existsById(id)){
            log.info("Eliminando guia de despacho");
            guiaDespachoRepository.deleteById(id);
            return true;
        }
        log.info("No existe guia de despacho");
        return false;
    }
}
