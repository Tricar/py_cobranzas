package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Datoscredito;
import Model.Credito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;

@ManagedBean
@SessionScoped
public class creditoBean implements Serializable{

    public Credito credito = new Credito();
    public List<Credito> creditos;
    private List<Datoscredito> lista = new ArrayList();
    private HtmlInputText nletras = new HtmlInputText();

    public HtmlInputText getNletras() {
        return nletras;
    }

    public void setNletras(HtmlInputText nletras) {
        this.nletras = nletras;
    }
    
    public List<Datoscredito> getLista() {
        return lista;
    }

    public void setLista(List<Datoscredito> lista) {
        this.lista = lista;
    }
    
    public creditoBean() {
    }

    public Credito getVenta() {
        return credito;
    }

    public void setVenta(Credito credito) {
        this.credito = credito;
    }

    public List<Credito> getVentas() {
        CreditoDao linkdao = new CreditoDaoImp();
        creditos = linkdao.mostrarVentas();
        return creditos;
    }

    public void setVentas(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public void insertar (){
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.insertarVenta(credito);
    }
    
    public void modificar(){
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.modificarVenta(credito);
        credito = new Credito();
    }
    
    public void eliminar(){
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.eliminarVenta(credito);
        credito = new Credito();
    }
       
}