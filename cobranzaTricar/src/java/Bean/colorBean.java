/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.ColorDao;
import Dao.ColorDaoImplements;
import Model.Color;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class colorBean {
    
    public Color color = new Color();
    public List<Color> colores;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public List<Color> getColores() {
        ColorDao tipodao = new ColorDaoImplements();
        colores = tipodao.mostrarColor();
        return colores;
    }

    public void setColores(List<Color> colores) {
        this.colores = colores;
    }
    
    public colorBean() {
    }
    
    public void insertar (){
        ColorDao linkDao = new ColorDaoImplements();
        Date d = new Date();
        color.setFecreg(d);
        linkDao.insertarColor(color);
        color = new Color();
        
    }
    
    public void modificar(){
        ColorDao linkDao = new ColorDaoImplements();
        linkDao.modificarColor(color);
        color = new Color();
    }
    
    public void eliminar(){
        ColorDao linkDao = new ColorDaoImplements();
        linkDao.eliminarColor(color);
        color = new Color();
    }
}
