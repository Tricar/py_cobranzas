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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

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
    private BigDecimal montopago = new BigDecimal(BigInteger.ZERO);
    private String descripcion;
    private final creditoBean credbean = new creditoBean();
    public List<Letras> letraslista = new ArrayList();
    public LoginBean loginbean = new LoginBean();

    /**
     * Creates a new instance of pagosBean
     */
    public pagosBean() {
    }

    public List<Pagos> PagosxCredito(Credito cred) {
        PagosDao linkdao = new PagosDaoImp();
        pagosxcredito = linkdao.mostrarPagosxCredito(cred);
        if (pagosxcredito.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "No existen pagos de este cliente."));
        }
        credito = cred;
        return pagosxcredito;
    }

    public List<Pagos> Pagosxletra() {
        PagosDao linkdao = new PagosDaoImp();
        pagosxletra = linkdao.mostrarPagosxLetras(letra);
        return pagosxletra;
    }

    public void insertar(int idusuario) {
        PagosDao pagosdao = new PagosDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        CreditoDao creditodao = new CreditoDaoImp();
//        System.out.println("Este es el Id de letra :"+letra.getIdletras());
        pago.setLetras(letra);
        if (pago.getMonto().compareTo(letra.getSaldo()) != 1) {
//            if ((pago.getMonto().compareTo(letra.getSaldo())) !=1 ) {                
            letra.setSaldo(letra.getSaldo().subtract(pago.getMonto()));
            letrasdao.modificarLetra(letra);
            pago.setUsuario(idusuario);            
            pagosdao.insertarPago(pago);
            credito.setDeudactual(credito.getDeudactual().subtract(pago.getMonto()));
            creditodao.modificarVenta(credito);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El Pago fue satisfactorio."));
            credbean.cargarLetras(credito);
            RequestContext.getCurrentInstance().update("formModificar");
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').hide()");
//            } else {
//                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede cobrar; monto es mayor al importe."));
//            }
            pago = new Pagos();
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede cobrar; monto es mayor al importe."));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Revise el monto", "Monto excede el saldo"));
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        }

    }

    public void cargarLetra(Letras letras) {
        letra = letras;
        montopago = letra.getMonto();
        descripcion = letra.getDescripcion();
        CreditoDao linkdao = new CreditoDaoImp();
        credito = linkdao.cargarCreditoxLetra(letra);
        //return letra;        
    }

    public void limpiar() {
        pagosxcredito = new ArrayList();
    }

    public void eliminar() {
        PagosDao pagodao = new PagosDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        CreditoDao creditodao = new CreditoDaoImp();
        Letras letrapago = new Letras();
        Credito creditopago = new Credito();
        try {
            if (pago.getTipo().equals("LE") || pago.getTipo().equals("NC")) {
                pagodao.eliminarPago(pago);
                letrapago = pago.getLetras();
                letrapago.setSaldo(letrapago.getSaldo().add(pago.getMonto()));
                letrasdao.modificarLetra(letrapago);
                creditopago = letrapago.getCredito();
                creditopago.setDeudactual(creditopago.getDeudactual().add(pago.getMonto()));
                creditodao.modificarVenta(creditopago);
            } else {
                pagodao.eliminarPago(pago);
            }
            PagosxCredito(credito);
            RequestContext.getCurrentInstance().update("formhistorial");
            RequestContext.getCurrentInstance().execute("PF('dlghistorial').show()");
            letraslista = credbean.cargarLetrasArray(credito);
            RequestContext.getCurrentInstance().update("formModificar");
        } catch (Exception e) {
        }
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

    public List<Pagos> getPagosxcredito() {
        return pagosxcredito;
    }

    public void setPagosxcredito(List<Pagos> pagosxcredito) {
        this.pagosxcredito = pagosxcredito;
    }

    public List<Pagos> getPagosxletra() {
        return pagosxletra;
    }

    public void setPagosxletra(List<Pagos> pagosxletra) {
        this.pagosxletra = pagosxletra;
    }

    public BigDecimal getMontopago() {
        return montopago;
    }

    public void setMontopago(BigDecimal montopago) {
        this.montopago = montopago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Pagos> getPagos() {
        PagosDao linkdao = new PagosDaoImp();
        pagos = linkdao.mostrarPagos();
        return pagos;
    }

    public List<Letras> Letraslista(Credito credito) {
        letraslista = credbean.cargarLetrasArray(credito);
        return letraslista;
    }

    public List<Letras> getLetraslista() {
        return letraslista;
    }

    public void setLetraslista(List<Letras> letraslista) {
        this.letraslista = letraslista;
    }
}
