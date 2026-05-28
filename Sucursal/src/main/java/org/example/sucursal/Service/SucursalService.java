package org.example.sucursal.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.sucursal.Model.SucursalModel;
import org.example.sucursal.Repository.SucursalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {
    private static final Logger log = LoggerFactory.getLogger(SucursalService.class);
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<SucursalModel> listarSucursales(){
        return sucursalRepository.findAll();
    }

    public SucursalModel buscarPorId(Integer id){
        return sucursalRepository.findById(id).orElse(null);
    }
    
    public SucursalModel agregarSucursal(SucursalModel nuevo)
    {
        return sucursalRepository.save(nuevo);
    }

    public boolean borrarSucursal(Integer id){
        if (sucursalRepository.existsById(id)){
            sucursalRepository.deleteById(id);
            return true;
        }
        return  false;
    }

    public SucursalModel actualizarSucursal(Integer id, SucursalModel nuevo){
        if (sucursalRepository.existsById(id)){
            SucursalModel sucursal= sucursalRepository.findById(id).orElse(null);
            if(sucursal!=null){
                sucursal.setNombreSucursal(nuevo.getNombreSucursal());
                sucursal.setDireccion(nuevo.getDireccion());
                sucursal.setTelefonoSucursal(nuevo.getTelefonoSucursal());
                sucursal.setEmailSucursal(nuevo.getEmailSucursal());
                sucursal.setFecAperturaSucursal(nuevo.getFecAperturaSucursal());
                sucursal.setIdComuna(nuevo.getIdComuna());
                return  sucursalRepository.save(sucursal);
            }
        }
        return null;
    }

    public  List<SucursalModel> listarPorComuna(Integer idComuna){
        return sucursalRepository.findByIdComuna(idComuna);
    }
}
