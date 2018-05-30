/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Comentario;
import com.softbox.entity.Evento;
import com.softbox.entity.Socio;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author migue
 */
@Named(value = "comentariosBB")
@SessionScoped
public class comentariosBB implements Serializable {
    private Comentario com;
    private List<Comentario> listComen;
    private Long sigComentario = Long.parseLong("1");
    private String textoComentario;

    /**
     * Creates a new instance of comentariosBB
     */
    public comentariosBB() {
        listComen = new ArrayList<>();
        com = new Comentario();
        com.setEvento(new Evento());
        com.setFecha(Date.valueOf("2018-02-15"));
        com.setTexto("Me encanta la pesca, yo voy seguro!");
        Socio s1 = new Socio();
        
        s1.setNombre("Manuel");
        s1.setId_Socio(Long.parseLong("1"));
        com.setSocio(s1);
        com.setId_comentario(sigComentario++);
        listComen.add(com);
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }
    
    public String addComentario(Socio s, String c, Evento e){
        Comentario c1 = new Comentario();
        c1.setEvento(e);
        c1.setFecha(Date.valueOf(LocalDate.now()));
        c1.setTexto(c);
        c1.setSocio(s);
        c1.setId_comentario(sigComentario++);
        listComen.add(c1);
        textoComentario= "";
        return "evento.xhtml";
    }
    public Comentario getCom() {
        return com;
    }

    public void setCom(Comentario com) {
        this.com = com;
    }

    public List<Comentario> getListComen() {
        return listComen;
    }

    public void setListComen(List<Comentario> listComen) {
        this.listComen = listComen;
    }

    public Long getSigComentario() {
        return sigComentario;
    }

    public void setSigComentario(Long sigComentario) {
        this.sigComentario = sigComentario;
    }
    
    public void editarCom(){
    }

    public void borrarCom(Comentario com){
        listComen.remove(com);
    }
}
