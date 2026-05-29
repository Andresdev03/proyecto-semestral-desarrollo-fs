package org.example.proveedor.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.proveedor.Model.ProveedorModel;
import org.example.proveedor.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorModel getProveedorById(Integer id) {
        try {
            log.info("Obteniendo proveedor por id {}", id);
            return proveedorRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<ProveedorModel> getTodosProveedores() {
        try {
            log.info("Obteniendo todos proveedores");
            return proveedorRepository.findAll();
        }catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public ProveedorModel createProveedor(ProveedorModel proveedorModel) {
        try {
            log.info("Creando proveedor {}", proveedorModel);
            return proveedorRepository.save(proveedorModel);
        }  catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
