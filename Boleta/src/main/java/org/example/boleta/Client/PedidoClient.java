package org.example.boleta.Client;


import org.example.boleta.DTO.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Pedido")
public interface PedidoClient {

    @GetMapping("/api/pedido/{id}")
    PedidoDTO getPedido(@PathVariable Integer id);
}
