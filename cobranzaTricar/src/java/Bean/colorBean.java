/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.ColorDao;
import Dao.ColorDaoImplements;
import Model.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class colorBean implements Serializable{
    
    public Color color = new Color();
    public List<Color> colores;
    private List<SelectItem> SelectItemsColor;

    public colorBean() {
    }
    
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

    public List<SelectItem> getSelectItemsColor() {
        this.SelectItemsColor = new ArrayList<SelectItem>();
        ColorDao colorDao = new ColorDaoImplements();
        List<Color> colores = colorDao.mostrarColor();
        for (Color color : colores) {
            SelectItem selecItem = new SelectItem(color.getIdcolor(), color.getColor());
            this.SelectItemsColor.add(selecItem);
        }
        return SelectItemsColor;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
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
