/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Cuota;
import com.softbox.entity.Evento;
import com.softbox.entity.Pago_Cuota;
import com.softbox.entity.Pago_Evento;
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
@Named(value = "pagosBB")
@SessionScoped
public class pagosBB implements Serializable{
    
    private Long sigIdPagoEvento = Long.parseLong("1");
    private Long sigIdPagoCuota = Long.parseLong("1");
    private List<Pago_Cuota> pagosCuotas;
    private List<Pago_Evento> pagosEventos;
    private Pago_Cuota pago_cuota = new Pago_Cuota();
    private Pago_Evento pago_evento= new Pago_Evento();
    private boolean modo;

    /**
     * Creates a new instance of sociosBB
     */
    public pagosBB() {
        modo= true;
        pagosEventos=new ArrayList<Pago_Evento>();
        Pago_Evento pagoEvento1=new Pago_Evento();
        
        pagosCuotas = new ArrayList<Pago_Cuota>();
        Pago_Cuota pagoCuota1 = new Pago_Cuota();
        
        Cuota cuota=new Cuota();
        cuota.setId_Cuota(Long.parseLong("1"));
        cuota.setNombre("Cuota 1 ");
        
        Socio socio= new Socio();
        socio.setId_Socio(Long.parseLong("1"));
        socio.setNombre("Ricardo");
        socio.setApellidos("Romero");
        
        Evento evento=new Evento();
        evento.setId_evento(Long.parseLong("1"));
        evento.setNombre("Evento 1");
        
        pagoEvento1.setId_Pago(sigIdPagoEvento++);
        pagoEvento1.setImporte(Float.parseFloat("30.99"));
        pagoEvento1.setFecha(Date.valueOf("2022-12-01"));
        pagoEvento1.setEvento(evento);
        pagoEvento1.setSocio(socio);
        
        pagoCuota1.setId_Pago(sigIdPagoCuota++);
        pagoCuota1.setImporte(Float.parseFloat("56.99"));
        pagoCuota1.setFecha(Date.valueOf("2018-09-13"));
        pagoCuota1.setCuota(cuota);
        pagoCuota1.setSocio(socio);

        pagosCuotas.add(pagoCuota1);
        pagosEventos.add(pagoEvento1);
        
        Pago_Evento pagoEvento2=new Pago_Evento();
        pagoEvento2.setId_Pago(sigIdPagoEvento++);
        pagoEvento2.setImporte(Float.parseFloat("0.99"));
        pagoEvento2.setFecha(Date.valueOf("2019-05-12"));
        pagoEvento2.setEvento(evento);
        pagoEvento2.setSocio(socio);
        
        Pago_Cuota pagoCuota2 = new Pago_Cuota();
        pagoCuota2.setId_Pago(sigIdPagoCuota++);
        pagoCuota2.setImporte(Float.parseFloat("100.99"));
        pagoCuota2.setFecha(Date.valueOf("2022-04-29"));
        pagoCuota2.setCuota(cuota);
        pagoCuota2.setSocio(socio);
        
        pagosCuotas.add(pagoCuota2);
        pagosEventos.add(pagoEvento2);
        
        
    }

    public List<Pago_Evento> getPagosEventos() {
        return pagosEventos;
    }

    public void setPagosEventos(List<Pago_Evento> pagosEventos) {
        this.pagosEventos = pagosEventos;
    }

    public Pago_Evento getPago_evento() {
        return pago_evento;
    }

    public void setPago_evento(Pago_Evento pago_evento) {
        this.pago_evento = pago_evento;
    }

    
    public List<Pago_Cuota> getPagosCuotas() {
           return pagosCuotas; 
    
    }

    public void setPagosCuotas(List<Pago_Cuota> pagosCuotas) {
        this.pagosCuotas = pagosCuotas;
    }

    public Pago_Cuota getPago_Cuota() {
        return pago_cuota;
    }

    public void setPago_Cuota(Pago_Cuota pago_cuota) {
        this.pago_cuota = pago_cuota;
    }
    
    //Accede a la vista de creación de pagosEventos
    public String newPagoCuota(){
        modo=true;
        Socio socio=new Socio();
        Cuota cuota=new Cuota();
        pago_cuota = new Pago_Cuota();
        pago_cuota.setSocio(socio);
        pago_cuota.setCuota(cuota);
        return "crearPago.xhtml";
    }
    
    public String newPagoEvento(){
        modo=false;
        Socio socio=new Socio();
        Evento evento=new Evento();
        pago_evento=new Pago_Evento();
        pago_evento.setSocio(socio);
        pago_evento.setEvento(evento);
        return "crearPago.xhtml";
    }
    
    //Crea el pago_evento con los datos proporcionado en la vista de creación
    public String createPagoCuota(){
        pago_cuota.setId_Pago(sigIdPagoCuota++);        
        pagosCuotas.add(pago_cuota);
        return "listarPagos.xhtml";
    }
    
    public String createPagoEvento(){
        pago_evento.setId_Pago(sigIdPagoEvento++);        
        pagosEventos.add(pago_evento);
        return "listarPagos.xhtml";
    }
    public String updatePagoCuota(Pago_Cuota pago_cuota){
        this.pago_cuota = pago_cuota;
        return "modificarPago.xhtml";
    }
    public String updatePagoEvento(Pago_Evento pago_evento){
        this.pago_evento = pago_evento;
        return "modificarPago.xhtml";
    }
    
    public String readPagoCuota(Pago_Cuota pago_cuota){
        this.pago_cuota = pago_cuota;
        return "consultarPago.xhtml";
    }
    public String readPagoEvento(Pago_Evento pago_evento){
        this.pago_evento = pago_evento;
        return "consultarPago.xhtml";
    }
    
    public String deletePagoCuota(Pago_Cuota soc){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < pagosCuotas.size()){
            if(pagosCuotas.get(i).getId_Pago().compareTo(soc.getId_Pago())==0){
                pagosCuotas.remove(i);
                borrado=true;
            }
            i++;
        }
        return "listarPagos.xhtml";
    }
    public String deletePagoEvento(Pago_Evento soc){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < pagosEventos.size()){
            if(pagosEventos.get(i).getId_Pago().compareTo(soc.getId_Pago())==0){
                pagosEventos.remove(i);
                borrado=true;
            }
            i++;
        }
        return "listarPagos.xhtml";
    }
    
    public void cambiarModo(){
        modo=!modo;
    }
    public boolean getModo(){
        return modo;
    }
    public void setModo(boolean modo){
        this.modo=modo;
    }
}
