package Bean;

import Dao.HistoDao;
import Dao.HistoDaoImp;
import Model.Historialmora;
import Model.Letras;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class historialBean {
    
    private List<Historialmora> historiales = new ArrayList();
    private Historialmora historial = new Historialmora();
    private Letras letra = new Letras();
    
    public historialBean() {
    }

    public List<Historialmora> getHistoriales() {
        return historiales;
    }
    public List todos() {
        HistoDao cajadao = new HistoDaoImp();
        historiales = cajadao.mostrarHistoriales();
        return historiales;
    }

    public void insertar(Historialmora histo) {
        HistoDao histodao = new HistoDaoImp();        
        histodao.insertarHist(histo);
    }

    public void modificar() {
        HistoDao histodao = new HistoDaoImp();
        histodao.modificarHist(historial);
    }

    public void eliminar() {
        HistoDao histodao = new HistoDaoImp();
        histodao.eliminarHist(historial);
    }

    public void nuevo() {
        historial = new Historialmora();
         
    }

    public void cargarPorIdModify(Integer idmovcaja) {
        HistoDao cajadao = new HistoDaoImp();        
    }

    public void cargarPorIdDelete(Integer idmovcaja) {
        HistoDao cajadao = new HistoDaoImp();        
    }

    public void setHistoriales(List<Historialmora> historiales) {
        this.historiales = historiales;
    }

    public Historialmora getHistorial() {
        return historial;
    }

    public void setHistorial(Historialmora historial) {
        this.historial = historial;
    }

    public Letras getLetra() {
        return letra;
    }

    public void setLetra(Letras letra) {
        this.letra = letra;
    }
    
}
