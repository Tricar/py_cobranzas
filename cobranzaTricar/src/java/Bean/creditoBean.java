package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CajaDao;
import Dao.CajaDaoImp;
import Dao.ConceptosDao;
import Dao.ConceptosDaoImp;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.MovcajaDao;
import Dao.MovcajaDaoImp;
import Dao.OcupacionDao;
import Dao.OcupacionDaoImpl;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Anexo;
import Model.Caja;
import Model.Conceptos;
import Model.Credito;
import Model.Letras;
import Model.Modelo;
import Model.Movcaja;
import Model.Ocupacion;
import Model.Pagos;
import Model.Tipodoc;
import Model.Usuario;
import Model.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
import org.primefaces.context.RequestContext;
import utiles.dbManager;
import utiles.inicial;
import utiles.precio;

@ManagedBean
@SessionScoped
public class creditoBean implements Serializable {

    public Credito credito = new Credito();
    public Vehiculo vehiculo = new Vehiculo();
    public Credito crediton = new Credito();
    public Letras letrasa = new Letras();
    public List<Credito> creditos = new ArrayList();
    LetrasDao letrasdao = new LetrasDaoImplements();
    public List<Letras> letrascredito;
    public List<Credito> filtradafecha;
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();
    public List<Letras> letraslista = new ArrayList();
    private Pagos pago = new Pagos();
    private Letras letra = new Letras();
    private String dni;
    private BigDecimal res;
    private BigDecimal precio;
    private BigDecimal inicia = new BigDecimal(BigInteger.ZERO);
    private BigDecimal iniciapre;
    private String codigo;
    private Date fechafin;
    private String nombre;
    private BigDecimal saldo;
    private int sw = 0;
    private String vehi;
    private String descND;
    private boolean value;
    private boolean value2;
    private boolean valuei;
    private boolean valuei2;
    private Anexo anexo;
    private String btnaprobar;
    private String btnguardar;
    private boolean aprobar;
    private boolean disableaval;
    private List<Ocupacion> ocupsxanexo = new ArrayList();
    private ocupacionBean ocupbean = new ocupacionBean();
    public Ocupacion objocup = new Ocupacion();
    private modeloBean modbean = new modeloBean();
    private List<Modelo> listafiltrada;
    private List<Pagos> pagosxcredito;
    private List<Ocupacion> ocupsxcredito;
    private List<String> listaocups = new ArrayList();
    private String bandera = "ND";
    private String mensaje;
    private List<Ocupacion> listamodificar = new ArrayList();
    private boolean disabledespacho = false;
    private Conceptos concepto = new Conceptos();
    private List<Conceptos> conceptos = new ArrayList();
    private BigDecimal montopago;
    private String btnpago;
    private boolean disablecaja;
    private List<Tipodoc> listafilttipo = new ArrayList();
    private boolean disableoper;
    private boolean disablecomp;
    private List<Caja> todasCajas = new ArrayList();
    private List<Date> fechas = new ArrayList();
    private BigDecimal sinicial = new BigDecimal(BigInteger.ZERO);
    private boolean valuesi;
    private boolean valuesi2;
    private List<Anexo> avales = new ArrayList();
    private List<Anexo> avalesant = new ArrayList();
    private List<Anexo> avalesmod = new ArrayList();
    private String banderaval;
    private String[] selectedReqs;    
    private String banderareq = new String();
    private List<String> reqs;
    
    @PostConstruct
    public void init() {
        reqs = new ArrayList<String>();
        reqs.add("1.Copia DNI Titular");
        reqs.add("2.Copia DNI conyuge");
        reqs.add("3.Copia DNI aval(es)");
        reqs.add("4.Copia de Título o Constancia");
        reqs.add("5.Copia Recibo de agua");
        reqs.add("6.Copia Recibo de luz");
        reqs.add("7.Copia de Croquis");
        reqs.add("8.Copia de Recibo de ingreso");
        reqs.add("9.Otros");
    }

    public creditoBean() {
    }

    public void resultadoId() {
        if (credito.getAnexo() != (null)) {
            anexo = credito.getAnexo();
        } else {
            anexo = null;
        }
    }

    public void resultadoSaldo() {
        res = precio.subtract(credito.getInicial());
    }

    public void resSaldo() {
        saldo = precio.subtract(inicia);
    }

    public void precioModeloCredito() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));
        String distrito = credito.getAnexo().getDistrito();
        String tipov = credito.getVehiculo().getTipovehiculo();
        iniciapre = Inicial.inicialCredito(distrito, tipov, precio, credito.getVehiculo().getModelo().getModelo());
        saldo = precio.subtract(inicia);
    }

    public void precioModeloCreditoEspecial() {
        precio Precio = new precio();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));

    }

    public void precioModeloCotiza() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getModelo().getModelo()));
        String distrito = credito.getAnexo().getDistrito();
        String tipov = credito.getVehi();
        iniciapre = Inicial.inicialCredito(distrito, tipov, precio, credito.getModelo().getModelo());
        saldo = precio.subtract(inicia);
    }

    public void insertarCredito(Usuario usuario) {
        CreditoDao creditodao = new CreditoDaoImp();
        credavalBean credavalbean = new credavalBean();
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
        } else {
            if (!valuei) {
                if (inicia.compareTo(iniciapre) == -1 || inicia == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial debe ser Mayor."));
                    return;
                } else {
                    credito.setInicial(inicia);
                }
            } else {
                credito.setInicial(inicia);
            }
            if (ocupsxanexo.isEmpty() == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, Cliente debe tener al menos un ingreso econónmico", "El cliente debe tener al menos un ingreso económico"));
            } else {
                credito.setPrecio(precio);
                credito.setInicial(inicia);
                credito.setSaldo(credito.getPrecio().subtract(credito.getInicial()));
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                    if (!valuesi2) {
                        Calendar fecven = Calendar.getInstance();
                        Calendar fecreg = Calendar.getInstance();
                        Calendar fec2ini = Calendar.getInstance();
                        fec2ini.setTime(fecha2);
                        fecreg.setTime(credito.getFechareg());
                        fecven.setTime(credito.getFechareg());
                        fecven.add(Calendar.DAY_OF_YEAR, 15);
                        if (fec2ini.before(fecven) && fec2ini.after(fecreg)) {
                            try {
                                credito.setFecsinicial(fecha2);
                                credito.setTotaldeuda(BigDecimal.ZERO);
                                credito.setEstado("EM");
                                credito.setSinicial(sinicial);
                                credito.setEmpresa("CA");
                                credito.setCalificacion("PN");
                                credito.setElaborado(usuario.getAnexo().getIdanexo());
                                credito.setSwinicial(false);
                                creditodao.insertarVenta(credito);
                                if (avales.isEmpty() == false) {
                                    credavalbean.insertarCreditoAval(credito, avales);
                                }
                                sw = 1;
                                for (int i = 0; i < ocupsxanexo.size(); i++) {
                                    Ocupacion get = ocupsxanexo.get(i);
                                    ocupbean.insertarCredito(credito.getIdventa(), get);
                                }
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El crédito se registró correctamente."));
                            } catch (Exception e) {
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Modifique la fecha de la segunda inicial"));
                        }
                    } else {
                        try {
                            credito.setTotaldeuda(BigDecimal.ZERO);
                            credito.setSinicial(BigDecimal.ZERO);
                            credito.setEstado("EM");
                            credito.setEmpresa("CA");
                            credito.setCalificacion("PN");
                            credito.setElaborado(usuario.getAnexo().getIdanexo());
                            credito.setSwinicial(false);
                            creditodao.insertarVenta(credito);
                            if (avales.isEmpty() == false) {
                                credavalbean.insertarCreditoAval(credito, avales);
                            }
                            sw = 1;
                            for (int i = 0; i < ocupsxanexo.size(); i++) {
                                Ocupacion get = ocupsxanexo.get(i);
                                ocupbean.insertarCredito(credito.getIdventa(), get);
                            }
                            System.out.println("valor de la lista avales: " + avales.isEmpty());
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El crédito se registró correctamente."));
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (sw == 1) {
                        btnaprobar = "Desaprobar";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este crédito ya ha sido registrado"));
                        return;
                    }
                    if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Esto es una venta al contado"));
                        return;
                    }
                    if (credito.getSaldo().compareTo(BigDecimal.ZERO) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial supera monto de vehículo"));
                        return;
                    }
                }
            }
        }
    }

    public void solicitarDeProf(Integer idusuario) {
        CreditoDao credao = new CreditoDaoImp();
        LetrasDao letradao = new LetrasDaoImplements();
        if (credao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El código de venta ya existe."));
        } else {
            if (!valuei) {
                if (inicia.compareTo(iniciapre) == -1 || inicia == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial debe ser Mayor."));
                    return;
                } else {
                    credito.setInicial(inicia);
                }
            } else {
                credito.setInicial(inicia);
            }
            if (credito.getAnexo().getIdanexo().equals(credito.getIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El aval no puede ser el mismo cliente."));
            } else {
                credito.setPrecio(precio);
                credito.setSaldo(precio.subtract(inicia));
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                    try {
                        credito.setTotaldeuda(BigDecimal.ZERO);
                        credito.setEstado("EM");
                        credito.setModificado(idusuario);
                        letrascredito = letradao.mostrarLetrasXCred(credito);
                        for (int i = 0; i < letrascredito.size(); i++) {
                            Letras get = letrascredito.get(i);
                            letradao.eliminarLetra(get);
                        }
                        credao.modificarVenta(credito);
                        sw = 1;
                        for (int i = 0; i < ocupsxanexo.size(); i++) {
                            Ocupacion get = ocupsxanexo.get(i);
                            ocupbean.insertarCredito(credito.getIdventa(), get);
                        }
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La solicitud se registró correctamente."));
                    } catch (Exception e) {

                    }
                } else {
                    if (sw == 1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este crédito ya ha sido registrado"));
                    }

                }

            }
        }
    }

    public void lanzarDlgModificar() {
        if (credito.getEstado().equals("AP")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, este crédito ya ha sido aprobado. No se puede modificar", "Crédito Aprobado, no se puede modificar."));
            return;
        } else {
            avalesmod = new ArrayList();
            if (avalesant.isEmpty() == false && avales.isEmpty() == false) {
                if (avalesant.size() > avales.size()) {
                    banderaval = "RES";
                    for (int i = 0; i < avalesant.size(); i++) {
                        int c = 0;
                        Anexo get = avalesant.get(i);
                        for (int j = 0; j < avales.size(); j++) {
                            Anexo get1 = avales.get(j);
                            if (get.getIdanexo().equals(get1.getIdanexo())) {
                                c++;
                            }
                        }
                        if (c == 0) {
                            avalesmod.add(get);
                        }
                    }
                } else {
                    if (avales.size() > avalesant.size()) {
                        banderaval = "SUM";
                        for (int i = 0; i < avales.size(); i++) {
                            int c = 0;
                            Anexo get = avales.get(i);
                            for (int j = 0; j < avalesant.size(); j++) {
                                Anexo get1 = avalesant.get(j);
                                if (get.getIdanexo().equals(get1.getIdanexo())) {
                                    c++;
                                }
                            }
                            if (c == 0) {
                                avalesmod.add(get);
                            }
                        }
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('dlgmodificar').show()");
        }
    }

    public void modificarCredito() {
        requisitosBean reqsbean = new requisitosBean();
        credavalBean credavalbean = new credavalBean();
        CreditoDao credao = new CreditoDaoImp();
        if (btnguardar.equals("Modificar")) {
            if (inicia.compareTo(credito.getInicial()) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial debe ser Mayor."));
                return;
            } else {
                credito.setInicial(inicia);
            }
            if (sinicial.compareTo(credito.getSinicial()) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "2DA inicial debe ser Mayor."));
                return;
            } else {
                credito.setSinicial(sinicial);
            }
            try {
                if (bandera.equals("SUM")) {
                    for (int j = 0; j < listamodificar.size(); j++) {
                        Ocupacion get = listamodificar.get(j);
                        ocupbean.insertarCredito(credito.getIdventa(), get);
                    }
                }
                if (bandera.equals("RES")) {
                    for (int j = 0; j < listamodificar.size(); j++) {
                        Ocupacion get = listamodificar.get(j);
                        ocupbean.eliminar(get);
                    }
                }
            } catch (Exception e) {
            }
            if (avalesant.isEmpty() && avales.isEmpty() == false) {
                credavalbean.insertarCreditoAval(credito, avales);
            }
            try {
                if (banderaval.equals("SUM")) {
                    for (int i = 0; i < avalesmod.size(); i++) {
                        Anexo get = avalesmod.get(i);
                        credavalbean.insertar(credito, get);
                    }
                }
                if (banderaval.equals("RES")) {
                    for (int j = 0; j < avalesmod.size(); j++) {
                        Anexo get = avalesmod.get(j);
                        credavalbean.eliminar(get, credito);
                    }
                }
            } catch (Exception e) {
            }
            try {
                reqsbean.eliminarParaModificar(credito);
                reqsbean.modificar(credito, selectedReqs);
            } catch (Exception e) {
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Se Modificó la solicitud de crédito"));
            credito.setSaldo(credito.getPrecio().subtract((credito.getInicial().add(credito.getSinicial()))));
            credao.modificarVenta(credito);
        }
        ocupsxanexo = ocupbean.cargarxCredito(credito);
    }

    public void limpiarlistamod() {
        listamodificar = new ArrayList();
        RequestContext.getCurrentInstance().execute("PF('dlgmodificar').hide()");
    }

    public void insertarCreditoEspecial() {
        CreditoDao creditodao = new CreditoDaoImp();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
        } else {
            if (inicia.compareTo(iniciapre) == -1 || inicia == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial debe ser Mayor."));
                return;
            } else {
                credito.setInicial(inicia);
            }
            if (credito.getAnexo().getIdanexo().equals(credito.getIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El aval no puede ser el mismo cliente."));
            } else {
                credito.setPrecio(precio);
                credito.setSaldo(saldo);
                credito.setTotaldeuda(credito.getSaldo());
                credito.setDeudactual(credito.getSaldo());
                credito.setInteres(BigDecimal.ZERO);
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                    //vehiculo = credito.getVehiculo();
                    //vehiculo.setEstado("N");
                    //vehiculodao.modificarVehiculo(vehiculo);
                    credito.setTotaldeuda(credito.getSaldo());
                    Letras letras = new Letras();
                    BigDecimal nletras = new BigDecimal(BigInteger.ONE);
                    BigDecimal montoletras = credito.getSaldo();
                    BigDecimal interes = new BigDecimal(0);
                    credito.setEstado("AP");
                    creditodao.insertarVenta(credito);
                    Date fechaini = new Date();
                    fechaini = credito.getFechareg();
                    Date fechafin = new Date();
                    fechafin = sumaDias(fechaini, 60);
                    BigDecimal mtointeres = BigDecimal.ZERO;
                    letras.setFecreg(credito.getFechareg());
                    letras.setCredito(credito);
                    letras.setMontoletra(montoletras);
                    letras.setInteres(mtointeres);
                    letras.setMonto(credito.getSaldo());
                    letras.setFecini(fechaini);
                    letras.setFecven(fechafin);
                    letras.setMora(BigDecimal.ZERO);
                    letras.setSaldo(letras.getMonto());
                    letras.setEstado("PN");
                    letras.setDescripcion("L1/L1");
                    letrasdao.insertarLetra(letras);
                    sw = 1;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
                } else {
                    if (sw == 1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este crédito ya ha sido registrado"));
                        return;
                    }
                    if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Esto es una venta al contado"));
                        return;
                    }
                    if (credito.getSaldo().compareTo(BigDecimal.ZERO) == -1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial supera monto de vehículo"));
                    }
                }
            }
        }
    }

    public void refinanciar(Usuario usuario) {
        CreditoDao credao = new CreditoDaoImp();
        List<Letras> antiguas = new ArrayList();
        Letras nuevas = new Letras();
        try {
            if (btnguardar.equals("Solicitar")) {
                if (credito.getLiqventa().endsWith("A")) {
                    crediton.setLiqventa(credito.getLiqventa().concat("A"));
                } else {
                    crediton.setLiqventa(credito.getLiqventa().concat("-A"));
                }
                crediton.setFechareg(fecha1);
                crediton.setElaborado(usuario.getIdusuario());
                crediton.setEstadoref("EM");
                crediton.setCalificacion("PN");
                crediton.setRefinanciado(true);
                crediton.setAnterior(credito.getIdventa());
                credao.insertarVenta(crediton);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se solicitó la refinanciación."));
                value = true;
            } else {
                if (usuario.getPerfil().getAbrev().equals("JE") || usuario.getPerfil().getAbrev().equals("AD")) {
                    credito = credao.veryId(crediton.getAnterior());
                    antiguas = letrasdao.mostrarLetrasXCred(credito);
                    for (int i = 0; i < antiguas.size(); i++) {
                        Letras get = antiguas.get(i);
                        //get.setSaldo(BigDecimal.ZERO);
                        if (get.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                            get.setEstado("RF");
                            get.setDescripcion("Ref. :" + crediton.getLiqventa());
                            //get.setSaldo(BigDecimal.ZERO);
                            letrasdao.modificarLetra(get);
                        }
                    }
                    BigDecimal deuda = credito.getDeudactual();
                    credito.setDeudactual(BigDecimal.ZERO);
                    credito.setCalificacion("CN");
                    credito.setRefinanciado(true);
                    credao.modificarVenta(credito);
                    BigDecimal nletras = new BigDecimal(crediton.getNletras());
                    BigDecimal montoletras = deuda.divide(nletras, 2);
                    BigDecimal interes = new BigDecimal(0);
                    crediton.setEstado("DP");
                    crediton.setEstadoref("AP");
                    crediton.setTotaldeuda(BigDecimal.ZERO);
                    BigDecimal cien = new BigDecimal(100);
                    interes = (crediton.getInteres().multiply(nletras)).divide(cien);
                    Date fechaini = new Date();
                    fechaini = crediton.getFechareg();
                    Date fechafin = new Date();
                    fechafin = sumaDias(fechaini, 30);
                    BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
                    nuevas.setFecreg(crediton.getFechareg());
                    for (int i = 1; i <= (crediton.getNletras()); i++) {
                        nuevas.setCredito(crediton);
                        nuevas.setMontoletra(montoletras);
                        nuevas.setInteres(mtointeres);
                        nuevas.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                        nuevas.setFecini(fechaini);
                        nuevas.setFecven(fechafin);
                        nuevas.setMora(BigDecimal.ZERO);
                        fechaini = fechafin;
                        fechafin = sumaDias(fechaini, 30);
                        nuevas.setSaldo(nuevas.getMonto());
                        nuevas.setEstado("PN");
                        nuevas.setDescripcion("L" + i + "/L" + crediton.getNletras());
                        crediton.setTotaldeuda(crediton.getTotaldeuda().add(nuevas.getSaldo()));
                        crediton.setDeudactual(crediton.getTotaldeuda());
                        letrasdao.insertarLetra(nuevas);
                    }
                    crediton.setAprobado(usuario.getIdusuario());
                    value = true;
                    credao.modificarVenta(crediton);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se aprobó la solicitud."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No tiene permisos para aprobar la solicitud."));
                }
            }
        } catch (Exception e) {
        }

    }

    public String cargarRefinanciar(int idusuario) {
        crediton = credito;
        fecha1 = null;
        value = false;
        modeloTipo(credito.getVehi());
        sw = 1;
        inicial Inicial = new inicial();
        inicia = crediton.getInicial();
        precio = crediton.getPrecio();
        saldo = precio.subtract(inicia);
        anexo = crediton.getAnexo();
        if (credito.getEstadoref() != null && credito.getEstadoref().equals("EM")) {
            btnguardar = "Aprobar";
            return "/refinanciar/formaprobref.xhtml";
        } else {
            btnguardar = "Solicitar";
            return "/refinanciar/formrefinancia.xhtml";
        }
    }

    public void insertarCotiza(Integer idusuario) {
        CreditoDao creditodao = new CreditoDaoImp();
        credito.setPrecio(precio);
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "La proforma ya existe, no se puede volver a registrar."));
        } else {
            if (inicia.compareTo(iniciapre) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial debe ser Mayor."));
                return;
            } else {
                credito.setInicial(inicia);
            }
            credito.setSaldo(precio.subtract(inicia));
            if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                Letras letras = new Letras();
                BigDecimal nletras = new BigDecimal(credito.getNletras());
                BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
                BigDecimal interes = new BigDecimal(0);
                BigDecimal cien = new BigDecimal(100);
                credito.setEstado("NA");
                credito.setEmpresa("CA");
                credito.setCalificacion("PN");
                credito.setElaborado(idusuario);
                credito.setSwinicial(false);
                creditodao.insertarVenta(credito);
                interes = (credito.getInteres().multiply(nletras)).divide(cien);
                Date fechaini = new Date();
                fechaini = credito.getFechareg();
                //Date fechafin = new Date();
                fechas = fechasLetras(fechaini);
                BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
                letras.setFecreg(credito.getFechareg());
                for (int i = 1; i <= (credito.getNletras()); i++) {
                    Date fechaf = fechas.get(i - 1);
                    letras.setCredito(credito);
                    letras.setMontoletra(montoletras);
                    letras.setInteres(mtointeres);
                    letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                    letras.setFecini(fechaini);
                    letras.setFecven(fechaf);
                    fechaini = fechaf;
                    //fechafin = sumaDias(fechaini, 30);
                    letras.setSaldo(letras.getMonto());
                    letras.setEstado("NA");
                    letras.setMora(BigDecimal.ZERO);
                    letras.setDescripcion("L" + i + "/L" + credito.getNletras());
                    letrasdao.insertarLetra(letras);
                }
                sw = 1;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La proforma se realizó correctamente."));
            } else {
                if (sw == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Esta proforma ya ha sido registrada"));
                    return;
                }
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Esto es una venta al contado"));
                    return;
                }
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == -1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Inicial supera monto de vehículo"));
                }

            }
        }
    }

    public void eliminar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
//        List<Letras> letrita = new ArrayList();
        Vehiculo vehiculo = new Vehiculo();
//        VehiculoDao vehiculodao = new VehiculoDaoImplements();
//        letrita = letrasdao.mostrarLetrasXCred(credito);
//        BigDecimal totaldeuda = new BigDecimal(BigInteger.ZERO);
//        for (int i = 0; i < letrita.size(); i++) {
//            Letras get = letrita.get(i);
//            totaldeuda = totaldeuda.add(get.getMonto());
//        }
        if (credito.getEstado().equals("EM")) {
//            vehiculo = credito.getVehiculo();
//            vehiculo.setEstado("D");
//            vehiculodao.modificarVehiculo(vehiculo);
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

    public void eliminarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        try {
            creditodao.eliminarVenta(credito);
        } catch (Exception e) {
        }
        credito = new Credito();
        creditos = new ArrayList();
    }

    public Date sumaDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
    }

    public List fechasLetras(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int sumar = 0;
        int inicial = 0;
        int fin = 0;
        int difdays = 0;
        boolean sw = true;
        for (int i = 0; i < credito.getNletras(); i++) {
            if (sw) {
                inicial = cal.get(Calendar.DATE);
                int j = 1;
                cal.add(Calendar.MONTH, j);
                System.out.println(cal.getTime());
                fechas.add(cal.getTime());
                fin = cal.get(Calendar.DATE);
            }
            sw = true;
            if (inicial - fin != 0) {
                difdays = inicial - fin;
                sumar = fin + difdays;
                cal.add(Calendar.DATE, sumar);
                System.out.println(cal.getTime());
                fechas.add(cal.getTime());
                inicial = 0;
                fin = 0;
                sw = false;
            }
        }
        return fechas;
    }

    public long Diffdays(Date fechavenc) {
        long mili = fechavenc.getTime();
        long mili2 = new Date().getTime();
        long diff = mili2 - mili;
        long diffdays = diff / (24 * 60 * 60 * 1000);
        return diffdays;
    }

    public void filtrarFechas() {
        CreditoDao linkdao = new CreditoDaoImp();
        filtradafecha = linkdao.filtrarFechas(fecha1, fecha2, "AP");
        letraslista = new ArrayList();
    }

    public void cargarCredito(Anexo anexo) {
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal cien = new BigDecimal((BigInteger.TEN).multiply(BigInteger.TEN));
        cinco = cinco.divide(cien);
        credito = creditodao.cargarCreditoxAnexo(anexo);
        LetrasDao letras = new LetrasDaoImplements();
        letritas = letras.mostrarLetrasXCred(credito);
        for (int i = 0; i < letritas.size(); i++) {
            Letras get = letritas.get(i);
            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                get.setEstado("CN");
            } else {
                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
                    if (get.getFecven().after(fecha)) {
                        get.setEstado("PN");
                    } else {
                        get.setEstado("VN");
                    }
                }
            }
            moraant = get.getMora();
            moraact = ((get.getSaldo().multiply(cinco)).setScale(2)).setScale(1, RoundingMode.UP);
            if (get.getEstado().equals("VN")) {
                if (moraact.compareTo(moraant) == -1) {
                    get.setMora(moraant);
                } else {
                    get.setMora(moraact);
                }
            }
            letrasdao.modificarLetra(get);
        }
        letraslista = letras.mostrarLetrasXCred(credito);
    }

    public void cargarLetras(Credito cred) {
        letraslista = new ArrayList();
//        CreditoDao creditodao = new CreditoDaoImp();
//        List<Letras> letritas = new ArrayList();
//        Calendar calendario = GregorianCalendar.getInstance();
//        Date fecha = calendario.getTime();
//        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
//        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
//        BigDecimal cinco = new BigDecimal(5);
//        BigDecimal cien = new BigDecimal(100);
//        cinco = cinco.divide(cien);
//        LetrasDao letrasdao = new LetrasDaoImplements();
//        letritas = letrasdao.mostrarLetrasXCred(cred);
//        for (int i = 0; i < letritas.size(); i++) {
//            Letras get = letritas.get(i);
//            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
//                get.setEstado("CN");
//            } else {
//                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
//                    if (get.getFecven().after(fecha)) {
//                        get.setEstado("PN");
//                    } else {
//                        get.setEstado("VN");
//                        get.setDiffdays(Diffdays(get.getFecven()));
//                    }
//                }
//            }
//            moraant = get.getMora();
//            moraact = (get.getSaldo().multiply(cinco)).setScale(1, RoundingMode.UP);
//            if (get.getEstado().equals("VN")) {
//                if (moraact.compareTo(moraant) == -1) {
//                    get.setMora(moraant);
//                } else {
//                    get.setMora(moraact);
//                }
//            }
//            letrasdao.modificarLetra(get);
//        }
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public List<Letras> cargarLetrasArray(Credito cred) {
        letrasBean letbean = new letrasBean();
        letraslista = new ArrayList();
//        CreditoDao creditodao = new CreditoDaoImp();
//        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
//        Date fecha = calendario.getTime();
//        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
//        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
//        BigDecimal cinco = new BigDecimal(5);
//        BigDecimal cien = new BigDecimal(100);
//        cinco = cinco.divide(cien);
//        LetrasDao letrasdao = new LetrasDaoImplements();
//        letritas = letrasdao.mostrarLetrasXCred(cred);
//        for (int i = 0; i < letritas.size(); i++) {
//            Letras get = letritas.get(i);
//            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
//                get.setEstado("CN");
//            } else {
//                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
//                    if (get.getFecven().after(fecha)) {
//                        get.setEstado("PN");
//                    } else {
//                        get.setEstado("VN");
//                        get.setDiffdays(Diffdays(get.getFecven()));
//                        get.setCobradonc(true);
//                    }
//                }
//            }
//            moraant = get.getMora();
//            moraact = (get.getSaldo().multiply(cinco)).setScale(1, RoundingMode.UP);
//            if (get.getEstado().equals("VN")) {
//                if (moraact.compareTo(moraant) == -1) {
//                    get.setMora(moraant);
//                } else {
//                    get.setMora(moraact);
//                }
//            }
//            letrasdao.modificarLetra(get);
//        }
        return letraslista = letbean.mostrarSoloLetrasxCred(cred);
    }

    public void cargarLetrasCotiza(Credito cred) {
        letraslista = new ArrayList();
        LetrasDao letrasdao = new LetrasDaoImplements();
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public void insertarPago() {
        PagosDao linkDao = new PagosDaoImp();
        linkDao.insertarPago(pago);
        pago = new Pagos();
    }

//    public void insertarNotaDebito() {
//        LetrasDao letrasdao = new LetrasDaoImplements();
//        PagosDao pagosdao = new PagosDaoImp();
//        pago = new Pagos();
//        Date d = new Date();
//        if (letra.getMonto().compareTo(BigDecimal.ZERO) == 0) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Monto debe ser mayor a cero."));
//        }
//        CreditoDao creditodao = new CreditoDaoImp();
//        letra.setCredito(credito);
//        letra.setDescripcion("ND");
//        letra.setFecreg(d);
//        letra.setFecven(letra.getFecreg()/*fechafin*/);
//        letra.setMontoletra(BigDecimal.ZERO);
//        letra.setInteres(BigDecimal.ZERO);
//        letra.setSaldo(BigDecimal.ZERO/*letra.getMonto()*/);
//        letra.setMora(BigDecimal.ZERO);
//        //letra.setEstado("PN");
//        //credito.setTotaldeuda(credito.getTotaldeuda().add(letra.getMonto()));
//        //creditodao.modificarVenta(credito);
//        letrasdao.insertarLetra(letra);
//        pago.setLetras(letra);
//        pago.setFecreg(letra.getFecreg());
//        pago.setMonto(letra.getMonto());
//        pago.setDescripcion("MORA");
//        pago.setTipo("ND");
//        pago.setOperacion(descND);
//        pagosdao.insertarPago(pago);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Inserto Nota Débito correctamente."));
//    }
    public void cargarAnexoDNI() {
        filtradafecha = new ArrayList();
        letraslista = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Credito> creditosn = creditodao.mostrarVentas();
        try {
            anexos = anexodao.buscarClienteDni(dni, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosn.size(); j++) {
                    if (creditosn.get(j).getAnexo().equals(get)) {
                        Credito get1 = creditosn.get(j);
                        filtradafecha.add(get1);
                    }
                }
            }
        } catch (Exception e) {
        }
        dni = "";
    }

    public void cargarCreditoNombre() {
        filtradafecha = new ArrayList();
        letraslista = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Credito> creditosn = creditodao.cargarxEstado("DP");
        try {
            anexos = anexodao.buscarClienteNombre(nombre, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosn.size(); j++) {
                    if (creditosn.get(j).getAnexo().equals(get)) {
                        Credito get1 = creditosn.get(j);
                        credito = get1;
                        filtradafecha.add(credito);
                    }
                }
            }
            RequestContext.getCurrentInstance().update("formCliente");
            RequestContext.getCurrentInstance().execute("PF('dlgcargar').show()");
        } catch (Exception e) {
        }

        nombre = "";
    }

    public void pasarCliente(Credito cred) {
        filtradafecha = new ArrayList();
        filtradafecha.add(cred);
    }

    public void cargarxCodigoCredito() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEstadoDos(codigo, "EM", "AP");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void cargarPendientes() {
        creditos = new ArrayList();
        CreditoDao credao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = credao.cargarxCodigoEstado(codigo, "AP");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void cargarxCodigo() {
        creditos = new ArrayList();
        CreditoDao credao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = credao.cargarxCodigoCalif(codigo, "CN");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void cargarPendSinCodi() {
        CreditoDao credao = new CreditoDaoImp();
        try {
            creditos = credao.cargarxEstado("AP");
        } catch (Exception e) {
        }
    }

    public void cargarRefinanciados() {
        CreditoDao credao = new CreditoDaoImp();
        try {
            creditos = credao.cargarxRef(true);
        } catch (Exception e) {
        }
    }

    public void cargarxCodigoCotiza() {
        creditos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEstado(codigo, "NA");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void aprobarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        letrascredito = letrasdao.mostrarLetrasXCred(credito);
        Letras letra = new Letras();
        BigDecimal interes = new BigDecimal(0);
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
            RequestContext.getCurrentInstance().update("formAprobar");
            RequestContext.getCurrentInstance().execute("PF('dlgaprobar').show()");
        } else {
            if (credito.getAnexo().getIdanexo().equals(credito.getIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aval no puede ser el mismo cliente", ""));
                RequestContext.getCurrentInstance().update("formAprobar");
                RequestContext.getCurrentInstance().execute("PF('dlgaprobar').show()");
            } else {
                try {
                    for (int i = 0; i < credito.getNletras(); i++) {
                        letra = (Letras) letrascredito.get(i);
                        letra.setFecini(credito.getFechareg());
                        letra.setFecven(sumaDias(letra.getFecini(), 30));
                        letra.setEstado("PN");
                        letrasdao.modificarLetra(letra);
                        letra.setFecini(letra.getFecven());
                        interes = interes.add(letra.getMonto());
                    }
                    credito.setTotaldeuda(interes);
                    credito.setDeudactual(credito.getTotaldeuda());
                    credito.setEstado("AP");
                    creditodao.modificarVenta(credito);
                    credito = new Credito();
                    credito = new Credito();
                    creditos = new ArrayList();
                    RequestContext.getCurrentInstance().update("formAprobar");
                    RequestContext.getCurrentInstance().execute("PF('dlgaprobar').hide()");
                } catch (Exception e) {

                }
            }
        }
    }

    public void resultadoFecha() {
        fechafin = sumaDias(letra.getFecini(), 30);
    }

    public String nuevo() {
        selectedReqs = new String[9];
        credito = new Credito();
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        sinicial = BigDecimal.ZERO;
        iniciapre = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        fecha2 = null;
        value2 = true;
        sw = 0;
        value = false;
        valuei = false;
        valuei2 = true;
        valuesi = false;
        valuesi2 = true;
        btnaprobar = "Aprobar";
        btnguardar = "Guardar";
        avales = new ArrayList();
        ocupsxanexo = new ArrayList();
        return "/venta/form.xhtml";
    }

    public String cargar(Usuario usuario) {
        requisitosBean reqsbean = new requisitosBean();
        credavalBean credavalbean = new credavalBean();
        modeloTipo(credito.getVehi());
        avales = new ArrayList();
        List<Anexo> rec = new ArrayList();
        sw = 1;
        inicial Inicial = new inicial();
        rec = credavalbean.avalesxCredito(credito);
        if (!rec.isEmpty()) {
            avales = rec;
        }
        if (credito.getSinicial().compareTo(BigDecimal.ZERO) == 1) {
            sinicial = credito.getSinicial();
            valuesi = false;
        }
        inicia = credito.getInicial();
        precio = credito.getPrecio();
        saldo = precio.subtract(inicia);
        saldo = saldo.subtract(sinicial);
        anexo = credito.getAnexo();        
        ocupsxanexo = ocupbean.cargarxCredito(credito);
        iniciapre = Inicial.inicialCredito(anexo.getDistrito(), credito.getVehi(), credito.getPrecio(), credito.getModelo().getModelo());
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("JE")) {
            selectedReqs = reqsbean.mostrarSoloRequisitosxCred(credito);
            if (credito.getEstado().equals("EM")) {
                value = true;
                disableaval = false;
                btnaprobar = "Aprobar";
                return "/venta/formaprobar.xhtml";
            } else {                
                value = false;
                disableaval = true;
                btnaprobar = "Desaprobar";
            }
            return "/venta/formaprobar.xhtml";
        } else {
            valuei = false;
            valuei2 = true;
            mensaje = compararListas();
            btnguardar = "Modificar";
            return "/venta/formmodificar.xhtml";
        }
    }

    public String cargardespacho(Usuario usuario) {
        tipodocBean tipobean = new tipodocBean();
        ConceptosDao condao = new ConceptosDaoImp();
        try {
            modeloTipo(credito.getVehi());
            codigo = new String();
            sw = 1;
            inicia = credito.getInicial();
            precio = credito.getPrecio();
            saldo = precio.subtract(inicia);
            anexo = credito.getAnexo();
            disabledespacho = false;
            ocupsxanexo = ocupbean.cargarxCredito(credito);
            if (usuario.getPerfil().getAbrev().equals("AS") || usuario.getPerfil().getAbrev().equals("AD")) {
                if (!credito.getSwinicial()) {
                    System.err.println("Sinicial");
                    pago = new Pagos();
//                    pagosBean pagbean = new pagosBean();
                    listafilttipo = tipobean.listaTipoDoc("IN");
                    btnpago = "Pagar";
                    concepto = condao.veryIdCredito(credito);
                    montopago = concepto.getMontopago();
                    RequestContext.getCurrentInstance().update("formpagar");
                    RequestContext.getCurrentInstance().execute("PF('dlgpagarini').show()");
//                    pagbean.pagovarios(concepto);
                } else {
                    System.err.println("Despachar");
                    btnaprobar = "Despachar";
                    return "/despacho/formdespacho.xhtml";
                }
            } else {
                RequestContext.getCurrentInstance().update("formMostrar");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para despachar."));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void despachar(Usuario usuario) {
        CreditoDao credao = new CreditoDaoImp();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        try {
            disabledespacho = true;
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("N");
            vehiculodao.modificarVehiculo(vehiculo);
            credito.setLiqventa(codigo);
            credito.setEstado("DP");
            credito.setDespachado(usuario.getIdusuario());
            credao.modificarVenta(credito);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se despachó la unidad"));
        } catch (Exception e) {
        }
    }

    public String pagos() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        filtradafecha = new ArrayList();
        return "/venta/listarv.xhtml";
    }

    public String compararListas() {
        //ocupsxcredito = ocupbean.cargarxCredito(credito); 
        requisitosBean reqsbean = new requisitosBean();
        String msj = new String();
        if (avales.isEmpty() == false) {
            avalesant = avales;
        }
        selectedReqs = reqsbean.mostrarReqsParaModificar(credito);
        ocupsxcredito = ocupbean.cargarIngresos(credito.getAnexo());
        if (ocupsxcredito.size() > ocupsxanexo.size()) {
            bandera = "SUM";
            msj = "Agregar ingresos económicos al cliente";
            int count = 0;
            for (int i = 0; i < ocupsxcredito.size(); i++) {
                count = 0;
                Ocupacion get = ocupsxcredito.get(i);
                for (int j = 0; j < ocupsxanexo.size(); j++) {
                    Ocupacion getj = ocupsxanexo.get(j);
                    if (get.getDescripcion().concat(get.getEmpresa()).equals(getj.getDescripcion().concat(getj.getEmpresa()))) {
                        count++;
                    }
                }
                if (count == 0) {
                    //ocupbean.insertarCredito(credito.getIdventa(), get);
                    listamodificar.add(get);
                }
            }
        } else {
            if (ocupsxcredito.size() < ocupsxanexo.size()) {
                bandera = "RES";
                msj = "Quitar ingresos económicos al cliente";
                int count = 0;
                for (int i = 0; i < ocupsxanexo.size(); i++) {
                    count = 0;
                    Ocupacion get = ocupsxanexo.get(i);
                    for (int j = 0; j < ocupsxcredito.size(); j++) {
                        Ocupacion getj = ocupsxcredito.get(j);
                        if (get.getDescripcion().concat(get.getEmpresa()).equals(getj.getDescripcion().concat(getj.getEmpresa()))) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        //ocupbean.eliminar(get);
                        listamodificar.add(get);
                    }
                }
            }
        }
        return msj;
    }

    public void eliminarpagos() {
        pagosBean pagbean = new pagosBean();
        pagosxcredito = pagbean.eliminar(credito, pago);
    }

    public void cargarPagos(Pagos pagos) {
        pago = pagos;
        RequestContext.getCurrentInstance().update("formEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgeliminar').show()");
    }

    public String nuevoespecial() {
        credito = new Credito();
        sw = 0;
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        iniciapre = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        return "/venta/formespecial.xhtml";
    }

    public String index() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/venta/index.xhtml";
    }

    public String indexref() {
        creditos = new ArrayList();
        codigo = "";
        return "/refinanciar/index.xhtml";
    }

    public String nuevocotiza() {
        credito = new Credito();
        sw = 0;
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        iniciapre = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        return "/cotiza/form.xhtml";
    }

    public String indexcotiza() {
        letraslista = new ArrayList();
        creditos = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/cotiza/index.xhtml";
    }

    public String indexdespacho() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        filtradafecha = new ArrayList();
        return "/despacho/index.xhtml";
    }

    public String nuevodespacho() {
        creditos = new ArrayList();
        filtradafecha = new ArrayList();
        return "/despacho/formdespacho.xhtml";
    }

    public String cargarDeProf(Credito cred) {
        inicial Inicial = new inicial();
        OcupacionDao ocudao = new OcupacionDaoImpl();
        CreditoDao credao = new CreditoDaoImp();
        credito = cred;
        anexo = credito.getAnexo();
        ocupsxanexo = ocudao.ocupacionesxIdanexo(credito.getAnexo());
        if (ocupsxanexo.isEmpty()) {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se debe ingresar los ingresos del cliente primero."));
            return null;
        } else {
            if (anexo.getCpropia().equals("SI")) {
                disableaval = true;
            } else {
                disableaval = false;
            }
            Date fecha = new Date();
            fecha.getTime();
            btnguardar = "Solicitar";
            modeloTipo(credito.getVehi());
            sw = 0;
            value2 = true;
            value = false;
            valuei = false;
            valuei2 = true;
            precio = credito.getPrecio();
            inicia = credito.getInicial();
            saldo = credito.getSaldo();
            credito.setFechareg(fecha);
            credito.setLiqventa(null);
            iniciapre = Inicial.inicialCredito(anexo.getDistrito(), credito.getVehi(), credito.getPrecio(), credito.getModelo().getModelo());
            return "/cotiza/formcargarcotiza.xhtml";
        }
    }

    public void aprobarcredito(Usuario usuario) {
        CreditoDao credao = new CreditoDaoImp();
        VehiculoDao vehidao = new VehiculoDaoImplements();
        ConceptosDao condao = new ConceptosDaoImp();
        Date fecaprob = new Date();
        try {
            if (btnaprobar.equals("Aprobar")) {
                Letras letras = new Letras();
                BigDecimal nletras = new BigDecimal(credito.getNletras());
                BigDecimal interes = new BigDecimal(0);
                BigDecimal cien = new BigDecimal(100);
                BigDecimal saldonuevo = new BigDecimal(0);
                saldonuevo = credito.getSaldo();
                interes = (credito.getInteres().multiply(nletras)).divide(cien);
                Date fechaini = new Date();
                //credito.setFecaprob(fecaprob);
                //fechaini = credito.getFecaprob();
                fechaini = credito.getFechareg();
                //Date fechafin = new Date();
                fechas = fechasLetras(fechaini);
                //fechafin = sumaDias(fechaini, 30);                
                if (credito.getSinicial().compareTo(BigDecimal.ZERO) == 1) {
                    letras.setCredito(credito);
                    letras.setMontoletra(credito.getSinicial());
                    letras.setInteres(BigDecimal.ZERO);
                    letras.setMonto(credito.getSinicial());
                    letras.setFecini(credito.getFechareg());
                    letras.setFecven(credito.getFecsinicial());
                    letras.setMora(BigDecimal.ZERO);
                    letras.setSaldo(credito.getSinicial());
                    letras.setEstado("PN");
                    letras.setCobradonc(false);
                    letras.setDescripcion("2DA INICIAL");
                    letrasdao.insertarLetra(letras);
                    saldonuevo = credito.getSaldo().subtract(credito.getSinicial());
                    credito.setTotaldeuda(credito.getTotaldeuda().add(credito.getSinicial()));
                    credito.setDeudactual(credito.getTotaldeuda());
                }
                BigDecimal montoletras = saldonuevo.divide(nletras, 2).setScale(1, RoundingMode.UP);
                BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
                letras.setFecreg(credito.getFechareg());
                for (int i = 1; i <= credito.getNletras(); i++) {
                    Date fechaf = fechas.get(i - 1);
                    letras.setCredito(credito);
                    letras.setMontoletra(montoletras);
                    letras.setInteres(mtointeres);
                    letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                    letras.setFecini(fechaini);
                    letras.setFecven(fechaf);
                    letras.setMora(BigDecimal.ZERO);
                    fechaini = fechaf;
                    //fechafin = sumaDias(fechaini, 30);
                    letras.setSaldo(letras.getMonto());
                    letras.setEstado("PN");
                    letras.setCobradonc(false);
                    letras.setDescripcion("L" + i + "/L" + credito.getNletras());
                    credito.setTotaldeuda(credito.getTotaldeuda().add(letras.getSaldo()));
                    credito.setDeudactual(credito.getTotaldeuda());
                    credito.setAprobado(usuario.getAnexo().getIdanexo());
                    letrasdao.insertarLetra(letras);
                }
                concepto.setMontopago(credito.getInicial());
                concepto.setTipo("IN");
                concepto.setTotal(credito.getInicial());
                concepto.setFecreg(credito.getFechareg());
                concepto.setCredito(credito);
                condao.insertarConcepto(concepto);
                vehiculo = credito.getVehiculo();
                vehiculo.setEstado("N");
                vehidao.modificarVehiculo(vehiculo);
                credito.setSwinicial(false);
                credito.setEstado("AP");
                value = false;
                btnaprobar = "Desaprobar";
                credito.setModificado(usuario.getAnexo().getIdanexo());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se aprobó el crédito."));
            } else {
                concepto = condao.veryIdCredito(credito);
                condao.eliminarConcepto(concepto);
                vehiculo = credito.getVehiculo();
                vehiculo.setEstado("D");
                vehidao.modificarVehiculo(vehiculo);
                List<Letras> letrita = new ArrayList();
                letrita = letrasdao.mostrarLetrasXCred(credito);
                for (int i = 0; i < letrita.size(); i++) {
                    Letras get = letrita.get(i);
                    letrasdao.eliminarLetra(get);
                }
                credito.setTotaldeuda(BigDecimal.ZERO);
                credito.setDeudactual(BigDecimal.ZERO);
                credito.setEstado("EM");
                credito.setVerificado(null);
                credito.setVehiculo(null);
                credito.setObsver(null);
                btnaprobar = "Aprobar";
                credito.setModificado(usuario.getAnexo().getIdanexo());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se desaprobó el crédito."));
                concepto = new Conceptos();
            }
        } catch (Exception e) {
        }
        credao.modificarVenta(credito);
        letrascredito = letrasdao.mostrarLetrasXCred(credito);
    }

    public void exportarFormato(String codigo, String tipo, String estado) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("liqventa", codigo);
        File jasper = new File("D:/reporte/solicitud.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=SOLICITUD-" + codigo + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void rechazarcredito(Usuario usuario) {
        CreditoDao credao = new CreditoDaoImp();
        try {
            if (credito.getEstado().equals("AP")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe desaprobar el crédito primero"));
            } else {
                credito.setEstado("RC");
                credao.modificarVenta(credito);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se rechazó el crédito"));
            }
        } catch (Exception e) {
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

    public Letras cargarLetra(Letras letras) {
        letra = letras;
        CreditoDao linkdao = new CreditoDaoImp();
        credito = linkdao.cargarCreditoxLetra(letra);
        return letra;
    }

    public void addMessage() {
        String summary = value ? "SI" : "NO";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
        if (value) {
            value2 = false;
        } else {
            value2 = true;
        }
    }

    public void addMessageini() {
        // String summary = valuei ? "SI" : "NO";
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
        if (valuei) {
            valuei2 = false;
        } else {
            valuei2 = true;
        }
        System.out.println("values: " + valuei2);
    }

    public void addMessageini2() {
        // String summary = valuei ? "SI" : "NO";
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
        if (valuesi) {
            valuesi2 = false;
        } else {
            valuesi2 = true;
            fecha2 = null;
        }
    }

    public void aprobacion(Usuario usuario) {
        AnexoDao andao = new AnexoDaoImplements();
        Anexo anexo = usuario.getAnexo();
        if (anexo.getTipoanexo().equals("AD") || anexo.getTipoanexo().equals("JE")) {
            aprobar = false;
        }
        aprobar = true;
    }

    public boolean disableGuardar() {
        if (sw == 1) {
            return true;
        }
        return false;
    }

    public void cargarIngreso(Anexo anexo) {
        ocupsxanexo = ocupbean.cargarIngresos(anexo);
        if (anexo.getCpropia().equals("SI")) {
            value2 = true;
        } else {
            value2 = false;
        }

    }

    public void cargarObjOcup(Ocupacion ocup) {
        objocup = ocup;
        listaocups = new ArrayList();
        if (objocup.getBoletas() != null && objocup.getBoletas() == true) {
            listaocups.add("Copia de boletas de pago");
        }
        if (objocup.getConstancia() != null && objocup.getConstancia() == true) {
            listaocups.add("Copia de Autoavalúo");
        }
        if (objocup.getFacbol() != null && objocup.getFacbol() == true) {
            listaocups.add("Copia de boletas y/o facturas de compras");
        }
        if (objocup.getFunc() != null && objocup.getFunc() == true) {
            listaocups.add("Copia de Licencia de funcionamiento");
        }
        if (objocup.getLicencia() != null && objocup.getLicencia() == true) {
            listaocups.add("Copia de Licencia de conducir");
        }
        if (objocup.getPagosunat() != null && objocup.getPagosunat() == true) {
            listaocups.add("Copia de pagos a sunat");
        }
        if (objocup.getRrhh() != null && objocup.getRrhh() == true) {
            listaocups.add("Copia de Recibo por honorarios");
        }
        if (objocup.getTpropiedad() != null && objocup.getTpropiedad() == true) {
            listaocups.add("Copia de tarjeta de propiedad");
        }
        RequestContext.getCurrentInstance().update("formOcup");
        RequestContext.getCurrentInstance().execute("PF('dlgverocup').show()");

    }

//    public void insertarOcupacion(Anexo anexo, Ocupacion ocup) {
//        if (anexo == null) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar un cliente."));
//        } else {
//            try {
//                ocupsxanexo = ocupbean.insertar(anexo, ocup);
//                RequestContext.getCurrentInstance().update("formOcupacion");
//                RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
//            } catch (Exception e) {
//            }
//        }
//
//    }
//
//    public void eliminarIngreso(Anexo anexo) {
//        System.out.println("ocupa: " + objocup.getDescripcion());
//        ocupsxanexo = ocupbean.eliminar(anexo, objocup);
//    }
//    public void listaTipoDoc() {
//        tipodocBean tipobean = new tipodocBean();
//        listafilttipo = tipobean.listaTipoDoc("IN");
////        if (tipo.equals("NC")) {
////            montopago = letra.getInteres();
////            btnpago = "Aplicar";
////        } else {
////            montopago = letra.getSaldo();
////            btnpago = "Pagar";
////        }
//    }
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

    public void insertarini(int idusuario) {
        Caja caja = new Caja();
        Movcaja mcaja = new Movcaja();
        PagosDao pagosdao = new PagosDaoImp();
        CreditoDao creditodao = new CreditoDaoImp();
        CajaDao cajadao = new CajaDaoImp();
        ConceptosDao condao = new ConceptosDaoImp();
        MovcajaDao movcajadao = new MovcajaDaoImp();
        try {
            if (montopago.compareTo(concepto.getMontopago()) != 1) {
                pago.setConceptos(concepto);
                pago.setMonto(montopago);
                pago.setTipo("IN");
                pago.setUsuario(idusuario);
                pagosdao.insertarPago(pago);
                caja = pago.getCaja();
                caja.setTotal(caja.getTotal().add(montopago));
                cajadao.modificarCaja(caja);
                mcaja.setCaja(caja);
                mcaja.setTipomov("IN");
                mcaja.setFechamov(pago.getFecreg());
                mcaja.setMonto(montopago);
                mcaja.setConcepto(concepto);
                movcajadao.insertarMovcaja(mcaja);
                concepto.setMontopago(concepto.getMontopago().subtract(montopago));
                condao.modificarConcepto(concepto);
                if (concepto.getMontopago().compareTo(BigDecimal.ZERO) == 0) {
                    credito.setSwinicial(true);
                    creditodao.modificarVenta(credito);
                }
                RequestContext.getCurrentInstance().update("formpagar");
                RequestContext.getCurrentInstance().execute("PF('dlgpagarini').hide()");
                RequestContext.getCurrentInstance().update("formpagar");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se registró la inicial."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Revise el monto", "Monto excede el saldo"));
                RequestContext.getCurrentInstance().update("formpagar");
                RequestContext.getCurrentInstance().execute("PF('dlgpagarini').show()");
            }
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formpagar");
            RequestContext.getCurrentInstance().execute("PF('dlgpagarini').show()");
        }

    }

    public void modeloTipo(String tipo) {
        listafiltrada = modbean.modeloTipo(tipo);
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Letras getLetrasa() {
        return letrasa;
    }

    public void setLetrasa(Letras letrasa) {
        this.letrasa = letrasa;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Credito> getVentas() {
        CreditoDao linkdao = new CreditoDaoImp();
        creditos = linkdao.mostrarVentas();
        return creditos;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public List<Letras> getLetrascredito() {
        LetrasDao linkdao = new LetrasDaoImplements();
        letrascredito = linkdao.mostrarLetrasXCred(credito);
        return letrascredito;
    }

    public List<Credito> getFiltradafecha() {
        return filtradafecha;
    }

    public void setVentas(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public LetrasDao getLetras() {
        return letrasdao;
    }

    public void setLetras(LetrasDao letrasdao) {
        this.letrasdao = letrasdao;
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

    public List<Letras> getLetraslista() {
        return letraslista;
    }

    public void setLetraslista(List<Letras> letraslista) {
        this.letraslista = letraslista;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getRes() {
        return res;
    }

    public void setRes(BigDecimal res) {
        this.res = res;
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

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getInicia() {
        return inicia;
    }

    public void setInicia(BigDecimal inicia) {
        this.inicia = inicia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Credito getCrediton() {
        return crediton;
    }

    public void setCrediton(Credito crediton) {
        this.crediton = crediton;
    }

    public BigDecimal getIniciapre() {
        return iniciapre;
    }

    public void setIniciapre(BigDecimal iniciapre) {
        this.iniciapre = iniciapre;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public String getVehi() {
        return vehi;
    }

    public void setVehi(String vehi) {
        this.vehi = vehi;
    }

    public String getDescND() {
        return descND;
    }

    public void setDescND(String descND) {
        this.descND = descND;
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

    public boolean isValuei() {
        return valuei;
    }

    public void setValuei(boolean valuei) {
        this.valuei = valuei;
    }

    public boolean isValuei2() {
        return valuei2;
    }

    public void setValuei2(boolean valuei2) {
        this.valuei2 = valuei2;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public String getBtnaprobar() {
        return btnaprobar;
    }

    public void setBtnaprobar(String btnaprobar) {
        this.btnaprobar = btnaprobar;
    }

    public boolean isAprobar() {
        return aprobar;
    }

    public void setAprobar(boolean aprobar) {
        this.aprobar = aprobar;
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

    public Ocupacion getObjocup() {
        return objocup;
    }

    public void setObjocup(Ocupacion objocup) {
        this.objocup = objocup;
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

    public void Pagosxcredito(Credito cred) {
        pagosBean pagbean = new pagosBean();
        List<Pagos> lista = new ArrayList();
        System.out.println("credito " + cred.getLiqventa());
        lista = pagbean.PagosxCredito(cred);
        if (lista.isEmpty() == false) {
            pagosxcredito = lista;
            RequestContext.getCurrentInstance().update("formhistorial");
            RequestContext.getCurrentInstance().execute("PF('dlghistorial').show()");
        } else {
            RequestContext.getCurrentInstance().update("formModificar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "No existen pagos de este cliente."));
        }

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

    public boolean isDisableaval() {
        return disableaval;
    }

    public void setDisableaval(boolean disableaval) {
        this.disableaval = disableaval;
    }

    public List<String> getListaocups() {
        return listaocups;
    }

    public void setListaocups(List<String> listaocups) {
        this.listaocups = listaocups;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Ocupacion> getListamodificar() {
        return listamodificar;
    }

    public void setListamodificar(List<Ocupacion> listamodificar) {
        this.listamodificar = listamodificar;
    }

    public boolean isDisabledespacho() {
        return disabledespacho;
    }

    public void setDisabledespacho(boolean disabledespacho) {
        this.disabledespacho = disabledespacho;
    }

    public Conceptos getConcepto() {
        return concepto;
    }

    public void setConcepto(Conceptos concepto) {
        this.concepto = concepto;
    }

    public List<Conceptos> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<Conceptos> conceptos) {
        this.conceptos = conceptos;
    }

    public BigDecimal getMontopago() {
        return montopago;
    }

    public void setMontopago(BigDecimal montopago) {
        this.montopago = montopago;
    }

    public String getBtnpago() {
        return btnpago;
    }

    public void setBtnpago(String btnpago) {
        this.btnpago = btnpago;
    }

    public boolean isDisablecaja() {
        return disablecaja;
    }

    public void setDisablecaja(boolean disablecaja) {
        this.disablecaja = disablecaja;
    }

    public List<Tipodoc> getListafilttipo() {
        return listafilttipo;
    }

    public void setListafilttipo(List<Tipodoc> listafilttipo) {
        this.listafilttipo = listafilttipo;
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

    public List<Caja> getTodasCajas() {
        cajaBean cajabean = new cajaBean();
        todasCajas = cajabean.getVerTodas();
        return todasCajas;
    }

    public void setTodasCajas(List<Caja> todasCajas) {
        this.todasCajas = todasCajas;
    }

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    public BigDecimal getSinicial() {
        return sinicial;
    }

    public void setSinicial(BigDecimal sinicial) {
        this.sinicial = sinicial;
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

    public List<Anexo> getAvales() {
        return avales;
    }

    public void setAvales(List<Anexo> avales) {
        this.avales = avales;
    }

    public List<Anexo> getAvalesant() {
        return avalesant;
    }

    public void setAvalesant(List<Anexo> avalesant) {
        this.avalesant = avalesant;
    }

    public List<Anexo> getAvalesmod() {
        return avalesmod;
    }

    public void setAvalesmod(List<Anexo> avalesmod) {
        this.avalesmod = avalesmod;
    }

    public String getBanderaval() {
        return banderaval;
    }

    public void setBanderaval(String banderaval) {
        this.banderaval = banderaval;
    }

    public String[] getSelectedReqs() {
        return selectedReqs;
    }

    public void setSelectedReqs(String[] selectedReqs) {
        this.selectedReqs = selectedReqs;
    }

    public String getBanderareq() {
        return banderareq;
    }

    public void setBanderareq(String banderareq) {
        this.banderareq = banderareq;
    }

    public List<String> getReqs() {
        return reqs;
    }

    public void setReqs(List<String> reqs) {
        this.reqs = reqs;
    }
}
