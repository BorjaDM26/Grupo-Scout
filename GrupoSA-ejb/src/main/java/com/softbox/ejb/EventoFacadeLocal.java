/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.ejb;

import com.softbox.entity.Evento;
import com.softbox.entity.Seccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miguel_martin
 */
@Local
public interface EventoFacadeLocal {

    void create(Evento evento);

    void edit(Evento evento);

    void remove(Evento evento);

    Evento find(Object id);

    List<Evento> findAll();

    List<Evento> findRange(int[] range);
    
    List<Evento> findBySeccion(Long id_Seccion);
    
    boolean usuarioInscrito(Long id_Socio, Long id_Evento);
    
    void inscribirSocio(Long id_Socio, Long id_Evento);
    
    int count();
    
}
