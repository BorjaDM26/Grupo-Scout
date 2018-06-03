/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Notificacion_Documento;
import com.softbox.entity.Notificacion_Evento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class Notificacion_EventoFacade extends AbstractFacade<Notificacion_Evento> implements Notificacion_EventoFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Notificacion_EventoFacade() {
        super(Notificacion_Evento.class);
    }
    
    public Notificacion_Evento getNotById (Long id_not){
        TypedQuery<Notificacion_Evento> query = em.createQuery("Select N from Notificacion_Evento N where N.id_not_evento = :fid_not", Notificacion_Evento.class);
        query.setParameter("fid_not", id_not);
        Notificacion_Evento notificacion = query.getResultList().get(0);
        return notificacion;
    }
    
    public List<Notificacion_Evento> findByIdUser (Long id_user){
        TypedQuery<Notificacion_Evento> query = em.createQuery("Select N from Notificacion_Evento N where N.socio.id_Usuario  = :fid_user", Notificacion_Evento.class);
        query.setParameter("fid_user", id_user);
        return query.getResultList();
    }
}
