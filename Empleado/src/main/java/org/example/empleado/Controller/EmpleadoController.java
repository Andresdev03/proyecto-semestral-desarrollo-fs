package org.example.empleado.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.empleado.Model.Empleado;
import org.example.empleado.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@Tag(name = "Empleados", description = "Operaciones asociadas a empleado.")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Operation(summary = "Registrar un empleado nuevo.", description = "Registra el empleado y lo retorna.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado registrado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Empleado no registrado por datos inválidos.")
    })
    @PostMapping
    public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado empleado){
        empleadoService.registrar(empleado);
        return new ResponseEntity<>(empleado, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los empleados", description = "Retorna la lista completa de empleados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa.")
    })
    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos(){
        List<Empleado> lista = empleadoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @Operation(summary = "Buscar empleados por su correo.", description = "Retorna la lista de empleados segun correo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado.")
    })
    @GetMapping("/correo/{correoEmpleado}")
    public ResponseEntity<List<Empleado>> buscarPorCorreo(@PathVariable String correoEmpleado){
        List<Empleado> lista = empleadoService.buscarPorCorreo(correoEmpleado);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar empleados por su apellido paterno.", description = "Retorna la lista de empleados segun apellido paterno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado.")
    })
    @GetMapping("/appaterno/{appaternoEmpleado}")
    public ResponseEntity<List<Empleado>> buscarPorAppaterno(@PathVariable String appaternoEmpleado){
        List<Empleado> lista = empleadoService.buscarPorAppaterno(appaternoEmpleado);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Modificar empleados por su RUN.", description = "Modifica al empleado, identificado por su RUN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado modificado."),
            @ApiResponse(responseCode = "404", description = "Empleado no modificado.")
    })
    @PutMapping("/{run}")
    public ResponseEntity<Empleado> modificarPorRun(@PathVariable String run, @Valid @RequestBody Empleado empleado){
        Empleado empleadoNuevo = empleadoService.modificarPorRun(run, empleado);
        if (empleadoNuevo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleadoNuevo, HttpStatus.OK);
    }

    @Operation(summary = "Buscar empleados por su RUN.", description = "Retorna la lista de empleados segun RUN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado.")
    })
    @GetMapping("/{run}")
    public ResponseEntity<Empleado> buscarPorRun(@PathVariable String run){
        Empleado empleado = empleadoService.buscarPorRun(run);

        if (empleado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar empleados por su RUN.", description = "Elimina un empleado segun RUN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado.")
    })
    @DeleteMapping("/{run}")
    public ResponseEntity<Void> eliminarPorRun(@PathVariable String run){
        boolean eliminado = empleadoService.eliminarPorRun(run);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
