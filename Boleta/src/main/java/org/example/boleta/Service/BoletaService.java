package org.example.boleta.Service;

import org.example.boleta.Model.Boleta;
import org.example.boleta.Repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public Boleta registrar(Boleta boleta){
        return boletaRepository.save(boleta);
    }


    public List<Boleta> listarTodas(){
        return boletaRepository.findAll();
    }

    public List<Boleta> buscarPorFecha(LocalDate fechaCreacionBoleta){
        return boletaRepository.findByFecCreacionBoleta(fechaCreacionBoleta);
    }

    public List<Boleta> buscarPorMetodoPago(String metodoPagoBoleta){
        return boletaRepository.findByMetodoPagoBoleta(metodoPagoBoleta);
    }

    public Boleta buscarPorId(Integer idBoleta){
        return boletaRepository.findById(idBoleta).orElse(null);
    }

    public Boleta modificarPorId(Integer idBoleta, Boleta boletaModificada){
        Boleta boletaOriginal = buscarPorId(idBoleta);
        if (boletaOriginal == null){
            return null;
        }

        boletaOriginal.setFecCreacionBoleta(boletaModificada.getFecCreacionBoleta());
        boletaOriginal.setValorTotalBoleta(boletaModificada.getValorTotalBoleta());
        boletaOriginal.setMetodoPagoBoleta(boletaModificada.getMetodoPagoBoleta());
        boletaOriginal.setIdPedido(boletaModificada.getIdPedido());
        return boletaRepository.save(boletaOriginal);



    }

    public boolean eliminarPorId(Integer idBoleta){
        Boleta boleta = buscarPorId(idBoleta);
        if (boleta == null){
            return false;
        }

        boletaRepository.deleteById(idBoleta);
        return true;
    }


}
