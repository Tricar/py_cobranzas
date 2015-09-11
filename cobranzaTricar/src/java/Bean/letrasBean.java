package Bean;

import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Model.Credito;
import Model.Letras;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

    /**
     * Creates a new instance of letrasBean
     */
    public letrasBean() {
    }

    public Letras getLetra() {
        return letra;
    }

    public void setLetra(Letras letra) {
        this.letra = letra;
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

    public void setLetras(List<Letras> letras) {
        this.letras = letras;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public void insertar(Credito credito) {
        LetrasDao linkDao = new LetrasDaoImplements();
        linkDao.insertarLetra(letra);
        letra = new Letras();
    }

    public void modificar() {
        LetrasDao linkDao = new LetrasDaoImplements();
        linkDao.modificarLetra(letra);
        letra = new Letras();
    }

    public void eliminar() {
        LetrasDao linkDao = new LetrasDaoImplements();
        linkDao.eliminarLetra(letra);
        letra = new Letras();
    }
    
}
