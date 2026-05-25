package org.example.pedido.Service;

import org.example.pedido.Model.PedidoModel;
import org.example.pedido.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoModel>  findAll(){
        return pedidoRepository.findAll();
    }

    public PedidoModel findById(Integer id){
        return pedidoRepository.findById(id).get();
    }

    public PedidoModel addPedido(PedidoModel pedido){
        return pedidoRepository.save(pedido);
    }

    public boolean deleteById(Integer id){
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
