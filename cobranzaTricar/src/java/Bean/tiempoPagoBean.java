package Bean;

import Dao.TiempoPagoDao;
import Dao.TiempoPagoDaoImplements;
import Model.Tiempopago;
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
public class tiempoPagoBean implements Serializable{

    public Tiempopago tpago = new Tiempopago();
    public List<Tiempopago> tpagos;
    /**
     * Creates a new instance of tiempoPagoBean
     */
    public tiempoPagoBean() {
    }

    public Tiempopago getTpago() {
        return tpago;
    }

    public void setTpago(Tiempopago tpago) {
        this.tpago = tpago;
    }

    public List<Tiempopago> getTpagos() {
        TiempoPagoDao tipodao = new TiempoPagoDaoImplements();
        tpagos = tipodao.mostrarTpago();
        return tpagos;
    }

    public void setTpagos(List<Tiempopago> tpagos) {
        this.tpagos = tpagos;
    }
    
    public void insertar (){
        TiempoPagoDao linkDao = new TiempoPagoDaoImplements();
        Date d = new Date();
        tpago.setFecreg(d);
        linkDao.insertarTpago(tpago);
        tpago = new Tiempopago();
        
    }
    
    public void modificar(){
        TiempoPagoDao linkDao = new TiempoPagoDaoImplements();
        linkDao.modificarTpago(tpago);
        tpago = new Tiempopago();
    }
    
    public void eliminar(){
        TiempoPagoDao linkDao = new TiempoPagoDaoImplements();
        linkDao.eliminarTpago(tpago);
        tpago = new Tiempopago();
    }
}
