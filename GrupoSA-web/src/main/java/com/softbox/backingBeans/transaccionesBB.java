/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Evento;
import com.softbox.entity.Seccion;
import com.softbox.entity.Socio;
import com.softbox.entity.Transaccion;
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
@Named(value = "transaccionesBB")
@SessionScoped
public class transaccionesBB implements Serializable{
    
    private Long sigIdTransaccion = Long.parseLong("1");   
    private List<Transaccion> transacciones;
    private Transaccion transaccion = new Transaccion();

    /**
     * Creates a new instance of sociosBB
     */
    public transaccionesBB() {
        
        Socio socio1=new Socio();
        socio1.setId_Socio(Long.parseLong("1"));
        socio1.setNombre("Socio 1");
        socio1.setApellidos("Risas");
        Socio socio2=new Socio();
        socio2.setId_Socio(Long.parseLong("2"));
        socio2.setNombre("Socio 2");
        socio2.setApellidos("No tan risas");
        
        Socio socio3=new Socio();
        socio3.setId_Socio(Long.parseLong("3"));
        socio3.setNombre("Socio 3");
        socio3.setApellidos("McDonalds");
        

        transacciones = new ArrayList<Transaccion>();
        Transaccion transaccion1=new Transaccion();
        transaccion1.setId_Transaccion(sigIdTransaccion++);
        transaccion1.setConcepto("Pago arreglos oficina");
        transaccion1.setFecha(Date.valueOf("2022-12-01"));
        transaccion1.setImporte(Float.parseFloat("-210.99"));
        transaccion1.setSocio(socio1);
        
        Transaccion transaccion2=new Transaccion();
        transaccion2.setId_Transaccion(sigIdTransaccion++);
        transaccion2.setConcepto("Donación");
        transaccion2.setFecha(Date.valueOf("2017-01-01"));
        transaccion2.setImporte(Float.parseFloat("1000.00"));
        transaccion2.setSocio(socio2);
        
        Transaccion transaccion3=new Transaccion();
        transaccion3.setId_Transaccion(sigIdTransaccion++);
        transaccion3.setConcepto("Pago herramientas para eventos");
        transaccion3.setFecha(Date.valueOf("2018-05-11"));
        transaccion3.setImporte(Float.parseFloat("-70.99"));
        transaccion3.setSocio(socio3);
        
        transacciones.add(transaccion1);
        transacciones.add(transaccion2);
        transacciones.add(transaccion3);
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
    
    //Accede a la vista de creación de eventos
    public String newTransaccion(){
        transaccion = new Transaccion();
        Socio socio= new Socio();
        transaccion.setSocio(socio);
        return "crearTransaccion.xhtml";
    }
    
    //Crea el evento con los datos proporcionado en la vista de creación
    public String createTransaccion(){
        transaccion.setId_Transaccion(sigIdTransaccion++);        
        transacciones.add(transaccion);
        return "listarTransacciones.xhtml";
    }
    
    public String updateTransaccion(Transaccion transaccion){
        this.transaccion = transaccion;
        return "modificarTransaccion.xhtml";
    }
    
    public String readTransaccion(Transaccion transaccion){
        this.transaccion = transaccion;
        return "consultarTransaccion.xhtml";
    }
    
    public String deleteTransaccion(Transaccion transaccion){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < transacciones.size()){
            if(transacciones.get(i).getId_Transaccion().compareTo(transaccion.getId_Transaccion())==0){
                transacciones.remove(i);
                borrado=true;
            }
            i++;
        }
        return "listarTransacciones.xhtml";
    }
}
