package Bean;

import Dao.VentaDao;
import Dao.VentaDaoImplements;
import Model.Venta;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ventaBean implements Serializable{

    public Venta venta = new Venta();
    public List<Venta> ventas;
    
    public ventaBean() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<Venta> getVentas() {
        VentaDao linkdao = new VentaDaoImplements();
        ventas = linkdao.mostrarVentas();
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    public void insertar (){
        VentaDao linkDao = new VentaDaoImplements();
        linkDao.insertarVenta(venta);
        venta = new Venta();
        
    }
    
    public void modificar(){
        VentaDao linkDao = new VentaDaoImplements();
        linkDao.modificarVenta(venta);
        venta = new Venta();
    }
    
    public void eliminar(){
        VentaDao linkDao = new VentaDaoImplements();
        linkDao.eliminarVenta(venta);
        venta = new Venta();
    }
    
}