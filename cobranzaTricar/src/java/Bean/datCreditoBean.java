package Bean;

import Dao.DatCreditoDao;
import Dao.DatCreditoDaoImp;
import Model.Datoscredito;
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
public class datCreditoBean implements Serializable{
    
    public Datoscredito datocredito = new Datoscredito();
    public List<Datoscredito> datocreditos;
    /**
     * Creates a new instance of datCreditoBean
     */
    public datCreditoBean() {
    }

    public Datoscredito getDatocredito() {
        return datocredito;
    }

    public void setDatocredito(Datoscredito datocredito) {
        this.datocredito = datocredito;
    }

    public List<Datoscredito> getDatocreditos() {
        DatCreditoDao linkdao = new DatCreditoDaoImp();
        datocreditos = linkdao.mostrarDatCredito();
        return datocreditos;
    }

    public void setDatocreditos(List<Datoscredito> datocreditos) {
        this.datocreditos = datocreditos;
    }
    
    public void insertar(){
        DatCreditoDao linkDao = new DatCreditoDaoImp();
        Date d = new Date();
        datocredito.setFecreg(d);
        linkDao.insertarDatCredito(datocredito);
        datocredito = new Datoscredito();
    }
    
    public void modificar(){
        DatCreditoDao linkDao = new DatCreditoDaoImp();
        linkDao.modificarDatCredito(datocredito);
        datocredito = new Datoscredito();
    }
    
    public void eliminar(){
        DatCreditoDao linkDao = new DatCreditoDaoImp();
        linkDao.eliminarDatCredito(datocredito);
        datocredito = new Datoscredito();
    }
}
