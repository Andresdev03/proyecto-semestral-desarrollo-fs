package org.example.empleado.Service;

import org.example.empleado.Model.Empleado;
import org.example.empleado.Repository.EmpleadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    @DisplayName("Debe retornar lista de todos los empleados")
    void testListarTodos() {
        // Arrange
        Empleado emp = new Empleado();
        emp.setRunEmpleado("12345678");
        emp.setPnombreEmpleado("Sebastian");
        when(empleadoRepository.findAll()).thenReturn(List.of(emp));

        // Act
        List<Empleado> resultado = empleadoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("12345678", resultado.get(0).getRunEmpleado());
    }

    @Test
    @DisplayName("Debe retornar empleado cuando existe el RUN")
    void testBuscarPorRunExistente() {
        // Arrange
        Empleado emp = new Empleado();
        emp.setRunEmpleado("12345678");
        when(empleadoRepository.findById("12345678")).thenReturn(Optional.of(emp));

        // Act
        Empleado resultado = empleadoService.buscarPorRun("12345678");

        // Assert
        assertNotNull(resultado);
        assertEquals("12345678", resultado.getRunEmpleado());
    }

    @Test
    @DisplayName("Debe retornar null cuando el RUN no existe")
    void testBuscarPorRunInexistente() {
        // Arrange
        when(empleadoRepository.findById("99999999")).thenReturn(Optional.empty());

        // Act
        Empleado resultado = empleadoService.buscarPorRun("99999999");

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe eliminar empleado existente y retornar true")
    void testEliminarPorRunExistente() {
        // Arrange
        Empleado emp = new Empleado();
        emp.setRunEmpleado("12345678");
        when(empleadoRepository.findById("12345678")).thenReturn(Optional.of(emp));

        // Act
        boolean resultado = empleadoService.eliminarPorRun("12345678");

        // Assert
        assertTrue(resultado);
        verify(empleadoRepository, times(1)).deleteById("12345678");
    }

    @Test
    @DisplayName("Debe retornar false cuando el empleado a eliminar no existe")
    void testEliminarPorRunInexistente() {
        // Arrange
        when(empleadoRepository.findById("99999999")).thenReturn(Optional.empty());

        // Act
        boolean resultado = empleadoService.eliminarPorRun("99999999");

        // Assert
        assertFalse(resultado);
        verify(empleadoRepository, never()).deleteById(any());
    }
}