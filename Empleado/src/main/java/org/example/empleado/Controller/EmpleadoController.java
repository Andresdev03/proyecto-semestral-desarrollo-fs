package org.example.empleado.Controller;

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
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado empleado){
        empleadoService.registrar(empleado);
        return new ResponseEntity<>(empleado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos(){
        List<Empleado> lista = empleadoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @GetMapping("/correo/{correoEmpleado}")
    public ResponseEntity<List<Empleado>> buscarPorCorreo(@PathVariable String correoEmpleado){
        List<Empleado> lista = empleadoService.buscarPorCorreo(correoEmpleado);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @GetMapping("/appaterno/{appaternoEmpleado}")
    public ResponseEntity<List<Empleado>> buscarPorAppaterno(@PathVariable String appaternoEmpleado){
        List<Empleado> lista = empleadoService.buscarPorAppaterno(appaternoEmpleado);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/{run}")
    public ResponseEntity<Empleado> modificarPorRun(@PathVariable String run, @Valid @RequestBody Empleado empleado){
        Empleado empleadoNuevo = empleadoService.modificarPorRun(run, empleado);
        if (empleadoNuevo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleadoNuevo, HttpStatus.OK);
    }

    @GetMapping("/{run}")
    public ResponseEntity<Empleado> buscarPorRun(@PathVariable String run){
        Empleado empleado = empleadoService.buscarPorRun(run);

        if (empleado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @DeleteMapping("/{run}")
    public ResponseEntity<Void> eliminarPorRun(@PathVariable String run){
        boolean eliminado = empleadoService.eliminarPorRun(run);

        if (!eliminado){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
