package Bean;

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

    public void insertar() {
        CreditoDao linkDao = new CreditoDaoImp();
        credito.setSaldo(credito.getPrecio().subtract(credito.getInicial()));
        linkDao.insertarVenta(credito);
        Letras letras = new Letras();
        BigDecimal nletras = new BigDecimal(credito.getNletras());
        BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
        BigDecimal interes = new BigDecimal(0);
        for (int i = 1; i <= (credito.getNletras()); i++) {
            interes = interes.add(credito.getInteres());
        }
        Date fechaini = new Date();
        fechaini = credito.getFechareg();
        Date fechafin = new Date();
        fechafin = sumaDias(fechaini, 30);
        BigDecimal cien = new BigDecimal(100);
        BigDecimal mtointeres = (montoletras.multiply(interes)).divide(cien);
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
            letrasdao.insertarLetra(letras);
        }
    }

    public void modificar() {
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.modificarVenta(credito);
        credito = new Credito();
    }

    public void eliminar() {
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.eliminarVenta(credito);
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
        CreditoDao linkdao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        BigDecimal cero = new BigDecimal(0);
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        credito = linkdao.cargarCredito(anexo);
        LetrasDao letras = new LetrasDaoImplements();
        letritas = letras.mostrarLetrasXCred(credito);
        for (int i = 0; i < letritas.size(); i++) {
            Letras get = letritas.get(i);
            if (get.getSaldo().compareTo(cero) == 0) {
                get.setEstado("CN");
            } else {
                if (get.getSaldo().compareTo(cero) == 1) {
                    if (get.getFecini().after(fecha)) {
                        get.setEstado("PN");
                    }else
                        get.setEstado("VN");
                }
            }
            letrasdao.modificarLetra(get);
        }
        letraslista = letras.mostrarLetrasXCred(credito);
//      return credito;
    }
    
}
