package Bean;

import Dao.ModeloDao;
import Dao.ModeloDaoImplements;
import Model.Modelo;
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
public class modeloBean implements Serializable{
    
    public Modelo modelo = new Modelo();
    public List<Modelo> modelos;
    private List<SelectItem> SelectItemsmodelo;
  
    public modeloBean() {
    }
    
    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Modelo> getModelos() {
        ModeloDao tipodao = new ModeloDaoImplements();
        modelos = tipodao.mostrarModelo();
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public void setSelectItemsmodelo(List<SelectItem> SelectItemsmodelo) {
        this.SelectItemsmodelo = SelectItemsmodelo;
    }

    public List<SelectItem> getSelectItemsmodelo() {
        this.SelectItemsmodelo = new ArrayList<SelectItem>();
        ModeloDao modeloDao = new ModeloDaoImplements();
        List<Modelo> modelos = modeloDao.mostrarModelo();
        for (Modelo modelo : modelos) {
            SelectItem selecItem = new SelectItem(modelo.getIdmodelo(),modelo.getModelo());
            this.SelectItemsmodelo.add(selecItem);
        }
        return SelectItemsmodelo;
    }
    
    public void insertar (){
        ModeloDao linkDao = new ModeloDaoImplements();
        Date d = new Date();
        modelo.setFecreg(d);
        linkDao.insertarModelo(modelo);
        modelo = new Modelo();
        
    }
    
    public void modificar(){
        ModeloDao linkDao = new ModeloDaoImplements();
        linkDao.modificarModelo(modelo);
        modelo = new Modelo();
    }
    
    public void eliminar(){
        ModeloDao linkDao = new ModeloDaoImplements();
        linkDao.eliminarModelo(modelo);
        modelo = new Modelo();
    }
}
