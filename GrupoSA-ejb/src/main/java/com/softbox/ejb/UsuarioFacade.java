/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miguel_martin
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    @Override
    public void create(Usuario u){
        em.persist(u);
    }
    @Override
    public void edit(Usuario u){
        em.merge(u);
    }
    
    @Override 
    public void remove(Usuario u){
        em.remove(em.merge(u));
    }
    
    @Override
    public void comprobarLogin(Usuario u){
        Usuario user = em.find(Usuario.class, u.getEmail());
        
        if(user != null){
            if(!user.getPass().equals(u.getPass())){
            }
        }
    }
    
}
