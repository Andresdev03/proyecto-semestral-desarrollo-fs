package org.example.empleado;

import net.datafaker.Faker;
import org.example.empleado.Model.Empleado;
import org.example.empleado.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Empleado emp = new Empleado();
            emp.setRunEmpleado(String.valueOf(10000000 + i));
            emp.setDvRunEmpleado(faker.numerify("#"));
            emp.setCorreoEmpleado(faker.internet().emailAddress());
            emp.setTelefonoEmpleado("569" + faker.number().numberBetween(10000000, 99999999));
            emp.setPnombreEmpleado(faker.name().firstName());
            emp.setSnombreEmpleado(faker.name().firstName());
            emp.setAppaternoEmpleado(faker.name().lastName());
            emp.setApmaternoEmpleado(faker.name().lastName());
            emp.setFechaNacEmpleado(LocalDate.of(
                    faker.number().numberBetween(1970, 2000),
                    faker.number().numberBetween(1, 12),
                    faker.number().numberBetween(1, 28)
            ));
            empleadoRepository.save(emp);
        }
    }
}
