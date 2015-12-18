package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Anexo;
import Model.Credito;
import Model.Letras;
import Model.Pagos;
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
    private List<Letras> letraslista = new ArrayList();
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

    public creditoBean() {
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

    public void resultadoSaldo() {
        res = precio.subtract(credito.getInicial());
    }

    public void resProformar() {
        saldo = precio.subtract(inicia);
    }

    public void precioModeloCredito() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));
        String distrito = credito.getAnexoByIdanexo().getDistrito();
        String tipov = credito.getVehiculo().getTipovehiculo();
        iniciapre = Inicial.inicialCredito(distrito, tipov, precio, credito.getVehiculo().getModelo().getModelo());
        saldo = precio.subtract(inicia);
    }

    public void precioModeloCreditoEspecial() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));

    }

    public void precioModeloCotiza() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getModelo().getModelo()));
        String distrito = credito.getAnexoByIdanexo().getDistrito();
        String tipov = credito.getVehi();
        iniciapre = Inicial.inicialCredito(distrito, tipov, precio, credito.getModelo().getModelo());
        saldo = precio.subtract(inicia);
    }

    public void insertarCredito() {
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
            if (credito.getAnexoByIdanexo().equals(credito.getAnexoByIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El aval no puede ser el mismo cliente."));
            } else {
                credito.setPrecio(precio);
                credito.setSaldo(precio.subtract(inicia));
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                    Letras letras = new Letras();
                    vehiculo = credito.getVehiculo();
                    vehiculo.setEstado("N");
                    credito.setTotaldeuda(BigDecimal.ZERO);
                    BigDecimal nletras = new BigDecimal(credito.getNletras());
                    BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
                    BigDecimal interes = new BigDecimal(0);
                    credito.setEstado("AP");
                    BigDecimal cien = new BigDecimal(100);
                    if(value2){
                        credito.setAnexoByIdaval(credito.getAnexoByIdanexo());
                    }
                    creditodao.insertarVenta(credito);                    
                    interes = (credito.getInteres().multiply(nletras)).divide(cien);
                    Date fechaini = new Date();
                    fechaini = credito.getFechareg();
                    Date fechafin = new Date();
                    fechafin = sumaDias(fechaini, 30);
                    BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
                    letras.setFecreg(credito.getFechareg());
                    for (int i = 1; i <= (credito.getNletras()); i++) {
                        letras.setCredito(credito);
                        letras.setMontoletra(montoletras);
                        letras.setInteres(mtointeres);
                        letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                        letras.setFecini(fechaini);
                        letras.setFecven(fechafin);
                        letras.setMora(BigDecimal.ZERO);
                        fechaini = fechafin;
                        fechafin = sumaDias(fechaini, 30);
                        letras.setSaldo(letras.getMonto());
                        letras.setEstado("PN");
                        letras.setDescripcion("L" + i + "/L" + credito.getNletras());
                        credito.setTotaldeuda(credito.getTotaldeuda().add(letras.getSaldo()));
                        credito.setDeudactual(credito.getTotaldeuda());
                        letrasdao.insertarLetra(letras);
                    }
                    creditodao.modificarVenta(credito);
                    vehiculodao.modificarVehiculo(vehiculo);
                    sw = 1;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El crédito se registró correctamente."));
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
            if (credito.getAnexoByIdanexo().equals(credito.getAnexoByIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El aval no puede ser el mismo cliente."));
            } else {
                credito.setPrecio(precio);
                credito.setSaldo(saldo);
                credito.setTotaldeuda(credito.getSaldo());
                credito.setDeudactual(credito.getSaldo());
                credito.setInteres(BigDecimal.ZERO);
                if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1 && (sw == 0)) {
                    vehiculo = credito.getVehiculo();
                    vehiculo.setEstado("N");
                    vehiculodao.modificarVehiculo(vehiculo);
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

    public void refinanciar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        List<Letras> antiguas = new ArrayList();
        Letras nuevas = new Letras();
        crediton.setFechareg(credito.getFechareg());
        crediton.setAnexoByIdaval(credito.getAnexoByIdaval());
        crediton.setAnexoByIdanexo(credito.getAnexoByIdanexo());
        crediton.setAnexoByCodven(credito.getAnexoByCodven());
        crediton.setAnexoByVerificado(credito.getAnexoByVerificado());
        crediton.setCondicionpago(credito.getCondicionpago());
        crediton.setContrato(credito.getContrato());
        crediton.setCronograma(credito.getCronograma());
        crediton.setEmpresa(credito.getEmpresa());
        crediton.setTienda(credito.getTienda());
        crediton.setInicial(BigDecimal.ZERO);
        crediton.setPrecio(BigDecimal.ZERO);
        crediton.setVehiculo(credito.getVehiculo());
        crediton.setSaldo(credito.getDeudactual());
        crediton.setTotaldeuda(BigDecimal.ZERO);
        if (credito.getLiqventa().endsWith("A")) {
            crediton.setLiqventa(credito.getLiqventa() + "A");
        } else {
            crediton.setLiqventa(credito.getLiqventa() + "-A");
        }
        antiguas = letrasdao.mostrarLetrasXCred(credito);
        for (int i = 0; i < antiguas.size(); i++) {
            Letras get = antiguas.get(i);
            get.setSaldo(BigDecimal.ZERO);
            get.setEstado("RF");
            get.setDescripcion("Ref. :" + crediton.getLiqventa());
            letrasdao.modificarLetra(get);
        }
        credito.setDeudactual(BigDecimal.ZERO);
        creditodao.modificarVenta(credito);
        BigDecimal nletras = new BigDecimal(crediton.getNletras());
        BigDecimal montoletras = crediton.getSaldo().divide(nletras, 2);
        BigDecimal interes = new BigDecimal(0);
        crediton.setEstado("AP");
        creditodao.insertarVenta(crediton);
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
        creditodao.modificarVenta(crediton);
    }

    public void insertarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        credito.setPrecio(precio);
        if (creditodao.veryLiqventa(this.credito.getLiqventa()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El código de venta ya existe."));
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
                creditodao.insertarVenta(credito);
                interes = (credito.getInteres().multiply(nletras)).divide(cien);
                Date fechaini = new Date();
                fechaini = credito.getFechareg();
                Date fechafin = new Date();
                fechafin = sumaDias(fechaini, 30);
                BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
                letras.setFecreg(credito.getFechareg());
                for (int i = 1; i <= (credito.getNletras()); i++) {
                    letras.setCredito(credito);
                    letras.setMontoletra(montoletras);
                    letras.setInteres(mtointeres);
                    letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                    letras.setFecini(fechaini);
                    letras.setFecven(fechafin);
                    fechaini = fechafin;
                    fechafin = sumaDias(fechaini, 30);
                    letras.setSaldo(letras.getMonto());
                    letras.setEstado("NA");
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

    public void modificar() {
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.modificarVenta(credito);
        credito = new Credito();
    }

    public void eliminar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
//        List<Letras> letrita = new ArrayList();
        Vehiculo vehiculo = new Vehiculo();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
//        letrita = letrasdao.mostrarLetrasXCred(credito);
//        BigDecimal totaldeuda = new BigDecimal(BigInteger.ZERO);
//        for (int i = 0; i < letrita.size(); i++) {
//            Letras get = letrita.get(i);
//            totaldeuda = totaldeuda.add(get.getMonto());
//        }
        if (credito.getDeudactual().compareTo(credito.getTotaldeuda()) == 0) {
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("D");
            vehiculodao.modificarVehiculo(vehiculo);
            creditodao.eliminarVenta(credito);
        } else {
            System.out.println("Mostrar Error de que el credito ya ha sido cobrado y no se puede borrar");
        }
        credito = new Credito();
        creditos = new ArrayList();
    }

    public void eliminarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        creditodao.eliminarVenta(credito);
        credito = new Credito();
        creditos = new ArrayList();
    }

    public Date sumaDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
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
//        credito = cred;
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal cien = new BigDecimal(100);
        cinco = cinco.divide(cien);
//        credito = creditodao.cargarCreditoxAnexo(anexo);
        LetrasDao letrasdao = new LetrasDaoImplements();
        letritas = letrasdao.mostrarLetrasXCred(cred);
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
                        get.setDiffdays(Diffdays(get.getFecven()));
                    }
                }
            }
            moraant = get.getMora();
            moraact = (get.getSaldo().multiply(cinco)).setScale(1, RoundingMode.UP);
            if (get.getEstado().equals("VN")) {
                if (moraact.compareTo(moraant) == -1) {
                    get.setMora(moraant);
                } else {
                    get.setMora(moraact);
                }
            }
            letrasdao.modificarLetra(get);

        }
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public void cargarLetrasCotiza(Credito cred) {
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public void insertarPago() {
        PagosDao linkDao = new PagosDaoImp();
        linkDao.insertarPago(pago);
        pago = new Pagos();
    }

    public void insertarNotaDebito() {
        LetrasDao letrasdao = new LetrasDaoImplements();
        PagosDao pagosdao = new PagosDaoImp();
        pago = new Pagos();
        Date d = new Date();
        if (letra.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Monto debe ser mayor a cero."));
        }
        CreditoDao creditodao = new CreditoDaoImp();
        letra.setCredito(credito);
        letra.setDescripcion("ND");
        letra.setFecreg(d);
        letra.setFecven(letra.getFecreg()/*fechafin*/);
        letra.setMontoletra(BigDecimal.ZERO);
        letra.setInteres(BigDecimal.ZERO);
        letra.setSaldo(BigDecimal.ZERO/*letra.getMonto()*/);
        letra.setMora(BigDecimal.ZERO);
        //letra.setEstado("PN");
        //credito.setTotaldeuda(credito.getTotaldeuda().add(letra.getMonto()));
        //creditodao.modificarVenta(credito);
        letrasdao.insertarLetra(letra);
        pago.setLetras(letra);
        pago.setFecreg(letra.getFecreg());
        pago.setMonto(letra.getMonto());
        pago.setDescripcion("MORA");
        pago.setTipo("ND");
        pago.setOperacion(descND);
        pagosdao.insertarPago(pago);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Inserto Nota Débito correctamente."));
    }

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
                    if (creditosn.get(j).getAnexoByIdanexo().equals(get)) {
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
        List<Credito> creditosn = creditodao.mostrarVentas();
        try {
            anexos = anexodao.buscarClienteNombre(nombre, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosn.size(); j++) {
                    if (creditosn.get(j).getAnexoByIdanexo().equals(get)) {
                        Credito get1 = creditosn.get(j);
                        filtradafecha.add(get1);
                    }
                }
            }
        } catch (Exception e) {
        }
        nombre = "";
    }

    public void cargarxCodigoCredito() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEstado(codigo, "AP");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
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
        } else {
            if (credito.getAnexoByIdanexo().equals(credito.getAnexoByIdaval())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El aval no puede ser el mismo cliente."));
            } else {
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
            }
        }
    }

    public void resultadoFecha() {
        fechafin = sumaDias(letra.getFecini(), 30);
    }

    public String nuevo() {
        credito = new Credito();
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        iniciapre = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        value2 = true;
        return "/venta/form.xhtml";

    }

    public String pagos() {
        creditos = new ArrayList();
        letraslista = new ArrayList();
        return "/venta/listarv.xhtml";
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
        creditos = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/cotiza/index.xhtml";
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
        if (value){
            value2 = false;
        }else{
            value2 = true;
        }
    }
}
