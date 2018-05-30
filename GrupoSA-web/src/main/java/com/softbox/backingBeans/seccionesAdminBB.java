/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Evento;
import com.softbox.entity.Seccion;
import com.softbox.entity.Socio;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Borja Delgado
 */
@Named(value = "seccionesAdminBB")
@SessionScoped
public class seccionesAdminBB implements Serializable{
    
    private Long sigIdSeccion = Long.parseLong("1");   
    private List<Seccion> secciones;
    private Seccion seccion = new Seccion();

    /**
     * Creates a new instance of sociosBB
     */
    public seccionesAdminBB() {
        List<Evento> eventos1= new ArrayList<Evento>();
        Evento evento1=new Evento();
        evento1.setId_evento(Long.parseLong("1"));
        evento1.setNombre("Evento 1");
        Evento evento2=new Evento();
        evento2.setId_evento(Long.parseLong("2"));
        evento2.setNombre("Evento 2");
        
        eventos1.add(evento1);
        eventos1.add(evento2);
        
        List<Evento> eventos2=new ArrayList<Evento>();
        Evento evento3=new Evento();
        evento3.setId_evento(Long.parseLong("3"));
        evento3.setNombre("Evento 3");
        
        eventos2.add(evento3);
        
        
        List<Socio> socios1= new ArrayList<Socio>();
        Socio socio1=new Socio();
        socio1.setId_Socio(Long.parseLong("1"));
        socio1.setNombre("Socio 1");
        socio1.setApellidos("Risas");
        Socio socio2=new Socio();
        socio2.setId_Socio(Long.parseLong("2"));
        socio2.setNombre("Socio 2");
        socio2.setApellidos("No tan risas");
        
        socios1.add(socio1);
        socios1.add(socio2);
        
        List<Socio> socios2=new ArrayList<Socio>();
        Socio socio3=new Socio();
        socio3.setId_Socio(Long.parseLong("3"));
        socio3.setNombre("Socio 3");
        socio3.setApellidos("McDonalds");
        socios2.add(socio3);
        
        secciones = new ArrayList<Seccion>();
        Seccion seccion1 = new Seccion();
        seccion1.setId_seccion(sigIdSeccion++);
        seccion1.setNombre("Seccion 1");
        seccion1.setEdad_max(22);
        seccion1.setEdad_min(9);
        seccion1.setDescripcion("Es una pasada.");
        seccion1.setEventos(eventos1);
        seccion1.setSocios(socios1);
        
        
        secciones.add(seccion1);
        
        Seccion seccion2 = new Seccion();
        
        seccion2.setId_seccion(sigIdSeccion++);
        seccion2.setNombre("Seccion 2");
        seccion2.setEdad_max(50);
        seccion2.setEdad_min(5);
        seccion2.setDescripcion("Madre mia.");
        seccion2.setEventos(eventos2);
        seccion2.setSocios(socios2);
        
        secciones.add(seccion2);
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
    //Accede a la vista de creación de eventos
    public String newSeccion(){
        seccion = new Seccion();
        return "crearSeccion.xhtml";
    }
    
    //Crea el evento con los datos proporcionado en la vista de creación
    public String createSeccion(){
        seccion.setId_seccion(sigIdSeccion++);        
        secciones.add(seccion);
        return "listarSecciones.xhtml";
    }
    
    public String updateSeccion(Seccion soc){
        seccion = soc;
        return "modificarSeccion.xhtml";
    }
    
    public String readSeccion(Seccion soc){
        seccion = soc;
        return "consultarSeccion.xhtml";
    }
    
    public String deleteSeccion(Seccion soc){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < secciones.size()){
            if(secciones.get(i).getId_seccion().compareTo(soc.getId_seccion())==0){
                secciones.remove(i);
                borrado=true;
            }
            i++;
        }
        return "listarSecciones.xhtml";
    }
}
