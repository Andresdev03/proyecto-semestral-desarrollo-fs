package org.example.proveedor;

import org.example.proveedor.Model.ProveedorModel;
import org.example.proveedor.Service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    @Test
    public void testCreateProveedor() {
        ProveedorModel proveedor = new ProveedorModel();
        proveedor.setRutProveedor("123456789");
        proveedor.setDvProveedor('9');
        proveedor.setEmailProveedor("proveedor@test.com");
        proveedor.setNombreProveedor("Proveedor Test");
        proveedor.setDireccionProveedor("Direccion Test 123");
        proveedor.setTelefonoProveedor("+56912345678");
        proveedor.setIdRegion(1);
        proveedor.setIdComuna(1);

        ProveedorModel saved = proveedorService.createProveedor(proveedor);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNombreProveedor()).isEqualTo("Proveedor Test");
    }

    @Test
    public void testGetProveedorById() {
        ProveedorModel proveedor = new ProveedorModel();
        proveedor.setRutProveedor("876543210");
        proveedor.setDvProveedor('K');
        proveedor.setEmailProveedor("proveedor2@test.com");
        proveedor.setNombreProveedor("Proveedor Test 2");
        proveedor.setDireccionProveedor("Direccion Test 456");
        proveedor.setTelefonoProveedor("+56987654321");
        proveedor.setIdRegion(2);
        proveedor.setIdComuna(2);

        ProveedorModel saved = proveedorService.createProveedor(proveedor);
        ProveedorModel found = proveedorService.getProveedorById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getNombreProveedor()).isEqualTo("Proveedor Test 2");
    }

    @Test
    public void testGetTodosProveedores() {
        ProveedorModel proveedor = new ProveedorModel();
        proveedor.setRutProveedor("112233444");
        proveedor.setDvProveedor('5');
        proveedor.setEmailProveedor("proveedor3@test.com");
        proveedor.setNombreProveedor("Proveedor Test 3");
        proveedor.setDireccionProveedor("Direccion Test 789");
        proveedor.setTelefonoProveedor("+56911223344");
        proveedor.setIdRegion(3);
        proveedor.setIdComuna(3);

        proveedorService.createProveedor(proveedor);

        List<ProveedorModel> list = proveedorService.getTodosProveedores();
        assertThat(list).isNotEmpty();
        assertThat(list.stream().anyMatch(p -> p.getNombreProveedor().equals("Proveedor Test 3"))).isTrue();
    }

    @Test
    public void testDeleteProveedor() {
        ProveedorModel proveedor = new ProveedorModel();
        proveedor.setRutProveedor("121212121");
        proveedor.setDvProveedor('K');
        proveedor.setEmailProveedor("proveedordelete@test.com");
        proveedor.setNombreProveedor("Proveedor Delete Test");
        proveedor.setDireccionProveedor("Direccion Delete 123");
        proveedor.setTelefonoProveedor("+56999999999");
        proveedor.setIdRegion(4);
        proveedor.setIdComuna(4);

        ProveedorModel saved = proveedorService.createProveedor(proveedor);
        Integer id = saved.getId();

        boolean deleted = proveedorService.deleteById(id);
        assertThat(deleted).isTrue();

        ProveedorModel found = proveedorService.getProveedorById(id);
        assertThat(found).isNull();
    }
}
