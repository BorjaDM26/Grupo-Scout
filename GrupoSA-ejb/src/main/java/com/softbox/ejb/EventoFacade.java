/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Evento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class EventoFacade extends AbstractFacade<Evento> implements EventoFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    @Override
    public List<Evento> findBySeccion(Long id_Seccion){
        TypedQuery tq = em.createQuery("SELECT e FROM Evento e WHERE e.seccion.id_seccion = :fseccion", Evento.class).setParameter("fseccion", id_Seccion);
        List<Evento> lista = tq.getResultList();
        return lista;
    }  
    
    @Override
    public boolean usuarioInscrito(Long id_Socio, Long id_Evento){
        Query q = em.createNativeQuery("SELECT * FROM Inscripcion i where i.inscripcion_socio_fk="+id_Socio+" AND i.inscripcion_evento_fk ="+id_Evento+"");
        List<Object> lista = q.getResultList();
        
        boolean encontrado=false;
        
        if(lista != null){
            encontrado = lista.size()==1;
        }
        return encontrado;
    }
    
    @Override
    public void inscribirSocio(Long id_Socio, Long id_Evento){
         em.createNativeQuery("INSERT INTO INSCRIPCION VALUES("+id_Socio+","+id_Evento+")").executeUpdate();
    }
}
