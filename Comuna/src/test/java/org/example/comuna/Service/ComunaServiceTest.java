package org.example.comuna.Service;

import org.example.comuna.Model.ComunaModel;
import org.example.comuna.Repository.ComunaRepository;
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
public class ComunaServiceTest {

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    private ComunaModel comunaEjemplo;

    @BeforeEach
    void setUp() {
        comunaEjemplo = new ComunaModel();
        comunaEjemplo.setIdComuna(1);
        comunaEjemplo.setNombreComuna("Santiago Centro");
        comunaEjemplo.setIdRegion(13); // ID ficticio de la región Metropolitana
    }

    @Test
    void testListarComunas() {
        when(comunaRepository.findAll()).thenReturn(Arrays.asList(comunaEjemplo));

        List<ComunaModel> resultado = comunaService.listarComunas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Santiago Centro", resultado.get(0).getNombreComuna());
        verify(comunaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Exitoso() {
        when(comunaRepository.findById(1)).thenReturn(Optional.of(comunaEjemplo));

        ComunaModel resultado = comunaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdComuna());
        assertEquals("Santiago Centro", resultado.getNombreComuna());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(comunaRepository.findById(99)).thenReturn(Optional.empty());

        ComunaModel resultado = comunaService.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    void testAgregarComuna() {
        when(comunaRepository.save(any(ComunaModel.class))).thenReturn(comunaEjemplo);

        ComunaModel resultado = comunaService.agregarComuna(new ComunaModel());

        assertNotNull(resultado);
        assertEquals("Santiago Centro", resultado.getNombreComuna());
    }

    @Test
    void testBorrarComuna_Exitoso() {
        when(comunaRepository.existsById(1)).thenReturn(true);
        doNothing().when(comunaRepository).deleteById(1);

        boolean resultado = comunaService.borrarComuna(1);

        assertTrue(resultado);
        verify(comunaRepository, times(1)).deleteById(1);
    }

    @Test
    void testBorrarComuna_NoExiste() {
        when(comunaRepository.existsById(99)).thenReturn(false);

        boolean resultado = comunaService.borrarComuna(99);

        assertFalse(resultado);
        verify(comunaRepository, never()).deleteById(anyInt());
    }
}