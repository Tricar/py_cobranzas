package Bean;

import Dao.CajaDao;
import Dao.CajaDaoImp;
import Dao.ConceptosDao;
import Dao.ConceptosDaoImp;
import Dao.MovcajaDao;
import Dao.MovcajaDaoImp;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Anexo;
import Model.Caja;
import Model.Credito;
import Model.Conceptos;
import Model.Movcaja;
import Model.Pagos;
import Model.Tipodoc;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

public class conceptosBean implements Serializable {

    public Conceptos concepto = new Conceptos();
    public List<Conceptos> conceptos = new ArrayList();
    public List<Conceptos> conceptosventa = new ArrayList();
    private Credito credito = new Credito();
    private Anexo anexo = new Anexo();
    private String btnGuardar;
    private Pagos pago = new Pagos();
    private List<Tipodoc> listafiltrada = new ArrayList();
    private List<Caja> todasCajas = new ArrayList();
    private boolean disablecaja = true;
    private boolean disableoper = true;
    private boolean disablecomp = true;
    private String btnpago;
    private BigDecimal montopago = new BigDecimal(BigInteger.ZERO);
    private cajaBean cajabean = new cajaBean();
    private tipodocBean tipobean = new tipodocBean();
    private pagosBean pagbean = new pagosBean();
    private List<Conceptos> todosconceptos = new ArrayList();
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();

    /**
     * Creates a new instance of letrasBean
     */
    public conceptosBean() {
    }

    public List<Conceptos> getConceptos() {
//        ConceptosDao linkdao = new ConceptosDaoImp();
//        conceptos = linkdao.mostrarConceptos();
        return conceptos;
    }

    public List<Conceptos> getConceptosventa() {
        ConceptosDao linkdao = new ConceptosDaoImp();
        conceptosventa = linkdao.mostrarConceptosXCred(credito);
        return conceptosventa;
    }

    public List<Conceptos> mostrarSoloConceptosxCred(Credito cred) {
        credito = cred;
        ConceptosDao letdao = new ConceptosDaoImp();
        conceptos = letdao.mostrarConceptosXCred(credito);
        return conceptos;
    }

    public String nuevoant() {
        conceptos = new ArrayList();
        anexo = new Anexo();
        anexo.setIdanexo(null);
        concepto = new Conceptos();
        return "/despacho/formAnt.xhtml";
    }
    
    public String index() {
        todosconceptos = new ArrayList();
        conceptos = new ArrayList();        
        anexo = new Anexo();
        anexo.setIdanexo(null);
        concepto = new Conceptos();                
        return "/despacho/anticipos.xhtml";
    }

    public void cargarAnticipos(Anexo anex) {
        anexo = anex;
        ConceptosDao condao = new ConceptosDaoImp();
        conceptos = condao.mostrarPendXAnexos(anexo, Boolean.FALSE);
    }

    public void insertarAnticipo(Anexo anex) {
        anexo = anex;
        ConceptosDao linkDao = new ConceptosDaoImp();
        linkDao.insertarConcepto(concepto);
        concepto = new Conceptos();
    }
    
    public void filtrarFechas() {
        ConceptosDao linkDao = new ConceptosDaoImp();        
        todosconceptos = linkDao.filtrarxFechas(fecha1, fecha2, "AN", Boolean.FALSE);
    }

    public void insertar(Credito credito) {
        ConceptosDao linkDao = new ConceptosDaoImp();
        linkDao.insertarConcepto(concepto);
        concepto = new Conceptos();
    }

    public String cargarPorIdModify(Integer idconcepto) {
        ConceptosDao linkdao = new ConceptosDaoImp();        
        concepto = linkdao.veryId(idconcepto);
        anexo = concepto.getAnexo();
        conceptos = linkdao.mostrarConceptosxAnexo(anexo);
        return "/despacho/formAnt.xhtml";
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idconcepto) {
        ConceptosDao linkdao = new ConceptosDaoImp();
        concepto = linkdao.veryId(idconcepto);
        RequestContext.getCurrentInstance().update("formAnti");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }

    public void eliminar() {
        ConceptosDao condao = new ConceptosDaoImp();
        Pagos pagos = new Pagos();
        Movcaja movcaja = new Movcaja();
        Caja caja = new Caja();
        BigDecimal monto = new BigDecimal(BigInteger.ZERO);
        ConceptosDao linkdao = new ConceptosDaoImp();
        PagosDao pagosdao = new PagosDaoImp();
        CajaDao cajadao = new CajaDaoImp();
        MovcajaDao movdao = new MovcajaDaoImp();
        try {
            pagos = pagosdao.devolverxIdconcepto(concepto.getIdconceptos());
            monto = pagos.getMonto();
            caja = pagos.getCaja();
            movcaja = movdao.devolverxIdcajaIdconceptos(caja.getIdcaja(), concepto.getIdconceptos());
            caja.setTotal(caja.getTotal().subtract(monto));
            cajadao.modificarCaja(caja);
            movdao.eliminarMovcaja(movcaja);
            pagosdao.eliminarPago(pagos);
            linkdao.eliminarConcepto(concepto);
            conceptos = condao.mostrarConceptosxAnexo(anexo);
            RequestContext.getCurrentInstance().update("formAnti");
            RequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");

        } catch (Exception e) {
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

    public void limpiarAnticipo(Anexo anex) {
        anexo = anex;
        pago = new Pagos();
        montopago = BigDecimal.ZERO;
        try {
            if (anexo.getIdanexo() != null) {
                pago.setTipo("AN");
                listafiltrada = tipobean.listaTipoDoc(pago.getTipo());
                disablecaja = false;
                btnpago = "Pagar";
                RequestContext.getCurrentInstance().update("formpagar");
                RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
            }
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formAnti");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar cliente"));
        }

    }

    public void insertarAnticipo(int idusuario, Anexo anex) {
        if (montopago.compareTo(BigDecimal.ZERO) != 0) {
            btnpago = "Pagar";
            ConceptosDao condao = new ConceptosDaoImp();
            pagbean.insertarAnticipo(idusuario, anex, concepto, pago, montopago);
            conceptos = condao.mostrarPendXAnexos(anexo, Boolean.FALSE);
            RequestContext.getCurrentInstance().update("formAnti");
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Monto de anticipo debe ser mayor a cero"));
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagar').show()");
        }
    }

    public void setConceptos(List<Conceptos> letras) {
        this.conceptos = letras;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public Conceptos getConcepto() {
        return concepto;
    }

    public void setConcepto(Conceptos concepto) {
        this.concepto = concepto;
    }

    public String getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(String btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public List<Tipodoc> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Tipodoc> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public List<Caja> getTodasCajas() {
        todasCajas = cajabean.getVerTodas();
        return todasCajas;
    }

    public void setTodasCajas(List<Caja> todasCajas) {
        this.todasCajas = todasCajas;
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

    public String getBtnpago() {
        return btnpago;
    }

    public void setBtnpago(String btnpago) {
        this.btnpago = btnpago;
    }

    public BigDecimal getMontopago() {
        return montopago;
    }

    public void setMontopago(BigDecimal montopago) {
        this.montopago = montopago;
    }

    public cajaBean getCajabean() {
        return cajabean;
    }

    public void setCajabean(cajaBean cajabean) {
        this.cajabean = cajabean;
    }

    public tipodocBean getTipobean() {
        return tipobean;
    }

    public void setTipobean(tipodocBean tipobean) {
        this.tipobean = tipobean;
    }

    public pagosBean getPagbean() {
        return pagbean;
    }

    public void setPagbean(pagosBean pagbean) {
        this.pagbean = pagbean;
    }

    public List<Conceptos> getTodosconceptos() {
        return todosconceptos;
    }

    public void setTodosconceptos(List<Conceptos> todosconceptos) {
        this.todosconceptos = todosconceptos;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }
}
