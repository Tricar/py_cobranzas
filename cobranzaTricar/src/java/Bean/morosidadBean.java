package Bean;

import Dao.MovcajaDao;
import Dao.MovcajaDaoImp;
import Model.Movcaja;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class morosidadBean implements Serializable {

    private List<Movcaja> listafiltrada = new ArrayList();
    private Movcaja movcaja = new Movcaja();
    private List<Movcaja> cajas = new ArrayList();
    private String btnguardar;
    private List<Movcaja> listaFiltTienda = new ArrayList();

    public morosidadBean() {
    }

    public List todos() {
        MovcajaDao cajadao = new MovcajaDaoImp();
        cajas = cajadao.mostrarMovcajas();
        return cajas;
    }

    public void insertar(Movcaja movcaja) {
        MovcajaDao cajadao = new MovcajaDaoImp();        
        cajadao.insertarMovcaja(movcaja);
    }

    public void modificar() {
        MovcajaDao cajadao = new MovcajaDaoImp();
        cajadao.modificarMovcaja(movcaja);
    }

    public void eliminar() {
        MovcajaDao cajadao = new MovcajaDaoImp();
        cajadao.eliminarMovcaja(movcaja);
    }

    public void nueva() {
        movcaja = new Movcaja();
        btnguardar = "Registrar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdModify(Integer idmovcaja) {
        MovcajaDao cajadao = new MovcajaDaoImp();
        movcaja = cajadao.getbyId(idmovcaja);
        btnguardar = "Modificar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idmovcaja) {
        MovcajaDao cajadao = new MovcajaDaoImp();
        movcaja = cajadao.getbyId(idmovcaja);
        RequestContext.getCurrentInstance().update("frmEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }

    public List<Movcaja> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Movcaja> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Movcaja getMovcaja() {
        return movcaja;
    }

    public void setMovcaja(Movcaja movcaja) {
        this.movcaja = movcaja;
    }

    public List<Movcaja> getMovcajas() {
        MovcajaDao cajadao = new MovcajaDaoImp();
        cajas = cajadao.mostrarMovcajas();
        return cajas;
    }

    public void setMovcajas(List<Movcaja> cajas) {
        this.cajas = cajas;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Movcaja> getListaFiltTienda() {
        return listaFiltTienda;
    }

    public void setListaFiltTienda(List<Movcaja> listaFiltTienda) {
        this.listaFiltTienda = listaFiltTienda;
    }

}
