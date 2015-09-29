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
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class creditoBean implements Serializable {

    public Credito credito = new Credito();
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
    private String codigo;
    private Date fechafin;

    public creditoBean() {
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

    public void resultadoSaldo() {
        res = credito.getPrecio().subtract(credito.getInicial());
    }

    public void insertarCredito() {
        CreditoDao creditodao = new CreditoDaoImp();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        credito.setSaldo(credito.getPrecio().subtract(credito.getInicial()));
        if (credito.getSaldo().compareTo(BigDecimal.ZERO)==1) {
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("N");
            vehiculodao.modificarVehiculo(vehiculo);
            credito.setTotaldeuda(BigDecimal.ZERO);
            Letras letras = new Letras();
            BigDecimal nletras = new BigDecimal(credito.getNletras());
            BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
            BigDecimal interes = new BigDecimal(0);
            credito.setEstado("AP");
            creditodao.insertarVenta(credito);
            for (int i = 1; i <= (credito.getNletras()); i++) {
                interes = interes.add(credito.getInteres());
            }
            Date fechaini = new Date();
            fechaini = credito.getFechareg();
            Date fechafin = new Date();
            fechafin = sumaDias(fechaini, 30);
            BigDecimal cien = new BigDecimal(100);
            BigDecimal mtointeres = (montoletras.multiply(interes)).divide(cien);
            letras.setFecreg(credito.getFechareg());
            for (int i = 1; i <= (credito.getNletras()); i++) {
                letras.setCredito(credito);
                letras.setMontoletra(montoletras);
                letras.setInteres(mtointeres);
                letras.setMonto(montoletras.add(mtointeres).setScale(2));
                letras.setFecini(fechaini);
                letras.setFecven(fechafin);
                fechaini = fechafin;
                fechafin = sumaDias(fechaini, 30);
                letras.setSaldo(montoletras.add(mtointeres));
                letras.setEstado("PN");
                letras.setDescripcion("L" + i);
                credito.setTotaldeuda(credito.getTotaldeuda().add(letras.getSaldo()));
                letrasdao.insertarLetra(letras);
            }
            creditodao.modificarVenta(credito);
        }
//        credito = new Credito();
    }

    public void insertarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        credito.setSaldo(credito.getPrecio().subtract(credito.getInicial()));
        System.out.println("No entro aun :"+credito.getPrecio()+" "+credito.getSaldo());
        if (credito.getSaldo().compareTo(BigDecimal.ZERO)==1) {
            System.out.println("Ya entré :"+credito.getPrecio()+" "+credito.getSaldo());
            Letras letras = new Letras();
            BigDecimal nletras = new BigDecimal(credito.getNletras());
            BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
            BigDecimal interes = new BigDecimal(0);
            credito.setEstado("NA");
            creditodao.insertarVenta(credito);
            for (int i = 1; i <= (credito.getNletras()); i++) {
                interes = interes.add(credito.getInteres());
            }
            Date fechaini = new Date();
            fechaini = credito.getFechareg();
            Date fechafin = new Date();
            fechafin = sumaDias(fechaini, 30);
            BigDecimal cien = new BigDecimal(100);
            BigDecimal mtointeres = (montoletras.multiply(interes)).divide(cien,2);
            letras.setFecreg(credito.getFechareg());
            for (int i = 1; i <= (credito.getNletras()); i++) {
                letras.setCredito(credito);
                letras.setMontoletra(montoletras);
                letras.setInteres(mtointeres);
                letras.setMonto(montoletras.add(mtointeres).setScale(2));
                letras.setFecini(fechaini);
                letras.setFecven(fechafin);
                fechaini = fechafin;
                fechafin = sumaDias(fechaini, 30);
                letras.setSaldo(montoletras.add(mtointeres));
                letras.setEstado("NA");
                letras.setDescripcion("L" + i);
                letrasdao.insertarLetra(letras);
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
        List<Letras> letrita = new ArrayList();
        Vehiculo vehiculo = new Vehiculo();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        letrita = letrasdao.mostrarLetrasXCred(credito);
        BigDecimal totaldeuda = new BigDecimal(BigInteger.ZERO);
        for (int i = 0; i < letrita.size(); i++) {
            Letras get = letrita.get(i);
            totaldeuda = totaldeuda.add(get.getMonto());
        }
        if (totaldeuda.compareTo(credito.getTotaldeuda()) == 0) {
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

    public void filtrarFechas() {
        CreditoDao linkdao = new CreditoDaoImp();
        filtradafecha = linkdao.filtrarFechas(fecha1, fecha2);
    }

    public void cargarCredito(Anexo anexo) {
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
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
            letrasdao.modificarLetra(get);
        }
        letraslista = letras.mostrarLetrasXCred(credito);
//      return credito;
    }

    public void cargarLetras(Credito cred) {
        letraslista = new ArrayList();
//        credito = cred;
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
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
                    }
                }
            }
            letrasdao.modificarLetra(get);
        }
        letraslista = letrasdao.mostrarLetrasXCred(cred);
//      return credito;
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
        Date d = new Date();
        CreditoDao creditodao = new CreditoDaoImp();
        letra.setCredito(credito);
        letra.setDescripcion("ND");
        letra.setFecreg(d);
        letra.setFecven(fechafin);
        letra.setMontoletra(BigDecimal.ZERO);
        letra.setInteres(BigDecimal.ZERO);
        letra.setSaldo(letra.getMonto());
        letra.setEstado("PN");
        credito.setTotaldeuda(credito.getTotaldeuda().add(letra.getMonto()));
        creditodao.modificarVenta(credito);
        letrasdao.insertarLetra(letra);
    }

    public void cargarAnexoDNI() {
        filtradafecha = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        Anexo anexo = new Anexo();
        CreditoDao creditodao = new CreditoDaoImp();
        try {
            anexo = anexodao.cargarClientexDoc(dni, "CN", "CJ");
            credito = creditodao.cargarCreditoxAnexo(anexo);
            filtradafecha.add(credito);
        } catch (Exception e) {
        }
    }

    public void cargarxCodigo() {
        creditos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigo(codigo);
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }

    }

    public void resultadoFecha() {
        fechafin = sumaDias(letra.getFecini(), 30);
    }

    public String nuevo() {
        credito = new Credito();
        return "/venta/form.xhtml";
    }

    public String index() {
        creditos = new ArrayList();
        codigo = "";
        return "/venta/index.xhtml";
    }

    public String nuevocotiza() {
        credito = new Credito();
        return "/cotiza/form.xhtml";
    }
    
    public String indexcotiza() {
        creditos = new ArrayList();
        codigo = "";
        return "/cotiza/index.xhtml";
    }
}
