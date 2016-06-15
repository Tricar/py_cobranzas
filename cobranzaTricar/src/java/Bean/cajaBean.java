package Bean;

import Dao.CajaDao;
import Dao.CajaDaoImp;
import Model.Caja;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class cajaBean implements Serializable {

    private List<Caja> listafiltrada = new ArrayList();
    private Caja caja = new Caja();
    private List<Caja> cajas = new ArrayList();
    private String btnguardar;
    private List<Caja> listaFiltTienda = new ArrayList();
    private List<Caja> verTodas = new ArrayList();

    public cajaBean() {
    }

    public List listaTipoDoc(String tipo) {
        CajaDao cajadao = new CajaDaoImp();
        listafiltrada = cajadao.mostrarxTienda(tipo);
        return listafiltrada;
    }

    public List todos() {
        CajaDao cajadao = new CajaDaoImp();
        cajas = cajadao.mostrarCajas();
        return cajas;
    }

    public void insertar() {
        CajaDao cajadao = new CajaDaoImp();
        try {
            if (btnguardar.equals("Registrar")) {
                cajadao.insertarCaja(caja);
            } else {
                cajadao.modificarCaja(caja);
            }
            RequestContext.getCurrentInstance().update("formInsertar");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
        } catch (Error e) {
            RequestContext.getCurrentInstance().update("formInsertar");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
        }
    }

    public void modificar() {
        CajaDao cajadao = new CajaDaoImp();
        cajadao.modificarCaja(caja);
    }

    public void modificarExt(Caja caja) {
        CajaDao cajadao = new CajaDaoImp();
        cajadao.modificarCaja(caja);
    }

    public void eliminar() {
        CajaDao cajadao = new CajaDaoImp();
        cajadao.eliminarCaja(caja);
    }

    public void nueva() {
        caja = new Caja();
        btnguardar = "Registrar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdModify(Integer idcaja) {
        CajaDao cajadao = new CajaDaoImp();
        caja = cajadao.veryId(idcaja);
        btnguardar = "Modificar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idcaja) {
        CajaDao cajadao = new CajaDaoImp();
        caja = cajadao.veryId(idcaja);
        RequestContext.getCurrentInstance().update("frmEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }

    public List<Caja> filtrarTienda(String tienda) {
        CajaDao cajadao = new CajaDaoImp();
        if (tienda.equals("V3")) {
            listaFiltTienda = cajadao.mostrarCajas();
        } else {
            listaFiltTienda = cajadao.mostrarxTienda(tienda);
        }
        return listaFiltTienda;
    }

    public List<Caja> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Caja> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public List<Caja> getCajas() {
        CajaDao cajadao = new CajaDaoImp();
        cajas = cajadao.mostrarCajas();
        return cajas;
    }

    public void setCajas(List<Caja> cajas) {
        this.cajas = cajas;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Caja> getListaFiltTienda() {
        return listaFiltTienda;
    }

    public void setListaFiltTienda(List<Caja> listaFiltTienda) {
        this.listaFiltTienda = listaFiltTienda;
    }

    public List<Caja> getVerTodas() {
        CajaDao cajadao = new CajaDaoImp();
        verTodas = cajadao.mostrarCajas();
        return verTodas;
    }

    public void setVerTodas(List<Caja> verTodas) {
        this.verTodas = verTodas;
    }

}