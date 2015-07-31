package Bean;

import Dao.TipoVehiDao;
import Dao.TipoVehiDaoImplements;
import Model.Tipovehiculo;
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
public class tipoVehiBean {

    public Tipovehiculo vehiculo = new Tipovehiculo();
    public List<Tipovehiculo> vehiculos;

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
    
    public tipoVehiBean() {
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
