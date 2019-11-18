/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.control;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.AtributoAsiento;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sucursal;

/**
 *
 * @author root
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }
    
        public List<Pelicula> FindById(Integer id) {
        if (em != null) {
            try {
                if (id != 0) {
                    Query consulta = em.createQuery("SELECT a FROM AtributoAsiento a WHERE a.atributoAsientoPK.idAsiento = "+id);
                    if (consulta != null && consulta.getResultList() != Collections.EMPTY_LIST) {
                        return consulta.getResultList();
                    }else{
                        System.out.println("No se econtraron datos con el id "+id);
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
    
}
