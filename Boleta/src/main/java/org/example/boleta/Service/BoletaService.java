package org.example.boleta.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.boleta.Client.PedidoClient;
import org.example.boleta.Model.Boleta;
import org.example.boleta.Repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private PedidoClient pedidoClient;

    public Boleta registrar(Boleta boleta){
        try{
            log.info("Intentando registrar boleta para pedido {}", boleta.getIdPedido());

            pedidoClient.getPedido(boleta.getIdPedido());

            Boleta boletaGuardada = boletaRepository.save(boleta);
            log.info("Boleta registrada correctamente con id {}", boletaGuardada.getIdBoleta());
            return boletaGuardada;

        } catch (Exception e){
            log.error("Error al registrar boleta para pedido {}", boleta.getIdPedido(), e);
            return null;
        }
    }

    public List<Boleta> listarTodas(){
        try{
            log.info("Listando todas las boletas");
            List<Boleta> lista = boletaRepository.findAll();
            log.info("Se encontraron {} boletas", lista.size());
            return lista;

        } catch (Exception e){
            log.error("Error al listar boletas", e);
            return List.of();
        }
    }

    public List<Boleta> buscarPorFecha(LocalDate fechaCreacionBoleta){
        try{
            log.info("Buscando boletas por fecha {}", fechaCreacionBoleta);
            List<Boleta> lista = boletaRepository.findByFecCreacionBoleta(fechaCreacionBoleta);
            log.info("Se encontraron {} boletas para la fecha {}", lista.size(), fechaCreacionBoleta);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar boletas por fecha {}", fechaCreacionBoleta, e);
            return List.of();
        }
    }

    public List<Boleta> buscarPorMetodoPago(String metodoPagoBoleta){
        try{
            log.info("Buscando boletas por método de pago {}", metodoPagoBoleta);
            List<Boleta> lista = boletaRepository.findByMetodoPagoBoleta(metodoPagoBoleta);
            log.info("Se encontraron {} boletas con método de pago {}", lista.size(), metodoPagoBoleta);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar boletas por método de pago {}", metodoPagoBoleta, e);
            return List.of();
        }
    }

    public List<Boleta> buscarPorIdPedido(Integer idPedido){
        try{
            log.info("Buscando boleta(s) por idPedido {}", idPedido);

            pedidoClient.getPedido(idPedido);

            List<Boleta> lista = boletaRepository.findByIdPedido(idPedido);
            log.info("Se encontraron {} boleta(s) asociadas al pedido {}", lista.size(), idPedido);
            return lista;

        } catch (Exception e){
            log.error("Error al buscar boleta(s) por idPedido {}", idPedido, e);
            return List.of();
        }
    }

    public Boleta buscarPorId(Integer idBoleta){
        try{
            log.info("Buscando boleta por id {}", idBoleta);
            Boleta boleta = boletaRepository.findById(idBoleta).orElse(null);

            if (boleta == null){
                log.warn("No se encontró boleta con id {}", idBoleta);
            } else {
                log.info("Boleta encontrada con id {}", idBoleta);
            }

            return boleta;

        } catch (Exception e){
            log.error("Error al buscar boleta por id {}", idBoleta, e);
            return null;
        }
    }

    public Boleta modificarPorId(Integer idBoleta, Boleta boletaModificada){
        try{
            log.info("Intentando modificar boleta con id {}", idBoleta);

            Boleta boletaOriginal = buscarPorId(idBoleta);
            if (boletaOriginal == null){
                log.warn("No se puede modificar. No existe boleta con id {}", idBoleta);
                return null;
            }

            pedidoClient.getPedido(boletaModificada.getIdPedido());

            boletaOriginal.setFecCreacionBoleta(boletaModificada.getFecCreacionBoleta());
            boletaOriginal.setValorTotalBoleta(boletaModificada.getValorTotalBoleta());
            boletaOriginal.setMetodoPagoBoleta(boletaModificada.getMetodoPagoBoleta());
            boletaOriginal.setIdPedido(boletaModificada.getIdPedido());

            Boleta boletaActualizada = boletaRepository.save(boletaOriginal);
            log.info("Boleta modificada correctamente con id {}", idBoleta);
            return boletaActualizada;

        } catch (Exception e){
            log.error("Error al modificar boleta con id {}", idBoleta, e);
            return null;
        }
    }

    public boolean eliminarPorId(Integer idBoleta){
        try{
            log.info("Intentando eliminar boleta con id {}", idBoleta);

            Boleta boleta = buscarPorId(idBoleta);
            if (boleta == null){
                log.warn("No se puede eliminar. No existe boleta con id {}", idBoleta);
                return false;
            }

            boletaRepository.deleteById(idBoleta);
            log.info("Boleta eliminada correctamente con id {}", idBoleta);
            return true;

        } catch (Exception e){
            log.error("Error al eliminar boleta con id {}", idBoleta, e);
            return false;
        }
    }
}