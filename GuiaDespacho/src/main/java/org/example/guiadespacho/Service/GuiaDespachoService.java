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
        try {
            log.info("Obteniendo todas las guias de despacho");
            return guiaDespachoRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public GuiaDespachoModel findById(Integer id){
            try {
                log.info("Obteniendo guia de despacho por id");
                return guiaDespachoRepository.findById(id).get();
            }  catch (Exception e) {
                log.error(e.getMessage());
                return null;
            }
    }

    public GuiaDespachoModel addGuia(GuiaDespachoModel guia){
        try {
            log.info("Añadiendo guia de despacho");
            return guiaDespachoRepository.save(guia);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean deleteById(Integer id){
        try{
            log.info("Eliminando guia de despacho");
            guiaDespachoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.info("No existe guia de despacho");
            return false;
        }
    }
}
