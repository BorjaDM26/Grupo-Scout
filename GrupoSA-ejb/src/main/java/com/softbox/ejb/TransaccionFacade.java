/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Transaccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class TransaccionFacade extends AbstractFacade<Transaccion> implements TransaccionFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransaccionFacade() {
        super(Transaccion.class);
    }
    
}