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
        try {
            log.info("Buscando todas las facturas");
            return facturaRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    
    public List<FacturaModel> findByRutReceptor(String rutReceptor) {
        try {
            log.info("Buscando facturas por RutReceptor {}", rutReceptor);
            return facturaRepository.findByRutReceptor(rutReceptor);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public FacturaModel getFactura(Integer id){
        try {
            log.info("Buscando factura por id {}", id);
            return facturaRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public FacturaModel addFactura(FacturaModel facturaModel){
        try {
            log.info("Añadiendo nueva factura");
            return facturaRepository.save(facturaModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean deleteFactura(Integer id){
        try{
            log.info("Borrando factura por id {}", id);
            facturaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }
}
