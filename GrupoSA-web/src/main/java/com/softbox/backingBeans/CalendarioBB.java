/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Entrada_Calendario;
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
@Named(value = "CalendarioBB")
@SessionScoped
public class CalendarioBB implements Serializable{
    
    private Long sigIdEntrada = Long.parseLong("1");
    private List<Entrada_Calendario> calendario;
    private Entrada_Calendario entCal = new Entrada_Calendario();
    private Long buscador = Long.parseLong("0");

    /**
     * Creates a new instance of sociosBB
     */
    public CalendarioBB() {
        calendario = new ArrayList<Entrada_Calendario>();
        Socio socio1 = new Socio();
        socio1.setId_Socio(Long.parseLong("10000"));
        Socio socio2 = new Socio();
        socio2.setId_Socio(Long.parseLong("10001"));
        
        Entrada_Calendario entrada1 = new Entrada_Calendario();
        entrada1.setId_entrada_calendario(sigIdEntrada++);
        entrada1.setCategoria("Ingreso");
        entrada1.setFecha(Date.valueOf(LocalDate.now()));
        entrada1.setDescripcion("Te has unido al Grupo Scout 39 Santo Ángel.");
        entrada1.setSocio(socio1);
        calendario.add(entrada1);
        Entrada_Calendario entrada2 = new Entrada_Calendario();
        entrada2.setId_entrada_calendario(sigIdEntrada++);
        entrada2.setCategoria("Evento");
        entrada2.setFecha(Date.valueOf(LocalDate.now()));
        entrada2.setDescripcion("Evento en la sierra de la Alpujarra.");
        entrada2.setSocio(socio1);
        calendario.add(entrada2);
        Entrada_Calendario entrada3 = new Entrada_Calendario();
        entrada3.setId_entrada_calendario(sigIdEntrada++);
        entrada3.setCategoria("Evento");
        entrada3.setFecha(Date.valueOf(LocalDate.now()));
        entrada3.setDescripcion("Evento en la sierra de la Alpujarra.");
        entrada3.setSocio(socio2);
        calendario.add(entrada3);
        Entrada_Calendario entrada4 = new Entrada_Calendario();
        entrada4.setId_entrada_calendario(sigIdEntrada++);
        entrada4.setCategoria("Insignia");
        entrada4.setFecha(Date.valueOf(LocalDate.now()));
        entrada4.setDescripcion("Has conseguido la insignia de la valentía. Enhorabuena.");
        entrada4.setSocio(socio2);
        calendario.add(entrada4);
        Entrada_Calendario entrada5 = new Entrada_Calendario();
        entrada5.setId_entrada_calendario(sigIdEntrada++);
        entrada5.setCategoria("Insignia");
        entrada5.setFecha(Date.valueOf(LocalDate.now()));
        entrada5.setDescripcion("Has conseguido la insignia de la valentía. Enhorabuena.");
        entrada5.setSocio(socio2);
        calendario.add(entrada5);
    }

    public Long getSigIdEntrada() {
        return sigIdEntrada;
    }

    public void setSigIdEntrada(Long sigIdEntrada) {
        this.sigIdEntrada = sigIdEntrada;
    }

    public List<Entrada_Calendario> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<Entrada_Calendario> calendario) {
        this.calendario = calendario;
    }

    public Entrada_Calendario getEntCal() {
        return entCal;
    }

    public void setEntCal(Entrada_Calendario entCal) {
        this.entCal = entCal;
    }

    public Long getBuscador() {
        return buscador;
    }

    public void setBuscador(Long buscador) {
        this.buscador = buscador;
    }
    
    public List<Entrada_Calendario> getCalendarioFiltered() {
        if(buscador.compareTo(Long.parseLong("0"))==0){
            return calendario;
        }
        List<Entrada_Calendario> res = new ArrayList<Entrada_Calendario>();
        Iterator<Entrada_Calendario> it = calendario.iterator();
        Entrada_Calendario aux = it.next();
        if(aux.getSocio().getId_Socio().compareTo(buscador)==0){
                res.add(aux);
        }
        while(it.hasNext()){
            aux = it.next();
            if(aux.getSocio().getId_Socio().compareTo(buscador)==0){
                res.add(aux);
            }
        }
        return res;
    }

    public String readEntradaCalendario(Entrada_Calendario ent1){
        entCal = ent1;
        return "consultarEntradaCalendario.xhtml";
    }
    
    public String limpiarBusqueda(){
        buscador = Long.parseLong("0");
        return "listarCalendario.xhtml";
    }
}
