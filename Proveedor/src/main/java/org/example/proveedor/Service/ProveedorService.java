package org.example.proveedor.Service;

import lombok.RequiredArgsConstructor;
import org.example.proveedor.Model.ProveedorModel;
import org.example.proveedor.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorModel getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public List<ProveedorModel> getTodosProveedores() {
        return proveedorRepository.findAll();
    }

    public ProveedorModel createProveedor(ProveedorModel proveedorModel) {
        return proveedorRepository.save(proveedorModel);
    }
}
