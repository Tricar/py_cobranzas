package Bean;

import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Vehiculo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class vehiculoBean implements Serializable{
    
       
    public Vehiculo vehiculo = new Vehiculo();
    public List<Vehiculo> vehiculos;
   
       
    public vehiculoBean() {
    }

    public Vehiculo getVehiculo() {
        if(vehiculo == null){
            vehiculo = new Vehiculo();
        }
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
    
    public List<Vehiculo> completarTipo(String nombre){
        VehiculoDao tipodao = new VehiculoDaoImplements();
        vehiculos = tipodao.buscarxNombre(nombre);
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public void insertar (){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        Date d = new Date();
        vehiculo.setFechareg(d);
        linkDao.insertarVehiculo(vehiculo);
        vehiculo = new Vehiculo();
        
    }
    
    public void modificar(){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        linkDao.modificarVehiculo(vehiculo);
        vehiculo = new Vehiculo();
    }
    
    public void eliminar(){
        VehiculoDao linkDao = new VehiculoDaoImplements();
        linkDao.eliminarVehiculo(vehiculo);
        vehiculo = new Vehiculo();
    }
}