package Bean;

import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Credito;
import Model.Letras;
import Model.Pagos;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class pagosBean implements Serializable{
    private Pagos pago = new Pagos();
    private List<Pagos> pagos;
    private List<Pagos> pagosxcredito;    
    private List<Pagos> pagosxletra;
    private Letras letra = new Letras();
    private Credito credito = new Credito();
    /**
     * Creates a new instance of pagosBean
     */
    public pagosBean() {
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public Letras getLetra() {
        return letra;
    }

    public void setLetra(Letras letra) {
        this.letra = letra;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    
    public List<Pagos> getPagos() {
        PagosDao linkdao = new PagosDaoImp();
        pagos = linkdao.mostrarPagos();
        return pagos;
    }

    public List<Pagos> getPagosxCredito() {
        PagosDao linkdao = new PagosDaoImp();
        pagos = linkdao.mostrarPagosxCredito(credito);
        return pagosxcredito;
    }

    public List<Pagos> getPagosxletra() {
        PagosDao linkdao = new PagosDaoImp();
        pagos = linkdao.mostrarPagosxLetras(letra);        
        return pagosxletra;
    }
    
    public void insertar() {
        PagosDao linkDao = new PagosDaoImp();
        pago.setLetras(letra);
        linkDao.insertarPago(pago);
        pago = new Pagos();
    }
}