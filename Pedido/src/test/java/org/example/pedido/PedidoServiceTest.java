package org.example.pedido;

import org.example.pedido.Model.PedidoModel;
import org.example.pedido.Service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @Test
    public void testAddPedido() {
        PedidoModel pedido = new PedidoModel();
        pedido.setFecPedido(LocalDate.now());
        pedido.setEstadoPedido("CREADO");
        pedido.setTipoPedido("LOCAL");
        pedido.setNumMesa(5);
        pedido.setRunTrabajador("12345678-9");
        pedido.setIdSucursal(1);

        PedidoModel saved = pedidoService.addPedido(pedido);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEstadoPedido()).isEqualTo("CREADO");
    }

    @Test
    public void testFindById() {
        PedidoModel pedido = new PedidoModel();
        pedido.setFecPedido(LocalDate.now());
        pedido.setEstadoPedido("PENDIENTE");
        pedido.setTipoPedido("LLEVAR");
        pedido.setNumMesa(null);
        pedido.setRunTrabajador("98765432-1");
        pedido.setIdSucursal(2);

        PedidoModel saved = pedidoService.addPedido(pedido);
        PedidoModel found = pedidoService.findById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getEstadoPedido()).isEqualTo("PENDIENTE");
    }

    @Test
    public void testDeleteById() {
        PedidoModel pedido = new PedidoModel();
        pedido.setFecPedido(LocalDate.now());
        pedido.setEstadoPedido("TERMINADO");
        pedido.setTipoPedido("LOCAL");
        pedido.setNumMesa(3);
        pedido.setRunTrabajador("11111111-1");
        pedido.setIdSucursal(3);

        PedidoModel saved = pedidoService.addPedido(pedido);
        Integer id = saved.getId();

        boolean deleted = pedidoService.deleteById(id);
        assertThat(deleted).isTrue();

        boolean exists = true;
        try {
            pedidoService.findById(id);
        } catch (Exception e) {
            exists = false;
        }
        assertThat(exists).isFalse();
    }
}
