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
    public void comprobarLogin(Socio u) throws ScoutException {
        Socio user = findByEmail(u);

        if (user != null) {
            if (!user.getPass().equals(u.getPass())) {
                throw new PasswordInvalido();
            }
        } else {
            throw new UsuarioNoExiste();
        }
    }

    private Socio findByEmail(Socio u) {
        /*Query qu = em.createNativeQuery("SELECT S.* FROM SOCIO S LEFT JOIN USUARIO U ON U.ID_USUARIO=S.ID_USUARIO WHERE U.EMAIL='" + u.getEmail() + "'");
        List<Object[]> lista = qu.getResultList();
        if (lista.size() <= 0) {
            return null;
        } else {
            Long idSocio = (Long) lista.get(0)[0];
            Socio s = new Socio();
            Usuario user = em.find(Usuario.class, 1L);

            return s;
        }*/
        TypedQuery<Socio> q = em.createQuery("SELECT s FROM Socio s where s.email = :femail", Socio.class);
        q.setParameter("femail", u.getEmail());
        List<Socio> lista = q.getResultList();
        Socio s = null;
        if(lista.size()>0) s = lista.get(0);
        
        return s;
        

    }
}
