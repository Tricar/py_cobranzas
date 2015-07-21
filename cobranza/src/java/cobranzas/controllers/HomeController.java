/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranzas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sistemas2
 */
@ManagedBean
@SessionScoped
public class HomeController {

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }    
    
    public String index(){
        return "/index";
    }
    
    public String inicio(){
        return "/index";
    }
    
    public String acercaDe(){
        return "/acerca_de";
    }
    
    public String infoDelPie(){
        String nombre = null;
        String tipo = null;
        String usuario = "";
        if(nombre!=null && tipo!=null){
            usuario = nombre + "(" + tipo +")";
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        Date d = new Date();
        String fechaStr = sdf.format(d);
        
        return usuario + " - " + fechaStr + " - Copyring@2015 Derecho Reservado TRICAR - ";
    }
}
