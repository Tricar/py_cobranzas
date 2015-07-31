package Bean;

import Dao.ModeloDao;
import Dao.ModeloDaoImplements;
import Model.Modelo;
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
public class modeloBean {
    
    public Modelo modelo = new Modelo();
    public List<Modelo> modelos;

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
    
    public modeloBean() {
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
