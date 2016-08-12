package Bean;

import Dao.AnexoDaoImplements;
import Dao.ConceptosDao;
import Dao.ConceptosDaoImp;
import Dao.CredavalDao;
import Dao.CredavalDaoImp;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Anexo;
import Model.Conceptos;
import Model.Credito;
import Model.Creditoaval;
import Model.Letras;
import Model.Modelo;
import Model.Ocupacion;
import Model.Pagos;
import Model.Usuario;
import Model.Vehiculo;
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
import org.apache.commons.lang3.StringUtils;
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
    private List<Anexo> avales = new ArrayList();
    public List<Credito> filtradafecha;
    private BigDecimal precio;
    private List<Ocupacion> ocupsxanexo = new ArrayList();
    private ocupacionBean ocupbean = new ocupacionBean();
    private int sw = 0;
    private String codigo;
    private String tipo;
    private boolean value;
    private boolean value2;
    private boolean valuei2;
    private boolean valuesi;
    private boolean valuesi2;
    private String btnaprobar;
    private String btnguardar;
    private List<Modelo> listafiltrada;
    private List<Pagos> pagosxcredito;
    private List<Ocupacion> ocupsxcredito;
    private modeloBean modbean = new modeloBean();
    private Conceptos concepto = new Conceptos();
    public Vehiculo vehiculo = new Vehiculo();

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
            modelocredito = creditodao.cargarxCodigoVenta(codigo, "CO", "CD");
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
        value2 = true;
        sw = 0;
        value = false;
        valuei2 = true;
        valuesi = false;
        valuesi2 = false;
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
            this.anexo.setFechanac(d);
            this.anexo.setEdad(0);
            this.anexo.setEstcivil("SO");
            this.anexo.setCpropia("NO");
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
        ConceptosDao condao = new ConceptosDaoImp();
        VehiculoDao vehidao = new VehiculoDaoImplements();
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
        } else {
            credito.setPrecio(precio);
            if (sw == 0) {
                try {
                    credito.setTotaldeuda(BigDecimal.ZERO);
                    credito.setDeudactual(BigDecimal.ZERO);
                    credito.setCondicionpago("CO");
                    credito.setEstado("AP");
                    credito.setEmpresa("CA");
                    credito.setCalificacion("PN");
                    credito.setSwinicial(false);
                    credito.setInicial(credito.getPrecio());
                    credito.setSaldo(BigDecimal.ZERO);
                    credito.setElaborado(usuario.getAnexo().getIdanexo());
                    creditodao.insertarVenta(credito);
                    concepto.setMontopago(credito.getInicial());
                    concepto.setTipo("CO");
                    concepto.setTotal(credito.getInicial());
                    concepto.setFecreg(credito.getFechareg());
                    concepto.setCredito(credito);
                    condao.insertarConcepto(concepto);
                    vehiculo = credito.getVehiculo();
                    vehiculo.setEstado("N");
                    vehidao.modificarVehiculo(vehiculo);
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

    public void eliminar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        if (credito.getEstado().equals("EM")) {
            creditodao.eliminarVenta(credito);
            ocupsxanexo = ocupbean.cargarxCredito(credito);
            for (int i = 0; i < ocupsxanexo.size(); i++) {
                Ocupacion get = ocupsxanexo.get(i);
                ocupbean.eliminar(get);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este crédito ya ha sido procesado. No se puede eliminar."));
            return;
        }
        credito = new Credito();
        creditos = new ArrayList();
    }

    public void exportarFormato(String codigo, String tipo, String estado) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Credito cred = new Credito();
        CreditoDao credao = new CreditoDaoImp();
        cred = credao.veryLiqventa(codigo);
        CredavalDao linkdao = new CredavalDaoImp();
        List<Creditoaval> listafiltrada = new ArrayList();
        avales = new ArrayList();
        String naval1 = null;
        String dniaval1 = null;
        String naval2 = null;
        String dniaval2 = null;
        try {
            listafiltrada = linkdao.avales(cred);
            for (int i = 0; i < listafiltrada.size(); i++) {
                Creditoaval get = listafiltrada.get(i);
                avales.add(get.getAnexo());
            }
            if (avales.size() >= 0) {
                naval1 = avales.get(0).getNombre().concat(" ").concat(avales.get(0).getApepat()).concat(" ").concat(avales.get(0).getApemat());
                dniaval1 = avales.get(0).getNumdocumento();
                naval2 = avales.get(1).getNombre().concat(" ").concat(avales.get(1).getApepat()).concat(" ").concat(avales.get(1).getApemat());
                dniaval2 = avales.get(1).getNumdocumento();
            }
        } catch (Exception e) {
        }
        if (naval1 == null) {
            naval1 = " ";
        }
        if (dniaval1 == null) {
            dniaval1 = " ";
        }
        if (naval2 == null) {
            naval2 = " ";
        }
        if (dniaval2 == null) {
            dniaval2 = " ";
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (estado.equals("DP")) {
            if (tipo.equals("CO")) {
                parametros.put("liqventa", codigo);
                File jasper = new File("D:/reporte/liquicontado.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=LIQUIDACION-" + codigo + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            } else if (tipo.equals("CD")) {
                parametros.put("liqventa", codigo);
                parametros.put("nombresaval1", naval1);
                parametros.put("dniaval1", dniaval1);
                parametros.put("nombresaval2", naval2);
                parametros.put("dniaval2", dniaval2);
                parametros.put("liqventa", codigo);
                File jasper = new File("D:/reporte/liquicredito.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=LIQUIDACION-" + codigo + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La venta no se encuentra despachada."));
            return;
        }
        con.close();
    }

    public void exportarCronograma(String codigo, String tipo, String estado) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        CredavalDao linkdao = new CredavalDaoImp();
        List<Creditoaval> listafiltrada = new ArrayList();
        avales = new ArrayList();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (estado.equals("DP")) {
            if (StringUtils.isNotBlank(codigo)) {
                parametros.put("codigo", codigo);
                File jasper = new File("D:/reporte/cronograma.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=Cronograma" + codigo + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Genere el crédito primero"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El credito no se encuentra despachada."));
            return;
        }
        con.close();
    }
    
    public void addMessageini2() {
        if (valuesi) {
            valuesi2 = true;
        } else {
            valuesi2 = false;
        }
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Conceptos getConcepto() {
        return concepto;
    }

    public void setConcepto(Conceptos concepto) {
        this.concepto = concepto;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Anexo> getAvales() {
        return avales;
    }

    public void setAvales(List<Anexo> avales) {
        this.avales = avales;
    }

    public boolean isValuesi() {
        return valuesi;
    }

    public void setValuesi(boolean valuesi) {
        this.valuesi = valuesi;
    }

    public boolean isValuesi2() {
        return valuesi2;
    }

    public void setValuesi2(boolean valuesi2) {
        this.valuesi2 = valuesi2;
    }

}
