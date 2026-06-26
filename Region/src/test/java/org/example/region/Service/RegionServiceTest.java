package org.example.region.Service;

import org.example.region.Model.RegionModel;
import org.example.region.Repository.RegionRepository;
import org.example.region.Service.RegionService;
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
public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private RegionModel regionEjemplo;

    @BeforeEach
    void setUp() {
        regionEjemplo = new RegionModel();
        regionEjemplo.setIdRegion(1);
        regionEjemplo.setNombreRegion("Metropolitana");
    }

    @Test
    void testListarRegiones() {
        when(regionRepository.findAll()).thenReturn(Arrays.asList(regionEjemplo));

        List<RegionModel> resultado = regionService.listarRegiones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Metropolitana", resultado.get(0).getNombreRegion());
        verify(regionRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Exitoso() {
        when(regionRepository.findById(1)).thenReturn(Optional.of(regionEjemplo));

        RegionModel resultado = regionService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdRegion());
        assertEquals("Metropolitana", resultado.getNombreRegion());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(regionRepository.findById(99)).thenReturn(Optional.empty());

        RegionModel resultado = regionService.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    void testAgregarRegion() {
        when(regionRepository.save(any(RegionModel.class))).thenReturn(regionEjemplo);

        RegionModel resultado = regionService.agregarRegion(new RegionModel());

        assertNotNull(resultado);
        assertEquals("Metropolitana", resultado.getNombreRegion());
    }

    @Test
    void testBorrarRegion_Exitoso() {
        when(regionRepository.existsById(1)).thenReturn(true);
        doNothing().when(regionRepository).deleteById(1);

        boolean resultado = regionService.borrarRegion(1);

        assertTrue(resultado);
        verify(regionRepository, times(1)).deleteById(1);
    }

    @Test
    void testBorrarRegion_NoExiste() {
        when(regionRepository.existsById(99)).thenReturn(false);

        boolean resultado = regionService.borrarRegion(99);

        assertFalse(resultado);
        verify(regionRepository, never()).deleteById(anyInt());
    }
}