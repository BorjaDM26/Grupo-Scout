/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Seccion;
import com.softbox.entity.Socio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author migue
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    private String correo;
    private String password;
    private List<Socio> lista_usuarios;
    private Long idsocio = Long.parseLong("1");
    
    @Inject
    private ControlAutorizacion ctrl;
    /*
    @Inject // inyectamos la dependencia
    private SessionUtils session;*/
    
    public Login() {
        lista_usuarios = new ArrayList<>();
        Seccion sec = new Seccion();
        sec.setId_seccion(Long.parseLong("1"));
        sec.setNombre("unidad");
        Socio u1 = new Socio();
        u1.setEmail("edu@correo.com");
        u1.setPass("1234");
        u1.setPerfil("EDU");
        u1.setNombre("Manuel");
        u1.setApellidos("Pérez");
        u1.setSexo("Hombre");
        u1.setFecha_nacimiento(new Date(1992,04,19));
        u1.setSeccion(sec);
        u1.setId_Socio(idsocio++);
        
        Socio u2 = new Socio();
        u2.setEmail("scouter@correo.com");
        u2.setPass("oye");
        u2.setPerfil("SCOUT");
        u2.setId_Socio(idsocio++);
        Socio u3 = new Socio();
        u3.setEmail("admin@correo.com");
        u3.setPass("root");
        u3.setPerfil("ROOT");
        u3.setId_Socio(idsocio++);
        lista_usuarios.add(u3);
        lista_usuarios.add(u2);
        lista_usuarios.add(u1);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Socio> getLista_usuarios() {
        return lista_usuarios;
    }

    public void setLista_usuarios(List<Socio> lista_usuarios) {
        this.lista_usuarios = lista_usuarios;
    }
    
        public String autenticar() {
        // Implementar este método
        int contador = 0;
        boolean encontrado = false;
        Socio user1 = null;
        String cadena = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        while(contador < lista_usuarios.size() && !encontrado){
            user1 = lista_usuarios.get(contador);
            encontrado = user1.getEmail().equalsIgnoreCase(correo);
            contador++;
        }
        
        if(!encontrado){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario no existe", "El usuario no existe"));
        }else{
            if(user1.getPass().equals(password)){
                //session.add("Usuarios Logeado", user1);
                ctrl.setUsuario(user1);
                cadena = "index.xhtml";
            }else{
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña no es válida", "La contraseña no es válida"));
            }
        }
        
        return cadena;
    }
}
