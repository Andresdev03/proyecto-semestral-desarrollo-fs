package org.example.producto.Service;

import org.example.producto.Model.ProductoModel;
import org.example.producto.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    public List<ProductoModel> listarProductos(){
        return productoRepository.findAll();
    }
    public ProductoModel buscarPorId(Integer id){
        return productoRepository.findById(id).orElse(null);
    }
    public ProductoModel agregarProducto(ProductoModel nuevo){
        return productoRepository.save(nuevo);
    }
    public boolean borrarProducto(Integer id){
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return true;
        }
        return  false;
    }

    public ProductoModel actualizarProducto(Integer id, ProductoModel nuevo){
        if (productoRepository.existsById(id)){
            ProductoModel producto = productoRepository.findById(id).orElse(null);
            if (producto != null){
                producto.setNombreProducto(nuevo.getNombreProducto());
                producto.setDescripcionProducto(nuevo.getDescripcionProducto());
                producto.setPrecio(nuevo.getPrecio());
                producto.setCategoriaProducto(nuevo.getCategoriaProducto());
                producto.setDisponibleProducto(nuevo.getDisponibleProducto());

                return  productoRepository.save(producto);
            }
        }
        return null;
    }


}
