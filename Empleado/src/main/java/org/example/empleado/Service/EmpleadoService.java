package org.example.empleado.Service;

import org.example.empleado.Model.Empleado;
import org.example.empleado.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {


    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado registrar(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listarTodos(){
        return empleadoRepository.findAll();}


    public List<Empleado> buscarPorCorreo(String correoEmpleado) {
        return empleadoRepository.findByCorreoEmpleado(correoEmpleado);
    }

    public List<Empleado> buscarPorAppaterno(String appaternoEmpleado){
        return  empleadoRepository.findByAppaternoEmpleado(appaternoEmpleado);
    }
    public Empleado buscarPorRun(String run){
        return empleadoRepository.findById(run).orElse(null);
    }
    public Empleado modificarPorRun(String run, Empleado empleadoModificado){
        Empleado empleadoOriginal = buscarPorRun(run);
        if (empleadoOriginal == null){
            return null;
        }

        empleadoOriginal.setDvRunEmpleado(empleadoModificado.getDvRunEmpleado());
        empleadoOriginal.setCorreoEmpleado(empleadoModificado.getCorreoEmpleado());
        empleadoOriginal.setTelefonoEmpleado(empleadoModificado.getTelefonoEmpleado());
        empleadoOriginal.setPnombreEmpleado(empleadoModificado.getPnombreEmpleado());
        empleadoOriginal.setSnombreEmpleado(empleadoModificado.getSnombreEmpleado());
        empleadoOriginal.setAppaternoEmpleado(empleadoModificado.getAppaternoEmpleado());
        empleadoOriginal.setApmaternoEmpleado(empleadoModificado.getApmaternoEmpleado());
        empleadoOriginal.setFechaNacEmpleado(empleadoModificado.getFechaNacEmpleado());
        return empleadoRepository.save(empleadoOriginal);

    }

    public boolean eliminarPorRun(String run){
        Empleado empleado = buscarPorRun(run);

        if (empleado == null){
            return false;
        }

        empleadoRepository.deleteById(run);
        return true;



    }


    }







