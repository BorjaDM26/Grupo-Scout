/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Seccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class SeccionFacade extends AbstractFacade<Seccion> implements SeccionFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeccionFacade() {
        super(Seccion.class);
    }
    
    public Seccion findByNombre(String nombre){
        TypedQuery<Seccion> q = em.createQuery("SELECT S FROM Seccion S WHERE S.nombre = :fnombre", Seccion.class);
        q.setParameter("fnombre", nombre);
        return q.getResultList().get(0);
    }
}
