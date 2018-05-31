/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Socio;
import com.softbox.entity.Usuario;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author migue
 */
@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Socio socio;

    public void setUsuario(Socio socio) {
        this.socio = socio;
    }

    public Socio getSocio() {
        return socio;
    }

    public String logout() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        if (socio != null) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.getExternalContext().invalidateSession();
            socio = null;
        }
        return "index.xhtml";
    }

    public String logoutAdmin() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        if (socio != null) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.getExternalContext().invalidateSession();
            socio = null;
        }
        return "loginAdmin.xhtml";
    }

    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }

}
