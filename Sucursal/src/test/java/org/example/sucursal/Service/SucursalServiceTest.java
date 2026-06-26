package org.example.sucursal.Service;

import org.example.sucursal.Model.SucursalModel;
import org.example.sucursal.Repository.SucursalRepository;
import org.example.sucursal.Service.SucursalService;
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
public class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalService sucursalService;

    private SucursalModel sucursalEjemplo;

    @BeforeEach
    void setUp() {
        sucursalEjemplo = new SucursalModel();
        sucursalEjemplo.setIdSucursal(1);
        sucursalEjemplo.setNombreSucursal("Sucursal Central");
        sucursalEjemplo.setDireccion("Av. Principal 123");
    }

    @Test
    void testListarSucursales() {
        when(sucursalRepository.findAll()).thenReturn(Arrays.asList(sucursalEjemplo));

        List<SucursalModel> resultado = sucursalService.listarSucursales();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Sucursal Central", resultado.get(0).getNombreSucursal());
        verify(sucursalRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Exitoso() {
        when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursalEjemplo));

        SucursalModel resultado = sucursalService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdSucursal());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(sucursalRepository.findById(99)).thenReturn(Optional.empty());

        SucursalModel resultado = sucursalService.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    void testAgregarSucursal() {
        when(sucursalRepository.save(any(SucursalModel.class))).thenReturn(sucursalEjemplo);

        SucursalModel resultado = sucursalService.agregarSucursal(new SucursalModel());

        assertNotNull(resultado);
        assertEquals("Sucursal Central", resultado.getNombreSucursal());
    }

    @Test
    void testBorrarSucursal_Exitoso() {
        when(sucursalRepository.existsById(1)).thenReturn(true);
        doNothing().when(sucursalRepository).deleteById(1);

        boolean resultado = sucursalService.borrarSucursal(1);

        assertTrue(resultado);
        verify(sucursalRepository, times(1)).deleteById(1);
    }

    @Test
    void testBorrarSucursal_NoExiste() {
        when(sucursalRepository.existsById(99)).thenReturn(false);

        boolean resultado = sucursalService.borrarSucursal(99);

        assertFalse(resultado);
        verify(sucursalRepository, never()).deleteById(anyInt());
    }
}