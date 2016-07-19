package Bean;

import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Anexo;
import Model.Credito;
import Model.Letras;
import Model.Modelo;
import Model.Ocupacion;
import Model.Pagos;
import Model.Usuario;
import Persistencia.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import utiles.dbManager;
import utiles.precio;

@ManagedBean
@SessionScoped
public class ventaBean implements Serializable {

    private Session session;
    private Transaction transaction;
    public Anexo anexo = new Anexo();
    private String razonsocial;
    public Credito credito = new Credito();
    public List<Credito> creditos = new ArrayList();
    public List<Letras> letraslista = new ArrayList();
    public List<Credito> filtradafecha;
    private BigDecimal precio;
    private List<Ocupacion> ocupsxanexo = new ArrayList();
    private ocupacionBean ocupbean = new ocupacionBean();
    private int sw = 0;
    private String codigo;
    private boolean value;
    private boolean value2;
    private boolean valuei2;
    private String btnaprobar;
    private String btnguardar;
    private List<Modelo> listafiltrada;
    private List<Pagos> pagosxcredito;
    private List<Ocupacion> ocupsxcredito;
    private modeloBean modbean = new modeloBean();

    public ventaBean() {

    }

    public String venta() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/despacho/venta.xhtml";
    }
    
    public void nuevoanexo() {
        anexo = new Anexo();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
    }
    
    public void modeloTipo(String tipo) {
        listafiltrada = modbean.modeloTipo(tipo);
    }

    public boolean disableGuardar() {
        if (sw == 1) {
            return true;
        }
        return false;
    }

    public void cargarxCodigoVenta() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEst(codigo, "CO");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void precioModeloVenta() {
        precio Precio = new precio();
        precio = (Precio.precioModelo(credito.getModelo().getModelo()));
    }

    public String indexventa() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        filtradafecha = new ArrayList();
        return "/despacho/venta.xhtml";
    }

    public String nuevo() {
        credito = new Credito();
        precio = BigDecimal.ZERO;
        value2 = true;
        sw = 0;
        value = false;
        valuei2 = true;
        btnaprobar = "Aprobar";
        btnguardar = "Guardar";
        ocupsxanexo = new ArrayList();
        return "/despacho/form.xhtml";
    }
    
    public void insertarcliente() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (daotanexo.verByDocumento(this.session, this.anexo.getNumdocumento()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Aval ya existe en DB."));
                this.anexo = new Anexo();
                return;
            }
            if (this.razonsocial != null) {
                this.anexo.setNombre(this.razonsocial);
                this.anexo.setApemat("");
                this.anexo.setApepat("");
            }
            if (this.anexo.getTipodocumento().equals("DNI")) {
                this.anexo.setTipoanexo("CN");
            } else {
                this.anexo.setTipoanexo("CJ");
            }
            Date d = new Date();
            this.anexo.setFechareg(d);
            this.anexo.setCodven("");
            daotanexo.registrar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void insertarVenta(Usuario usuario) {
        CreditoDao creditodao = new CreditoDaoImp();
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
        } else {
            credito.setPrecio(precio);
            if (sw == 0) {
                try {
                    credito.setTotaldeuda(BigDecimal.ZERO);
                    credito.setCondicionpago("CO");
                    credito.setEstado("EM");
                    credito.setEmpresa("CA");
                    credito.setCalificacion("PN");
                    credito.setInicial(BigDecimal.ZERO);
                    credito.setElaborado(usuario.getAnexo().getIdanexo());
                    creditodao.insertarVenta(credito);
                    sw = 1;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La venta se registró correctamente."));
                } catch (Exception e) {

                }
            } else {
                if (sw == 1) {
                    btnaprobar = "Desaprobar";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este crédito ya ha sido registrado"));
                    return;
                }
            }
        }
    }

    public void exportarPDF(String codigo) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codigo", codigo);
        System.out.println("liq venta :" + codigo);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/proforma.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Proforma" + codigo + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public List<Credito> getVentas() {
        CreditoDao linkdao = new CreditoDaoImp();
        creditos = linkdao.mostrarVentas();
        return creditos;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }
    
    public List<Credito> getFiltradafecha() {
        return filtradafecha;
    }

    public void setVentas(List<Credito> creditos) {
        this.creditos = creditos;
    }
    
    public List<Letras> getLetraslista() {
        return letraslista;
    }

    public void setLetraslista(List<Letras> letraslista) {
        this.letraslista = letraslista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }
    
    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValuei2() {
        return valuei2;
    }

    public void setValuei2(boolean valuei2) {
        this.valuei2 = valuei2;
    }

    public String getBtnaprobar() {
        return btnaprobar;
    }

    public void setBtnaprobar(String btnaprobar) {
        this.btnaprobar = btnaprobar;
    }

    public List<Ocupacion> getOcupsxanexo() {
        return ocupsxanexo;
    }

    public void setOcupsxanexo(List<Ocupacion> ocupsxanexo) {
        this.ocupsxanexo = ocupsxanexo;
    }

    public ocupacionBean getOcupbean() {
        return ocupbean;
    }

    public void setOcupbean(ocupacionBean ocupbean) {
        this.ocupbean = ocupbean;
    }

    public modeloBean getModbean() {
        return modbean;
    }

    public void setModbean(modeloBean modbean) {
        this.modbean = modbean;
    }

    public List<Modelo> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Modelo> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public List<Pagos> getPagosxcredito() {
        return pagosxcredito;
    }

    public void setPagosxcredito(List<Pagos> pagosxcredito) {
        this.pagosxcredito = pagosxcredito;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Ocupacion> getOcupsxcredito() {
        return ocupsxcredito;
    }

    public void setOcupsxcredito(List<Ocupacion> ocupsxcredito) {
        this.ocupsxcredito = ocupsxcredito;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
    
    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRazonsocial() {
        return razonsocial;
    }
    
}
