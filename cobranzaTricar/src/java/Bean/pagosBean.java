package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.HistoDao;
import Dao.HistoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Caja;
import Model.Conceptos;
import Model.Credito;
import Model.Historialmora;
import Model.Letras;
import Model.Movcaja;
import Model.Pagos;
import Model.Tipodoc;
import Model.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private List<Caja> todasCajas = new ArrayList();
    private long difdays;
    private String btnpago;
    private letrasBean letbean = new letrasBean();
    private BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
    private Historialmora historial = new Historialmora();

    /**
     * Creates a new instance of pagosBean
     */
    public pagosBean() {
    }

    public List<Pagos> PagosxCredito(Credito cred) {
        PagosDao linkdao = new PagosDaoImp();
        pagosxcredito = linkdao.mostrarPagosxCredito(cred);
//        if (pagosxcredito.isEmpty() == true) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "No existen pagos de este cliente."));
//        }
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
        pago.setLetras(letra);
        if (btnpago.equals("Aplicar") && letra.getCobradonc() == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya se hizo el descuento por pronto pago. O letra vencida no procede descuento."));
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        } else {
            if (montopago.compareTo(letra.getSaldo()) != 1) {
                letra.setSaldo(letra.getSaldo().subtract(montopago));
                if (letra.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                    letra.setEstado("CN");
                }
                if (letra.getDiffdays()!=null && letra.getDiffdays() > 0) {
                    pago.setDiffdays(letra.getDiffdays());
                    pago.setCalificacion("Pago atrasado");
                } else {
                    pago.setCalificacion("Pago puntual");
                    pago.setDiffdays(new Long(0));
                }
                pago.setMonto(montopago);
                pago.setUsuario(idusuario);
                String temp = pago.getOperacion();
                pago.setOperacion(pago.getTipodoc().getAbrev().concat(" " + temp));
                pagosdao.insertarPago(pago);
                if (btnpago.equals("Pagar")) {
                    caja = pago.getCaja();
                    caja.setTotal(caja.getTotal().add(montopago));
                    mcaja.setCaja(caja);
                    mcaja.setTipomov("IN");
                    mcaja.setFechamov(pago.getFecreg());
                    mcaja.setMonto(montopago);
                    mcaja.setOrigen(letra);
                    movcbean.insertar(mcaja);
                    cajabean.modificarExt(caja);
                    letra.setCobradonc(false);
                } else {
                    letra.setCobradonc(true);
                }
                letrasdao.modificarLetra(letra);
                credito.setDeudactual(credito.getDeudactual().subtract(montopago));
                if (credito.getDeudactual().compareTo(BigDecimal.ZERO) == 0) {
                    credito.setCalificacion("CN");
                }
                creditodao.modificarVenta(credito);
                credbean.cargarLetras(credito);
                RequestContext.getCurrentInstance().update("principal");
                RequestContext.getCurrentInstance().update("formMostrar");
                RequestContext.getCurrentInstance().update("formModificar");
                RequestContext.getCurrentInstance().execute("PF('dlgpagar').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El Pago fue satisfactorio."));
                pago = new Pagos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Revise el monto", "Monto excede el saldo"));
                RequestContext.getCurrentInstance().update("formpagar");
                RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
            }
            numletra = new String();
            montopago = new BigDecimal(BigInteger.ZERO);
            descripcion = new String();
            pago = new Pagos();
        }

    }

    public void cargarLetraPagos(Letras letras) {
        letra = letras;
        listafiltrada = new ArrayList();
        btnpago = "Pagar";
        if (letras.getMora().compareTo(BigDecimal.ZERO) == 0) {
            numletra = letra.getDescripcion();
            montopago = letra.getSaldo();
            descripcion = letra.getDescripcion();
            CreditoDao linkdao = new CreditoDaoImp();
            credito = linkdao.cargarCreditoxLetra(letra);
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        } else {
            RequestContext.getCurrentInstance().update("formAlerta");
            RequestContext.getCurrentInstance().execute("PF('dlgalerta').show()");
        }
        //return letra;        
    }

    public void cargarLetraDebito(Letras letras) {
        letra = letras;
        listafiltrada = tipobean.listaTipoDoc("ND");
        numletra = letra.getDescripcion();
        montopago = letra.getMora();
        descripcion = letra.getDescripcion();        
        pago = new Pagos();
        RequestContext.getCurrentInstance().update("formNotadebito");
        RequestContext.getCurrentInstance().execute("PF('dlgnotadebito').show()");
    }

    public void cargarMora(Letras letras, Usuario usuario) {
        letra = letras;
        listafiltrada = tipobean.listaTipoDoc("ND");
        numletra = letra.getDescripcion();
        montopago = letra.getMora();
        descripcion = letra.getDescripcion();
        CreditoDao linkdao = new CreditoDaoImp();
        credito = linkdao.cargarCreditoxLetra(letra);
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("JE")) {
            moraant = letra.getMora();
            difdays = letra.getDiffdays();
            RequestContext.getCurrentInstance().update("formModNd");
            RequestContext.getCurrentInstance().execute("PF('dlgmodnd').show()");
        } else {
            RequestContext.getCurrentInstance().update("formModificar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para cambiar mora."));
        }
    }

    public void modificarMora(Usuario usuario) {
        HistoDao histodao = new HistoDaoImp();
        LetrasDao letdao = new LetrasDaoImplements();
        Date fec = new Date();
        try {
            letra.setModificadond(true);
            letra.setModificado(usuario.getIdusuario());
            letdao.modificarLetra(letra);
            historial.setLetras(letra);
            historial.setFecreg(fec);
            historial.setMontoanterior(moraant);
            historial.setMonto(letra.getMora());
            historial.setUsuario(usuario.getIdusuario());
            historial.setDiffdays(difdays);
            histodao.insertarHist(historial);
            credbean.cargarLetras(credito);
            RequestContext.getCurrentInstance().update("principal");
            RequestContext.getCurrentInstance().update("formMostrar");
            RequestContext.getCurrentInstance().update("formModificar");
            RequestContext.getCurrentInstance().execute("PF('dlgmodnd').hide()");
            pago = new Pagos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se modificó la mora del Cliente."));
        } catch (Exception e) {
        }
    }

    public void insertarNotaDebito(Usuario usuario) {
        LetrasDao letrasdao = new LetrasDaoImplements();
        PagosDao pagosdao = new PagosDaoImp();
        try {
            letra.setMora(BigDecimal.ZERO);
            letra.setDiffdays(Long.valueOf(0));
            difdays = letra.getDiffdays();
            letrasdao.modificarLetra(letra);
            letra = new Letras();
            //ago = new Pagos();           
            letra.setCredito(credito);
            letra.setDescripcion("ND");
            letra.setFecreg(pago.getFecreg());
            letra.setFecven(letra.getFecreg()/*fechafin*/);
            letra.setMontoletra(montopago);
            letra.setInteres(BigDecimal.ZERO);
            letra.setSaldo(BigDecimal.ZERO/*letra.getMonto()*/);
            letra.setMora(BigDecimal.ZERO);
            letra.setEstado("CN");
            letra.setDiffdays(difdays);
            letrasdao.insertarLetra(letra);
            pago.setUsuario(usuario.getIdusuario());
            pago.setLetras(letra);
            pago.setFecreg(letra.getFecreg());
            String temp = pago.getOperacion();
            pago.setOperacion(pago.getTipodoc().getAbrev().concat(" " + temp));
            pago.setMonto(montopago);
            pago.setTipo("ND");
            pagosdao.insertarPago(pago);
            caja = pago.getCaja();
            caja.setTotal(caja.getTotal().add(montopago));
            mcaja.setCaja(caja);
            mcaja.setTipomov("IN");
            mcaja.setFechamov(pago.getFecreg());
            mcaja.setMonto(montopago);
            mcaja.setOrigen(letra);
            movcbean.insertar(mcaja);
            cajabean.modificarExt(caja);
            RequestContext.getCurrentInstance().update("principal");
            RequestContext.getCurrentInstance().update("formMostrar");
            RequestContext.getCurrentInstance().update("formModificar");
            RequestContext.getCurrentInstance().execute("PF('dlgnotadebito').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Insertó Nota Débito correctamente."));
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formModificar");
            RequestContext.getCurrentInstance().execute("PF('dlgnotadebito').show()");
        }
        numletra = new String();
        montopago = new BigDecimal(BigInteger.ZERO);
        descripcion = new String();
        pago = new Pagos();
    }

    public void listaTipoDoc(String tipo) {
        listafiltrada = tipobean.listaTipoDoc(tipo);
        if (tipo.equals("NC")) {
            montopago = letra.getInteres();
            btnpago = "Aplicar";
        } else {
            montopago = letra.getSaldo();
            btnpago = "Pagar";
        }
    }

    public void disableDest(String tipo) {
        if (tipo.equals("LE")) {
            disablecaja = false;
        } else {
            disablecaja = true;
        }
    }

    public void disableDestDeb(String tipo) {
        if (tipo.equals("ND")) {
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
        return pagosxcredito;
    }

    public void pagovarios(Conceptos concepto) {        
        btnpago = "Pagar";
        montopago = concepto.getMontopago();
        disablecaja = true;
        disableoper = true;
        disablecomp = true;
        RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
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

    public List<Caja> getTodasCajas() {
        todasCajas = cajabean.getVerTodas();
        return todasCajas;
    }

    public void setTodasCajas(List<Caja> todasCajas) {
        this.todasCajas = todasCajas;
    }

    public long getDifdays() {
        return difdays;
    }

    public void setDifdays(long difdays) {
        this.difdays = difdays;
    }

    public String getBtnpago() {
        return btnpago;
    }

    public void setBtnpago(String btnpago) {
        this.btnpago = btnpago;
    }

    public letrasBean getLetbean() {
        return letbean;
    }

    public void setLetbean(letrasBean letbean) {
        this.letbean = letbean;
    }

    public BigDecimal getMoraant() {
        return moraant;
    }

    public void setMoraant(BigDecimal moraant) {
        this.moraant = moraant;
    }

    public Historialmora getHistorial() {
        return historial;
    }

    public void setHistorial(Historialmora historial) {
        this.historial = historial;
    }
}
