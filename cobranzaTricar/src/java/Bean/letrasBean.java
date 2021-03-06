package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Model.Credito;
import Model.Letras;
import Model.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped

public class letrasBean implements Serializable {

    public Letras letra = new Letras();
    public List<Letras> letras;
    public List<Letras> letrasventa;
    private Credito credito = new Credito();
    private BigDecimal res;
    private Date fechafin;

    /**
     * Creates a new instance of letrasBean
     */
    public letrasBean() {
    }

    public void cargarModNotaDebito(Letras let) {
        letra = let;
        System.out.println("cargado letra: "+letra.getDescripcion());
        RequestContext.getCurrentInstance().update("formModNd");
        RequestContext.getCurrentInstance().execute("PF('dlgmodnd').show()");
    }

    public void modificarMora(Usuario usuario) {
        try {
            System.out.println("letra "+letra.getDescripcion());
            System.out.println("usuario: "+usuario.getUsuario());
            LetrasDao letdao = new LetrasDaoImplements();
            letra.setModificado(usuario.getIdusuario());
            letdao.modificarLetra(letra);
            RequestContext.getCurrentInstance().update("formModNd");
            RequestContext.getCurrentInstance().execute("PF('dlgmodnd').hide()");
        } catch (Exception e) {
        }
    }

    public List<Letras> getLetras() {
        LetrasDao linkdao = new LetrasDaoImplements();
        letras = linkdao.mostrarLetras();
        return letras;
    }

    public List<Letras> getLetrasventa() {
        LetrasDao linkdao = new LetrasDaoImplements();
        letrasventa = linkdao.mostrarLetrasXCred(credito);
        return letrasventa;
    }

    public List<Letras> mostrarSoloLetrasxCred(Credito cred) {
        credito = cred;
        LetrasDao letdao = new LetrasDaoImplements();
        letras = letdao.mostrarSoloLetrasxCred(credito, "ND");
        return letras;
    }
    
    public List<Letras> mostrarLetrasxCredEstado(Credito cred) {
        credito = cred;
        LetrasDao letdao = new LetrasDaoImplements();
        letras = letdao.mostrarLetrasxCredEstado(credito, "M", "CN");
        return letras;
    }
    
    public List<Letras> mostrarDebitosxCred(Credito cred) {
        credito = cred;
        LetrasDao letdao = new LetrasDaoImplements();
        letras = letdao.mostrarDebitosxCred(credito);        
        return letras;
    }
    
    public List<Letras> mostrarDebitosxCredEstado(Credito cred) {
        credito = cred;
        LetrasDao letdao = new LetrasDaoImplements();
        letras = letdao.mostrarDebitosxCredEstado(credito, "CN");        
        return letras;
    }

    public void insertar(Credito credito) {
        LetrasDao linkDao = new LetrasDaoImplements();
        linkDao.insertarLetra(letra);
        letra = new Letras();
    }

    public void modificar() {
        LetrasDao linkDao = new LetrasDaoImplements();
        letra.setMontoletra(res);
        letra.setFecven(fechafin);
        linkDao.modificarLetra(letra);
        letra = new Letras();
    }

    public void eliminar() {
        LetrasDao linkDao = new LetrasDaoImplements();
        CreditoDao creditodao = new CreditoDaoImp();
        credito = creditodao.cargarCreditoxLetra(letra);
        credito.setTotaldeuda(credito.getTotaldeuda().subtract(letra.getMonto()));
        creditodao.modificarVenta(credito);
        linkDao.eliminarLetra(letra);
        letra = new Letras();
    }

    public void resultadoSaldo() {
        res = letra.getMonto().add(letra.getInteres());
    }

    public Date sumaDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
    }

    public void resultadoFecha() {
        fechafin = sumaDias(letra.getFecini(), 30);
    }

    public void setLetras(List<Letras> letras) {
        this.letras = letras;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public BigDecimal getRes() {
        return res;
    }

    public void setRes(BigDecimal res) {
        this.res = res;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Letras getLetra() {
        return letra;
    }

    public void setLetra(Letras letra) {
        this.letra = letra;
    }
}
