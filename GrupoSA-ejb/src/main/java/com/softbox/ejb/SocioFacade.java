/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Socio;
import com.softbox.entity.Usuario;
import com.softbox.exception.PasswordInvalido;
import com.softbox.exception.ScoutException;
import com.softbox.exception.UsuarioNoExiste;
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
public class SocioFacade extends AbstractFacade<Socio> implements SocioFacadeLocal {

    @PersistenceContext(unitName = "GrupoSA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFacade() {
        super(Socio.class);
    }

    @Override
    public Socio comprobarLogin(Socio u) throws ScoutException {
        Socio user = findByEmail(u);

        if (user != null) {
            if (!user.getPass().equals(u.getPass())) {
                throw new PasswordInvalido();
            }
        } else {
            throw new UsuarioNoExiste();
        }
        
        return user;
    }

    private Socio findByEmail(Socio u) {

        TypedQuery<Socio> q = em.createQuery("SELECT s FROM Socio s where s.email = :femail", Socio.class);
        q.setParameter("femail", u.getEmail());
        List<Socio> lista = q.getResultList();
        Socio s = null;
        
        if(lista.size() == 1)
            s = lista.get(0);
        
        return s;
        

    }
}
