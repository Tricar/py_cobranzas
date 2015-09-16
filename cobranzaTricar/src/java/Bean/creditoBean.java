package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Model.Anexo;
import Model.Credito;
import Model.Letras;
import Model.Pagos;
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
    public List<Credito> creditos;
    LetrasDao letrasdao = new LetrasDaoImplements();
    public List<Letras> letrasventa;
    public List<Credito> filtradafecha;
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();
    private List<Letras> letraslista = new ArrayList();
    private Pagos pago = new Pagos();
    private Letras letra = new Letras();
    private String dni;

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

    public List<Letras> getLetrasventa() {
        LetrasDao linkdao = new LetrasDaoImplements();
        letrasventa = linkdao.mostrarLetrasXCred(credito);
        return letrasventa;
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

    public void insertar() {
        CreditoDao creditodao = new CreditoDaoImp();
        credito.setSaldo(credito.getPrecio().subtract(credito.getInicial()));
        credito.setTotaldeuda(BigDecimal.ZERO);
        Letras letras = new Letras();
        BigDecimal nletras = new BigDecimal(credito.getNletras());
        BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
        BigDecimal interes = new BigDecimal(0);
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
//        credito = new Credito();
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
        letrita = letrasdao.mostrarLetrasXCred(credito);
        BigDecimal totaldeuda = new BigDecimal(BigInteger.ZERO);
        for (int i = 0; i < letrita.size(); i++) {
            Letras get = letrita.get(i);
            totaldeuda = totaldeuda.add(get.getMonto());
        }
        if (totaldeuda.compareTo(credito.getTotaldeuda()) == 0) {
            creditodao.eliminarVenta(credito);
        } else {
            System.out.println("Mostrar Error de que el credito ya ha sido cobrado y no se puede borrar");
        }
        credito = new Credito();
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

    public void resultadoSaldo() {
        BigDecimal r = new BigDecimal(0);
        r = credito.getPrecio().subtract(credito.getInicial());
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
            System.out.println(" Entre al for :" + letritas.get(i));
            Letras get = letritas.get(i);
            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                get.setEstado("CN");
                System.out.println(" Entre al IF :" + get.getEstado());
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

    public void insertarPago() {
        PagosDao linkDao = new PagosDaoImp();
        System.out.println("Este es el Id de letra :" + letra.getIdletras());
//        pago.setLetras(letra);
        linkDao.insertarPago(pago);
        pago = new Pagos();
    }

    public void insertarNotaDebito() {
        LetrasDao letrasdao = new LetrasDaoImplements();
        Date d = new Date();
//        credito = creditodao.cargarCreditoxLetra(letra);
        letra.setCredito(credito);
        letra.setDescripcion("ND");
        letra.setFecreg(d);
        letra.setMontoletra(BigDecimal.ZERO);
        letra.setInteres(BigDecimal.ZERO);
        letra.setSaldo(letra.getMonto());
        letra.setEstado("PN");
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
}
