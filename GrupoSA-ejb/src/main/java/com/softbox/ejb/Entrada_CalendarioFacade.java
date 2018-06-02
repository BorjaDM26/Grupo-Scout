/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Entrada_Calendario;
import com.softbox.entity.Socio;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class Entrada_CalendarioFacade extends AbstractFacade<Entrada_Calendario> implements Entrada_CalendarioFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Entrada_CalendarioFacade() {
        super(Entrada_Calendario.class);
    }
    
    public Long getNextId() {
        TypedQuery<BigDecimal> q = (TypedQuery<BigDecimal>) em.createNativeQuery("SELECT seq_count FROM SEQUENCE where seq_name = 'S_ENTCALENDARIO'");
        Query q2 = em.createNativeQuery("UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 1 WHERE SEQ_NAME = 'S_ENTCALENDARIO'");
        q2.executeUpdate();
        return q.getSingleResult().longValue();
    }
    
    public List<Entrada_Calendario> findByIdSocio(Long idSocio) {
//        TypedQuery<Entrada_Calendario> q = (TypedQuery<Entrada_Calendario>) em.createNativeQuery("SELECT E.* FROM Entrada_Calendario E, Socio S WHERE E.SOCIO_ID_USUARIO = S.ID_USUARIO AND S.ID_SOCIO = "+idSocio);
//        return q.getResultList();

//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Entrada_Calendario> cq = cb.createQuery(Entrada_Calendario.class);
//        Root<Entrada_Calendario> ec = cq.from(Entrada_Calendario.class);
//        Root<Socio> sc = cq.from(Socio.class);
//        ParameterExpression<Long> idS = cb.parameter(Long.class);
//        cq.select(ec).where(cb.and(cb.equal(ec.get("socio").get("id_Usuario"), idSocio),cb.equal(sc.get("id_Socio"), idS)));
//        Query q = getEntityManager().createQuery(cq);
//        q.setParameter(idS, idSocio);
//        return getEntityManager().createQuery(cq).getResultList();

        List<Entrada_Calendario> temp = findAll();
        List<Entrada_Calendario> res = new LinkedList<Entrada_Calendario>();
        for(Entrada_Calendario ec : temp){
            if(ec.getSocio().getId_Socio() == idSocio) res.add(ec);
        }
        return res;
    }
}
