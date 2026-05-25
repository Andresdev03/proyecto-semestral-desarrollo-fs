package org.example.empleado.Repository;

import org.example.empleado.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository  extends JpaRepository<Empleado, String> {

    List<Empleado> findByCorreoEmpleado(String correoEmpleado);
    List <Empleado> findByAppaternoEmpleado(String appaternoEmpleado);

}
