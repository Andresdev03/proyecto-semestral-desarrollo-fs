package org.example.producto.Service;

import org.example.producto.Model.ProductoModel;
import org.example.producto.Repository.ProductoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private ProductoModel productoEjemplo;

    @BeforeEach
    void setUp() {
        productoEjemplo = new ProductoModel();
        productoEjemplo.setIdProducto(1);
        productoEjemplo.setNombreProducto("Producto de Prueba");
        productoEjemplo.setPrecio(9990d);
    }

    @Test
    void testListarProductos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(productoEjemplo));

        List<ProductoModel> resultado = productoService.listarProductos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Producto de Prueba", resultado.get(0).getNombreProducto());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Exitoso() {
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoEjemplo));

        ProductoModel resultado = productoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdProducto());
        assertEquals("Producto de Prueba", resultado.getNombreProducto());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        ProductoModel resultado = productoService.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    void testAgregarProducto() {
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(productoEjemplo);

        ProductoModel resultado = productoService.agregarProducto(new ProductoModel());

        assertNotNull(resultado);
        assertEquals("Producto de Prueba", resultado.getNombreProducto());
    }

    @Test
    void testBorrarProducto_Exitoso() {
        when(productoRepository.existsById(1)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1);

        boolean resultado = productoService.borrarProducto(1);

        assertTrue(resultado);
        verify(productoRepository, times(1)).deleteById(1);
    }

    @Test
    void testBorrarProducto_NoExiste() {
        when(productoRepository.existsById(99)).thenReturn(false);

        boolean resultado = productoService.borrarProducto(99);

        assertFalse(resultado);
        verify(productoRepository, never()).deleteById(anyInt());
    }
}