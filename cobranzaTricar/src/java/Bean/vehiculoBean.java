package Bean;

import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Tipovehiculo;
import Model.Vehiculo;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class vehiculoBean {
    
    public Vehiculo vehiculo = new Vehiculo();
    public List<Vehiculo> vehiculos;
       
    public vehiculoBean() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Vehiculo> getVehiculos() {
        VehiculoDao tipodao = new VehiculoDaoImplements();
        vehiculos = tipodao.mostrarVehiculo();
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public void insertar (){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        Date d = new Date();
        vehiculo.setFechareg(d);
        linkDao.insertarTipoVehiculo(vehiculo);
        vehiculo = new Vehiculo();
        
    }
    
    public void modificar(){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        linkDao.modificarTipoVehiculo(vehiculo);
        vehiculo = new Vehiculo();
    }
    
    public void eliminar(){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        linkDao.eliminarTipoVehiculo(vehiculo);
        vehiculo = new Vehiculo();
    }
    
    @PostConstruct
    public void init(){
        Tipovehiculo tipovehiculo = new Tipovehiculo();
    }
}
