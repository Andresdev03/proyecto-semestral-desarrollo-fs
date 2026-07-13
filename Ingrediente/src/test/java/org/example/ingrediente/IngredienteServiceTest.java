package org.example.ingrediente;

import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Service.IngredienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class IngredienteServiceTest {

    @Autowired
    private IngredienteService ingredienteService;

    @Test
    public void testAddIngrediente() {
        IngredienteModel ingrediente = new IngredienteModel();
        ingrediente.setNombreIngrediente("Harina");
        ingrediente.setDescripcionIngrediente("Harina de trigo");
        ingrediente.setUnidadMedida("KG");
        ingrediente.setStockIngrediente(new BigDecimal("10.5"));
        ingrediente.setPrecioUnitario(new BigDecimal("1200"));
        ingrediente.setIdProveedor(1);

        IngredienteModel saved = ingredienteService.addIngrediente(ingrediente);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNombreIngrediente()).isEqualTo("Harina");
    }

    @Test
    public void testGetIngredienteById() {
        IngredienteModel ingrediente = new IngredienteModel();
        ingrediente.setNombreIngrediente("Azúcar");
        ingrediente.setDescripcionIngrediente("Azúcar refinada");
        ingrediente.setUnidadMedida("KG");
        ingrediente.setStockIngrediente(new BigDecimal("5.0"));
        ingrediente.setPrecioUnitario(new BigDecimal("1000"));
        ingrediente.setIdProveedor(1);

        IngredienteModel saved = ingredienteService.addIngrediente(ingrediente);
        IngredienteModel found = ingredienteService.getIngredienteById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getNombreIngrediente()).isEqualTo("Azúcar");
    }

    @Test
    public void testDeleteIngrediente() {
        IngredienteModel ingrediente = new IngredienteModel();
        ingrediente.setNombreIngrediente("Café en grano");
        ingrediente.setDescripcionIngrediente("Café arábica");
        ingrediente.setUnidadMedida("KG");
        ingrediente.setStockIngrediente(new BigDecimal("2.5"));
        ingrediente.setPrecioUnitario(new BigDecimal("15000"));
        ingrediente.setIdProveedor(2);

        IngredienteModel saved = ingredienteService.addIngrediente(ingrediente);
        Integer id = saved.getId();

        boolean deleted = ingredienteService.deleteIngrediente(id);
        assertThat(deleted).isTrue();

        IngredienteModel found = ingredienteService.getIngredienteById(id);
        assertThat(found).isNull();
    }
}
