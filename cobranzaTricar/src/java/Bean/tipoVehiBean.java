package Bean;

import Dao.TipoVehiDao;
import Dao.TipoVehiDaoImplements;
import Model.Tipovehiculo;
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
public class tipoVehiBean implements Serializable{

    public Tipovehiculo vehiculo = new Tipovehiculo();
    public List<Tipovehiculo> vehiculos;
    private List<SelectItem> SelectItemsTipoVehi;

    public tipoVehiBean() {
    }
    
    public Tipovehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Tipovehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Tipovehiculo> getVehiculos() {
        TipoVehiDao tipodao = new TipoVehiDaoImplements();
        vehiculos = tipodao.mostrarTipoVehiculo();
        return vehiculos;
    }

    public void setVehiculos(List<Tipovehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<SelectItem> getSelectItemsTipoVehi() {
        this.SelectItemsTipoVehi = new ArrayList<SelectItem>();
        TipoVehiDao tipoVehiDao = new TipoVehiDaoImplements();
        List<Tipovehiculo> tipos = tipoVehiDao.mostrarTipoVehiculo();
        for (Tipovehiculo tipo : tipos) {
            SelectItem selecItem = new SelectItem(tipo,tipo.getNombre());
            this.SelectItemsTipoVehi.add(selecItem);
        }
        return SelectItemsTipoVehi;
    }
    
    public void insertar (){
        TipoVehiDao linkDao = new TipoVehiDaoImplements();
        Date d = new Date();
        vehiculo.setFechareg(d);
        linkDao.insertarTipoVehiculo(vehiculo);
        vehiculo = new Tipovehiculo();
        
    }
    
    public void modificar(){
        TipoVehiDao linkDao = new TipoVehiDaoImplements();
        linkDao.modificarTipoVehiculo(vehiculo);
        vehiculo = new Tipovehiculo();
    }
    
    public void eliminar(){
        TipoVehiDao linkDao = new TipoVehiDaoImplements();
        linkDao.eliminarTipoVehiculo(vehiculo);
        vehiculo = new Tipovehiculo();
    }
    
    
}