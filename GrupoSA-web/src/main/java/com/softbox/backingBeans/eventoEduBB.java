/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Evento;
import java.io.Serializable;
import java.sql.Date;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author migue
 */
@Named(value = "eventoEduBB")
@SessionScoped
public class eventoEduBB implements Serializable{
    private Long idEvento;
    private Evento evt;
    /**
     * Creates a new instance of eventoEduBB
     */
    public eventoEduBB() {
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Evento getEvt() {
        return evt;
    }

    public void setEvt(Evento evt) {
        this.evt = evt;
    }
    
    public String mostrarEvento(String numero){
        idEvento = Long.parseLong(numero);
        evt = new Evento();
        evt.setId_evento(idEvento);
        evt.setNombre("Pesca en el espigón de la calita");
        evt.setDescripcion("¿En que consiste el evento? Una vez el jugador tiene un número de cajas (las que considere el jugador suficientes), pulsando en el banner se abrirá una pantalla de un puzzle. Se deben arrastrar las cajas a sus sitios y, una vez en sus casillas, se pueden abrir para obtener piezas del puzzle. Cada caja sólo da una pieza y la caja normal tiene varios tipos de piezas. ");
        evt.setFecha(Date.valueOf("2018-02-13"));
        evt.setPrecio(0f);
        return "evento.xhtml";
    }
    
}
