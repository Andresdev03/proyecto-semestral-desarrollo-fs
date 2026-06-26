package org.example.factura;

import org.example.factura.Model.FacturaModel;
import org.example.factura.Service.FacturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class FacturaServiceTest {

    @Autowired
    private FacturaService facturaService;

    @Test
    public void testAddFactura() {
        FacturaModel factura = new FacturaModel();
        factura.setFecEmision(LocalDate.now());
        factura.setRutReceptor("12345678-9");
        factura.setRazonSocial("Empresa Cafetera S.A.");
        factura.setGiroReceptor("Venta de cafe");
        factura.setMontoNeto(new BigDecimal("10000"));
        factura.setMontoIva(new BigDecimal("1900"));
        factura.setMontoTotal(new BigDecimal("11900"));
        factura.setIdPedido(1);
        factura.setIdProveedor(1);

        FacturaModel saved = facturaService.addFactura(factura);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getRazonSocial()).isEqualTo("Empresa Cafetera S.A.");
    }

    @Test
    public void testGetFactura() {
        FacturaModel factura = new FacturaModel();
        factura.setFecEmision(LocalDate.now());
        factura.setRutReceptor("98765432-1");
        factura.setRazonSocial("Cafeteria El Grano Ltda");
        factura.setGiroReceptor("Giro Cafe");
        factura.setMontoNeto(new BigDecimal("20000"));
        factura.setMontoIva(new BigDecimal("3800"));
        factura.setMontoTotal(new BigDecimal("23800"));
        factura.setIdPedido(2);
        factura.setIdProveedor(2);

        FacturaModel saved = facturaService.addFactura(factura);
        FacturaModel found = facturaService.getFactura(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getRazonSocial()).isEqualTo("Cafeteria El Grano Ltda");
    }

    @Test
    public void testFindByRutReceptor() {
        FacturaModel factura = new FacturaModel();
        factura.setFecEmision(LocalDate.now());
        factura.setRutReceptor("77777777-7");
        factura.setRazonSocial("Comercializadora Test");
        factura.setGiroReceptor("Comercio");
        factura.setMontoNeto(new BigDecimal("5000"));
        factura.setMontoIva(new BigDecimal("950"));
        factura.setMontoTotal(new BigDecimal("5950"));
        factura.setIdPedido(3);
        factura.setIdProveedor(3);

        facturaService.addFactura(factura);

        List<FacturaModel> found = facturaService.findByRutReceptor("77777777-7");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getRazonSocial()).isEqualTo("Comercializadora Test");
    }

    @Test
    public void testDeleteFactura() {
        FacturaModel factura = new FacturaModel();
        factura.setFecEmision(LocalDate.now());
        factura.setRutReceptor("88888888-8");
        factura.setRazonSocial("Distribuidora Cafes");
        factura.setGiroReceptor("Giro Distribucion");
        factura.setMontoNeto(new BigDecimal("15000"));
        factura.setMontoIva(new BigDecimal("2850"));
        factura.setMontoTotal(new BigDecimal("17850"));
        factura.setIdPedido(4);
        factura.setIdProveedor(4);

        FacturaModel saved = facturaService.addFactura(factura);
        Integer id = saved.getId();

        boolean deleted = facturaService.deleteFactura(id);
        assertThat(deleted).isTrue();

        FacturaModel found = facturaService.getFactura(id);
        assertThat(found).isNull();
    }
}
