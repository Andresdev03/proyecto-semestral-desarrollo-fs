package org.example.factura.Service;

import org.example.factura.Model.FacturaModel;
import org.example.factura.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public List<FacturaModel> getFacturas (){
        return facturaRepository.findAll();
    }

    public FacturaModel getFactura(Integer id){
        return facturaRepository.findById(id).get();
    }

    public FacturaModel addFactura(FacturaModel facturaModel){
        return facturaRepository.save(facturaModel);
    }

    public boolean deleteFactura(Integer id){
        if(facturaRepository.existsById(id)){
            facturaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
