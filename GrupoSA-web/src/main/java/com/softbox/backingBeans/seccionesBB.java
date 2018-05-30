/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Seccion;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author migue
 */
@Named(value = "seccionesBB")
@RequestScoped
public class seccionesBB {
    private String seccion;
    private Seccion sec;
    /**
     * Creates a new instance of seccionesBB
     */
    public seccionesBB() {
        sec = new Seccion();
        sec.setDescripcion("Esta es la sección de los castores, a la cual se pueden apuntar niños desde los X hasta los Y años.");
        sec.setNombre("castores");
    }

    public Seccion getSec() {
        return sec;
    }

    public void setSec(Seccion sec) {
        this.sec = sec;
    }
    
    public String cargarLista(String seccion){
        this.seccion = seccion;
        return "eventos.xhtml";
    }
    
    public String getSeccion(){
        return seccion;
    }
}
