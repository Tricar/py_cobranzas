package Bean;

import Dao.CondicionPagoDao;
import Dao.CondicionPagoDaoImplements;
import Model.Condicionpago;
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
public class condicionPagoBean {

    public Condicionpago condpago = new Condicionpago();
    public List<Condicionpago> condpagos;

    public Condicionpago getCondpago() {
        return condpago;
    }

    public void setCondpago(Condicionpago condpago) {
        this.condpago = condpago;
    }

    public List<Condicionpago> getCondpagos() {
        CondicionPagoDao condiciondao = new CondicionPagoDaoImplements();
        condpagos = condiciondao.mostrarCondicion();
        return condpagos;
    }

    public void setCondpagos(List<Condicionpago> condpagos) {
        this.condpagos = condpagos;
    }

    /**
     * Creates a new instance of condicionPagoBean
     */
    public condicionPagoBean() {
    }

    public void insertar() {
        CondicionPagoDao linkDao = new CondicionPagoDaoImplements();
        Date d = new Date();
        condpago.setFechareg(d);
        linkDao.insertarCondicion(condpago);
        condpago = new Condicionpago();
    }
    
    public void modificar(){
        CondicionPagoDao linkDao = new CondicionPagoDaoImplements();
        linkDao.modificarCondicion(condpago);
        condpago = new Condicionpago();
    }
    
    public void eliminar(){
        CondicionPagoDao linkDao = new CondicionPagoDaoImplements();
        linkDao.eliminarcondicion(condpago);
        condpago = new Condicionpago();
    }
}
