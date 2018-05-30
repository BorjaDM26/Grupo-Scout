/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbox.backingBeans;

import com.softbox.entity.Evento;
import com.softbox.entity.Seccion;
import com.softbox.entity.Socio;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;

/**
 *
 * @author Borja Delgado
 */
@Named(value = "eventosBB")
@SessionScoped
public class eventosBB implements Serializable{
    
    private Long sigIdEvento = Long.parseLong("1");   
    private List<Evento> eventos;
    private Evento evento = new Evento();

    /**
     * Creates a new instance of sociosBB
     */
    public eventosBB() {
        Seccion seccion= new Seccion();
        seccion.setId_seccion(Long.parseLong("1"));
        List<Socio> asistentes=new ArrayList<Socio>();
        List<Socio> inscritos=new ArrayList<Socio>();
        
        Socio socio1=new Socio();
        socio1.setId_Socio(Long.parseLong("0"));
        socio1.setNombre("Roberto");
        socio1.setApellidos("Ramirez");
        Socio socio2=new Socio();
        socio2.setId_Socio(Long.parseLong("1"));
        socio2.setNombre("Miguel");
        socio2.setApellidos("Antonio de la Rosa Santísima");
        Socio socio3=new Socio();
        socio3.setId_Socio(Long.parseLong("2"));
        socio3.setNombre("Juan Alberto");
        socio3.setApellidos("Totti");
        Socio socio4=new Socio();
        socio4.setId_Socio(Long.parseLong("3"));
        socio4.setNombre("Fidel");
        socio4.setApellidos("Delgado");
        Socio socio5=new Socio();
        socio5.setId_Socio(Long.parseLong("4"));
        socio5.setNombre("María Marta");
        socio5.setApellidos("Schwarzenegger");
        asistentes.add(socio1);
        asistentes.add(socio2);
        inscritos.add(socio3);
        inscritos.add(socio4);
        inscritos.add(socio5);
        eventos = new ArrayList<Evento>();
        Evento evento1 = new Evento();
        evento1.setId_evento(sigIdEvento++);
        evento1.setNombre("Evento 1");
        evento1.setDescripcion("Es una pasada.");
        evento1.setLocalizacion("Extremadura");
        evento1.setPrecio(Float.parseFloat("56.99"));
        evento1.setFecha(Date.valueOf("2018-09-13"));
        evento1.setImagen("resources/images/pesca.jpg");
        evento1.setSeccion(seccion);
        evento1.setAsistentes(asistentes);
        evento1.setInscritos(inscritos);

        eventos.add(evento1);
        
        Evento evento2 = new Evento();
        evento2.setId_evento(sigIdEvento++);
        evento2.setNombre("Evento 2");
        evento2.setDescripcion("Meh.");
        evento2.setLocalizacion("Malaga");
        evento2.setPrecio(Float.parseFloat("14.99"));
        evento2.setFecha(Date.valueOf("2018-07-09"));
        evento2.setImagen("resources/images/pesca.jpg");
        evento2.setSeccion(seccion);
        evento2.setAsistentes(asistentes);
        evento2.setInscritos(inscritos);

        eventos.add(evento2);
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    //Accede a la vista de creación de eventos
    public String newEvento(){
        evento = new Evento();
        Seccion seccion=new Seccion();
        evento.setSeccion(seccion);
        return "crearEvento.xhtml";
    }
    
    //Crea el evento con los datos proporcionado en la vista de creación
    public String createEvento(){
        evento.setId_evento(sigIdEvento++);        
        eventos.add(evento);
        return "listarEventos.xhtml";
    }
    
    public String updateEvento(Evento soc){
        evento = soc;
        return "modificarEvento.xhtml";
    }
    
    public String readEvento(Evento soc){
        evento = soc;
        return "consultarEvento.xhtml";
    }
    
    public String deleteEvento(Evento soc){
        boolean borrado = false;
        int i = 0;
        while(!borrado && i < eventos.size()){
            if(eventos.get(i).getId_evento().compareTo(soc.getId_evento())==0){
                eventos.remove(i);
                borrado=true;
            }
            i++;
        }
        return "listarEventos.xhtml";
    }
    public String getDireccionDescarga(int modo) throws UnsupportedEncodingException{
        String nombreArchivo="";
        if(modo==0){
            nombreArchivo="Asistentes.xlsx";
        }else{
            nombreArchivo="Inscritos.xlsx";
        }
        eventosBB demo=new eventosBB();
        String path = demo.getClass().getClassLoader().getResource("").getPath();
        String path2=demo.getClass().getResource("").getPath();
        String fullPath = URLDecoder.decode(path2, "UTF-8");
        
        String pathArr[] = fullPath.split("/WEB-INF/classes/backingBeans/");
        
        fullPath = pathArr[0];
        
        String reponsePath = "";
        reponsePath = new File(fullPath).getPath() + File.separatorChar + "resources"+File.separatorChar+nombreArchivo;
        return reponsePath;
    }
    
    public void descargaEvento(Evento evento,int modo) throws UnsupportedEncodingException{
        
        String FILE_NAME=getDireccionDescarga(modo);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet;
        List<Socio> socios;
        if(modo==0){
            sheet= workbook.createSheet("Asistentes");
            socios=evento.getAsistentes();
        }else{
            sheet= workbook.createSheet("Inscritos");
            socios=evento.getInscritos();
        }
        int rowNum = 0;
        
        Row rowInicial = sheet.createRow(rowNum++);
        Cell cellInicial=rowInicial.createCell(0);
        cellInicial.setCellValue("ID_SOCIO");
        
        cellInicial = rowInicial.createCell(1);
        cellInicial.setCellValue("NOMBRE");
        
        cellInicial = rowInicial.createCell(2);
        cellInicial.setCellValue("APELLIDOS");
        
        if(socios!=null){
            for (Socio socio : socios) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
            
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(socio.getId_Socio());
                
                cell = row.createCell(colNum++);
                cell.setCellValue(socio.getNombre());
            
                cell = row.createCell(colNum++);
                cell.setCellValue(socio.getApellidos());
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
