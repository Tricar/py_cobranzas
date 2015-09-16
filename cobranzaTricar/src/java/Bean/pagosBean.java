package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Credito;
import Model.Letras;
import Model.Pagos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class pagosBean implements Serializable {

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
        PagosDao pagosdao = new PagosDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        CreditoDao creditodao = new CreditoDaoImp();
//        System.out.println("Este es el Id de letra :"+letra.getIdletras());
        pago.setLetras(letra);
        if ((pago.getMonto().compareTo(letra.getSaldo())) == 0) {
            letra.setSaldo(BigDecimal.ZERO);
            letrasdao.modificarLetra(letra);
            pagosdao.insertarPago(pago);
            credito.setTotaldeuda(credito.getTotaldeuda().subtract(pago.getMonto()));
            creditodao.modificarVenta(credito);
        } else {
            if ((pago.getMonto().compareTo(letra.getSaldo())) == -1) {
                letra.setSaldo(letra.getSaldo().subtract(pago.getMonto()));
                letrasdao.modificarLetra(letra);
                pagosdao.insertarPago(pago);
                credito.setTotaldeuda(credito.getTotaldeuda().subtract(pago.getMonto()));
                creditodao.modificarVenta(credito);
            } else {
                System.out.println("No se puede cobrar");
            }
        }

        pago = new Pagos();
    }

    public Letras cargarLetra(Letras letras) {
        letra = letras;
//        System.out.println("Este es el Id de letra en cargar:"+letra.getIdletras());
        CreditoDao linkdao = new CreditoDaoImp();
        credito = linkdao.cargarCreditoxLetra(letra);
        return letra;
    }
}
