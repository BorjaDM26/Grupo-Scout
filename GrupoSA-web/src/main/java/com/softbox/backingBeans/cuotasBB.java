/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Cuota;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Tilted-Shugar
 */

@Named(value = "cuotasBB")
@SessionScoped
public class cuotasBB implements Serializable{
    private Long sigId = Long.parseLong("1");    
    private List<Cuota> cuotas;
    private Cuota cuota = new Cuota();
    
    public cuotasBB(){
        cuotas = new ArrayList<Cuota>();
        Cuota cuota1 = new Cuota();
        cuota1.setId_Cuota(sigId++);
        cuota1.setDescripcion("Cuota anual para Educandos");
        cuota1.setImporte(39);
        cuota1.setNombre("Educando");
        cuotas.add(cuota1);
        Cuota cuota2 = new Cuota();
        cuota2.setId_Cuota(sigId++);
        cuota2.setDescripcion("Cuota anual para Scouters");
        cuota2.setImporte(25);
        cuota2.setNombre("Scouter");
        cuotas.add(cuota2);
    }
    
    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }
    
    //Accede a la vista de creación de socios
    public String newCuota(){
        cuota = new Cuota();
        return "cuotasCrear.xhtml";
    }
    
    //Crea el socio con los datos proporcionado en la vista de creación
    public String createCuotas(){
        cuota.setId_Cuota(sigId++);     
        cuotas.add(cuota);
        return "cuotasLista.xhtml";
    }
    
    public String updateCuota(Cuota cuot){
        cuota = cuot;
        return "cuotasModificar.xhtml";
    }
    
    public String deleteCuota(Cuota cuot){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < cuotas.size()){
            if(cuotas.get(i).getId_Cuota().compareTo(cuot.getId_Cuota())==0){
                cuotas.remove(i);
                borrado=true;
            }
            i++;
        }
        return "cuotasLista.xhtml";
    }
}
