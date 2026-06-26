package org.example.guiadespacho;

import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.example.guiadespacho.Service.GuiaDespachoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class GuiaDespachoServiceTest {

    @Autowired
    private GuiaDespachoService guiaDespachoService;

    @Test
    public void testAddGuia() {
        GuiaDespachoModel guia = new GuiaDespachoModel();
        guia.setFecEmisionGuia(LocalDate.now());
        guia.setDescripcionGuia("Test Guia Despacho Description");
        guia.setEstadoGuia("EMITIDO");
        guia.setIdProveedor(1);
        guia.setIdSucursal(2);

        GuiaDespachoModel saved = guiaDespachoService.addGuia(guia);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDescripcionGuia()).isEqualTo("Test Guia Despacho Description");
        assertThat(saved.getEstadoGuia()).isEqualTo("EMITIDO");
    }

    @Test
    public void testFindById() {
        GuiaDespachoModel guia = new GuiaDespachoModel();
        guia.setFecEmisionGuia(LocalDate.now());
        guia.setDescripcionGuia("Find by ID target");
        guia.setEstadoGuia("PENDIENTE");
        guia.setIdProveedor(1);
        guia.setIdSucursal(1);

        GuiaDespachoModel saved = guiaDespachoService.addGuia(guia);
        GuiaDespachoModel found = guiaDespachoService.findById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getDescripcionGuia()).isEqualTo("Find by ID target");
    }

    @Test
    public void testDeleteById() {
        GuiaDespachoModel guia = new GuiaDespachoModel();
        guia.setFecEmisionGuia(LocalDate.now());
        guia.setDescripcionGuia("To be deleted");
        guia.setEstadoGuia("EN_TRANSITO");
        guia.setIdProveedor(3);
        guia.setIdSucursal(3);

        GuiaDespachoModel saved = guiaDespachoService.addGuia(guia);
        Integer id = saved.getId();

        boolean deleted = guiaDespachoService.deleteById(id);
        assertThat(deleted).isTrue();

        GuiaDespachoModel found = guiaDespachoService.findById(id);
        assertThat(found).isNull();
    }
}
