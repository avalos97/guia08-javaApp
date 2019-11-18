/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sala;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sucursal;
import ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.control.SalaFacade;
import ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.control.SucursalFacade;

/**
 *
 * @author root
 */
@Named
@SessionScoped
public class DashboardBean implements Serializable {

    @Inject
    SalaFacade salafacade;
    @Inject
    SucursalFacade sucursalFacade;

    Sala salita;
    List<Pelicula> listaPeli = new ArrayList<>();

    @PostConstruct
    public void iniciar(){
        salita = new Sala();
    }
    
    public void LlenarLista(){
        
    }
    
    public List<Sucursal> autoComplete(String consulta){
        List<Sucursal> allSucursal = sucursalFacade.findAll();
        List<Sucursal> filtradaSucursal = new ArrayList<>();
        
        for (int i = 0; i < allSucursal.size(); i++) {
            Sucursal skin = allSucursal.get(i);
            if(skin.getNombre().toLowerCase().contains(consulta)){
                filtradaSucursal.add(skin);
            }  
        }
        return filtradaSucursal;
    }

}
