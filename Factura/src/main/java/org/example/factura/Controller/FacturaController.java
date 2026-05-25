package org.example.factura.Controller;

import org.example.factura.Model.FacturaModel;
import org.example.factura.Repository.FacturaRepository;
import org.example.factura.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping("/{id}")
    public ResponseEntity<FacturaModel> getFactura(@PathVariable Integer id){
        FacturaModel factura =  facturaService.getFactura(id);
        if (factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @GetMapping
    public ResponseEntity<List<FacturaModel>> getAllFacturas(){
        List<FacturaModel> listaFacturas = facturaService.getFacturas();
        if (listaFacturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaFacturas);
    }

    @PostMapping
    public ResponseEntity<FacturaModel> addFactura(@RequestBody FacturaModel factura){
        FacturaModel facturaModel = facturaService.addFactura(factura);
        if (facturaModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacturaModel> deleteFactura(@PathVariable Integer id){
        boolean cliente = facturaService.deleteFactura(id);
        if (cliente){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
