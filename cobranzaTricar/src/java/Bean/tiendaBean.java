package Bean;

import Dao.TiendaDao;
import Dao.TiendaDaoImplements;
import Model.Tienda;
import java.io.Serializable;
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
public class tiendaBean implements Serializable{

    public Tienda tienda = new Tienda();
    public List<Tienda> tiendas;
    /**
     * Creates a new instance of tiendaBean
     */
    public tiendaBean() {
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public List<Tienda> getTiendas() {
        TiendaDao linkdao = new TiendaDaoImplements();
        tiendas = linkdao.mostrarTienda();
        return tiendas;
    }

    public void setTiendas(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
    
    public void insertar (){
        TiendaDao linkDao = new TiendaDaoImplements();
        Date d = new Date();
        tienda.setFecreg(d);
        linkDao.insertarTienda(tienda);
        tienda = new Tienda();
        
    }
    
    public void modificar(){
        TiendaDao linkDao = new TiendaDaoImplements();
        linkDao.modificarTienda(tienda);
        tienda = new Tienda();
    }
    
    public void eliminar(){
        TiendaDao linkDao = new TiendaDaoImplements();
        linkDao.eliminarTienda(tienda);
        tienda = new Tienda();
    }
}
