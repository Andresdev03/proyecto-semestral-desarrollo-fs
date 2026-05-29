package org.example.factura.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.factura.Model.FacturaModel;
import org.example.factura.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public List<FacturaModel> getFacturas (){
        return facturaRepository.findAll();
    }
    
    public List<FacturaModel> findByRutReceptor(String rutReceptor) {
        log.info("Buscando facturas por RutReceptor {}", rutReceptor);
        return facturaRepository.findByRutReceptor(rutReceptor);
    }

    public FacturaModel getFactura(Integer id){
        log.info("Buscando factura por id {}", id);
        return facturaRepository.findById(id).get();
    }

    public FacturaModel addFactura(FacturaModel facturaModel){
        log.info("Añadiendo nueva factura");
        return facturaRepository.save(facturaModel);
    }

    public boolean deleteFactura(Integer id){
        if(facturaRepository.existsById(id)){
            log.info("Borrando factura por id {}", id);
            facturaRepository.deleteById(id);
            return true;
        }else{
            log.info("Factura no encontrada");
            return false;
        }
    }
}
