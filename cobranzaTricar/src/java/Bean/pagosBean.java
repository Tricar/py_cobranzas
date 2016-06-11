package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.MovcajaDao;
import Dao.MovcajaDaoImp;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Caja;
import Model.Credito;
import Model.Letras;
import Model.Movcaja;
import Model.Pagos;
import Model.Tipodoc;
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
    private String numletra;
    private tipodocBean tipobean = new tipodocBean();
    private cajaBean cajabean = new cajaBean();
    private List<Tipodoc> listafiltrada = new ArrayList();
    private List<Caja> listaFiltCaja = new ArrayList();
    private boolean disablecaja = true;
    private boolean disableoper = true;
    private boolean disablecomp = true;
    private movcajaBean movcbean = new movcajaBean();
    private Movcaja mcaja = new Movcaja();
    private Caja caja = new Caja();

    /**
     * Creates a new instance of pagosBean
     */
    public pagosBean() {
    }

    public List<Pagos> PagosxCredito(Credito cred) {
        PagosDao linkdao = new PagosDaoImp();
        pagosxcredito = linkdao.mostrarPagosxCredito(cred);
        if (pagosxcredito.isEmpty() == true) {
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
        MovcajaDao mcajadao = new MovcajaDaoImp();
        pago.setLetras(letra);
        if (montopago.compareTo(letra.getSaldo()) != 1) {
            letra.setSaldo(letra.getSaldo().subtract(montopago));
            letrasdao.modificarLetra(letra);
            pago.setMonto(montopago);
            pago.setUsuario(idusuario);
            pagosdao.insertarPago(pago);
            caja = pago.getCaja();
            caja.setTotal(caja.getTotal().add(montopago));
            mcaja.setCaja(caja);
            mcaja.setTipomov("IN");
            mcaja.setFechamov(pago.getFecreg());
            mcaja.setMonto(montopago);
            movcbean.insertar(mcaja);
            cajabean.modificarExt(caja);
            credito.setDeudactual(credito.getDeudactual().subtract(montopago));
            creditodao.modificarVenta(credito);
            credbean.cargarLetras(credito);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El Pago fue satisfactorio."));
            RequestContext.getCurrentInstance().update(":formModificar:tablaletras");
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').hide()");
            pago = new Pagos();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Revise el monto", "Monto excede el saldo"));
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        }

    }

    public void cargarLetra(Letras letras) {
        letra = letras;
        numletra = letra.getDescripcion();
        montopago = letra.getSaldo();
        descripcion = letra.getDescripcion();
        CreditoDao linkdao = new CreditoDaoImp();
        credito = linkdao.cargarCreditoxLetra(letra);
        RequestContext.getCurrentInstance().update("formpagar");
        RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        //return letra;        
    }

    public void listaTipoDoc(String tipo) {
        listafiltrada = tipobean.listaTipoDoc(tipo);
    }

    public void disableDest(String tipo) {
        if (tipo.equals("LE")) {
            disablecaja = false;
        } else {
            disablecaja = true;
        }
    }

    public void disableNumOper(String tipo) {
        if (tipo.equals("DB")) {
            disableoper = false;
            disablecomp = true;
        } else {
            disableoper = true;
            disablecomp = false;
        }
    }

    public void limpiar() {
        pagosxcredito = new ArrayList();
    }

    public List eliminar(Credito cred, Pagos pag) {
        pago = pag;
        credito = cred;
        System.out.println("delete en pagosbean: " + credito.getAnexo().getNombres());
        PagosDao pagodao = new PagosDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        CreditoDao creditodao = new CreditoDaoImp();
        Letras letrapago = new Letras();
        Credito creditopago = new Credito();
        System.out.println("aca hay un pago: " + pago.getDescripcion());
        try {
            if (pago.getTipo().equals("LE") || pago.getTipo().equals("NC")) {
                System.out.println("aca hay un pago: " + pago.getDescripcion());
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
        return pagosxcredito;
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

    public String getNumletra() {
        return numletra;
    }

    public void setNumletra(String numletra) {
        this.numletra = numletra;
    }

    public tipodocBean getTipobean() {
        return tipobean;
    }

    public void setTipobean(tipodocBean tipobean) {
        this.tipobean = tipobean;
    }

    public cajaBean getCajabean() {
        return cajabean;
    }

    public void setCajabean(cajaBean cajabean) {
        this.cajabean = cajabean;
    }

    public List<Tipodoc> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Tipodoc> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public List<Caja> getListaFiltCaja() {
        if (credito.getTienda() != null) {
            listaFiltCaja = cajabean.filtrarTienda(credito.getTienda());
        }
        return listaFiltCaja;
    }

    public void setListaFiltCaja(List<Caja> listaFiltCaja) {
        this.listaFiltCaja = listaFiltCaja;
    }

    public boolean isDisablecaja() {
        return disablecaja;
    }

    public void setDisablecaja(boolean disablecaja) {
        this.disablecaja = disablecaja;
    }

    public boolean isDisableoper() {
        return disableoper;
    }

    public void setDisableoper(boolean disableoper) {
        this.disableoper = disableoper;
    }

    public boolean isDisablecomp() {
        return disablecomp;
    }

    public void setDisablecomp(boolean disablecomp) {
        this.disablecomp = disablecomp;
    }

    public movcajaBean getMovcbean() {
        return movcbean;
    }

    public void setMovcbean(movcajaBean movcbean) {
        this.movcbean = movcbean;
    }

    public Movcaja getMcaja() {
        return mcaja;
    }

    public void setMcaja(Movcaja mcaja) {
        this.mcaja = mcaja;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }
}
