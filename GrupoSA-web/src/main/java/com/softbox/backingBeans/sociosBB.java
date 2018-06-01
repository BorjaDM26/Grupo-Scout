/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.ejb.SocioFacadeLocal;
import com.softbox.entity.Rol_Perfil;
import com.softbox.entity.Socio;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Borja Delgado
 */
@Named(value = "sociosBB")
@SessionScoped
public class sociosBB implements Serializable{
    
    @Inject
    private SocioFacadeLocal sf;
    @Inject
    private SocioFacadeLocal uf;
    
    private Long sigIdUsuario = Long.parseLong("1");
    private Long sigIdSocio = Long.parseLong("10000");    
    private List<Socio> socios;
    private Socio socio = new Socio();

    /**
     * Creates a new instance of sociosBB
     */
    public sociosBB() {
    }

    public List<Socio> getSociosEJB() {
        return sf.findAll();
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    
    
    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    //Accede a la vista de creación de socios
    public String newSocio(){
        socio = new Socio();
        return "crearSocio.xhtml";
    }
    
    //Crea el socio con los datos proporcionado en la vista de creación
    public String createSocio(){
        socio.setId_Socio(sigIdSocio++);
        socio.setId_Usuario(sigIdUsuario++);        
        socio.setFecha_ingreso(Date.valueOf(LocalDate.now()));
        sf.create(socio);
        return "sociosLista.xhtml";
    }
    
    
    public String updateSocio(Socio soc){
        sf.edit(soc);
        return "modificarSocio.xhtml";
    }
    
    public String readSocio(Socio soc){
        socio = soc;
        return "consultarSocio.xhtml";
    }
    
    public String deleteSocio(Socio soc){
        socio = sf.find(soc.getId_Usuario());
        sf.remove(socio);
//        boolean borrado = false;
//        int i = 0;
//        while(!borrado && i < socios.size()){
//            if(socios.get(i).getId_Socio().compareTo(soc.getId_Socio())==0){
//                socios.remove(i);
//                borrado=true;
//            }
//            i++;
//        }
        return "sociosLista.xhtml";
    }
    
    public Socio getSocioByID(Long id){
        return sf.find(id);

//        boolean encontrado = false;
//        int i = 0;
//        while(!encontrado && i < socios.size()){
//            if(socios.get(i).getId_Socio().compareTo(id)==0){
//                return socios.get(i);
//            }
//            i++;
//        }
//        return null;
    }
}
