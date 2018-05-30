/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Socio;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Borja Delgado
 */
@Named(value = "sociosBB")
@SessionScoped
public class sociosBB implements Serializable{
    
    private Long sigIdUsuario = Long.parseLong("1");
    private Long sigIdSocio = Long.parseLong("10000");    
    private List<Socio> socios;
    private Socio socio = new Socio();

    /**
     * Creates a new instance of sociosBB
     */
    public sociosBB() {
        socios = new ArrayList<Socio>();
        Socio socio1 = new Socio();
        socio1.setId_Socio(sigIdSocio++);
        socio1.setId_Usuario(sigIdUsuario++);
        socio1.setNombre("Juan");
        socio1.setApellidos("Fernández");
        socio1.setDni("12345678");
        socio1.setEmail("HansTheMan@correo.com");
        socio1.setSexo("Hombre");
        socio1.setFecha_nacimiento(Date.valueOf("1994-02-13"));
        socio1.setDireccion("Calle de Atrás, N1, 1ºB. Madrid, 10101");        
        socio1.setTelefono(952121212);
        socio1.setTelefono_movil(650121212);
        socio1.setPerfil("Scouter");
        socio1.setGrupo("Clan");
        socio1.setCargo("Instructor");
        socio1.setAmbito("Federal");
        socio1.setFecha_firma(Date.valueOf(LocalDate.now()));
        socio1.setFecha_ingreso(Date.valueOf(LocalDate.now()));
        socios.add(socio1);
        Socio socio2 = new Socio();
        socio2.setId_Socio(sigIdSocio++);
        socio2.setId_Usuario(sigIdUsuario++);
        socio2.setNombre("Loles");
        socio2.setApellidos("Lelos");
        socio2.setDni("12121212");
        socio2.setEmail("LaLoLes@correo.com");
        socio2.setSexo("Mujer");
        socio2.setFecha_nacimiento(Date.valueOf("1998-07-19"));
        socio2.setDireccion("Calle de Atrás, N1, 3ºA. Madrid, 10101");        
        socio2.setTelefono(952345345);
        socio2.setTelefono_movil(650345345);
        socio2.setPerfil("Educando");
        socio2.setGrupo("Clan");
        socio2.setAmbito("Grupo");
        socio2.setFecha_firma(Date.valueOf(LocalDate.now()));
        socio2.setFecha_ingreso(Date.valueOf(LocalDate.now()));
        socios.add(socio2);
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
        socios.add(socio);
        return "sociosLista.xhtml";
    }
    
    public String updateSocio(Socio soc){
        socio = soc;
        return "modificarSocio.xhtml";
    }
    
    public String readSocio(Socio soc){
        socio = soc;
        return "consultarSocio.xhtml";
    }
    
    public String deleteSocio(Socio soc){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < socios.size()){
            if(socios.get(i).getId_Socio().compareTo(soc.getId_Socio())==0){
                socios.remove(i);
                borrado=true;
            }
            i++;
        }
        return "sociosLista.xhtml";
    }
    
    public Socio getSocioByID(Long id){
        boolean encontrado = false;
        int i = 0;
        while(!encontrado && i < socios.size()){
            if(socios.get(i).getId_Socio().compareTo(id)==0){
                return socios.get(i);
            }
            i++;
        }
        return null;
    }
}
