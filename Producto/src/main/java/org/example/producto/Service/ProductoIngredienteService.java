package org.example.producto.Service;

import lombok.RequiredArgsConstructor;
import org.example.producto.Model.ProductoIngredienteId;
import org.example.producto.Model.ProductoIngredienteModel;
import org.example.producto.Repository.ProductoIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoIngredienteService {
    private final ProductoIngredienteRepository repository;

    public List<ProductoIngredienteModel> listar(){
        return repository.findAll();
    }
    public ProductoIngredienteModel findById(ProductoIngredienteId id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Relacion no encontrada"));
    }
    public ProductoIngredienteModel guardar(ProductoIngredienteModel productoIngredienteModel) {
        return repository.save(productoIngredienteModel);

    }
    public void eliminar(ProductoIngredienteId id) {
        repository.deleteById(id);
    }
}
