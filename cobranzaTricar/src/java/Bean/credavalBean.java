package Bean;

import Dao.CredavalDao;
import Dao.CredavalDaoImp;
import Model.Anexo;
import Model.Credito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Creditoaval;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class credavalBean implements Serializable {

    private List<Creditoaval> listafiltrada = new ArrayList();    
    private Creditoaval credaval = new Creditoaval();
    private List<Creditoaval> credavales = new ArrayList();
    private String btnguardar;
    private List<Creditoaval> listaFiltTienda = new ArrayList();
    private Credito credito = new Credito();
    private Anexo anexo = new Anexo();
    private List<Anexo> avales = new ArrayList();

    public credavalBean() {
    }

    public List todos() {
        CredavalDao linkdao = new CredavalDaoImp();
        credavales = linkdao.mostrarCreditoavals();
        return credavales;
    }

    public void insertar(Credito cred, Anexo anexo) {
        CredavalDao linkdao = new CredavalDaoImp();
        credaval.setCredito(cred);
        credaval.setAnexo(anexo);
        linkdao.insertarCreditoaval(credaval);
    }

    public void modificar() {
        CredavalDao linkdao = new CredavalDaoImp();
        linkdao.modificarCreditoaval(credaval);
    }

    public void eliminar(Anexo anexo, Credito credito) {
        CredavalDao linkdao = new CredavalDaoImp();
        credaval = linkdao.getbyAnexoCredito(anexo, credito);
        linkdao.eliminarCreditoaval(credaval);
    }

    public void nueva() {
        credaval = new Creditoaval();
        btnguardar = "Registrar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdModify(Integer idcredaval) {
        CredavalDao linkdao = new CredavalDaoImp();
        credaval = linkdao.getbyId(idcredaval);
        btnguardar = "Modificar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idmovcaja) {
        CredavalDao linkdao = new CredavalDaoImp();
        credaval = linkdao.getbyId(idmovcaja);
        RequestContext.getCurrentInstance().update("frmEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }    

    public List<Creditoaval> getCreditoavals() {
        CredavalDao linkdao = new CredavalDaoImp();
        credavales = linkdao.mostrarCreditoavals();
        return credavales;
    }
    
    public void insertarCreditoAval(Credito cred, List<Anexo> avals){
        CredavalDao linkdao = new CredavalDaoImp();
        credito = cred;
        avales = avals;
        credaval.setCredito(credito);
        for (int i = 0; i < avales.size(); i++) {
            Anexo get = avales.get(i);
            credaval.setAnexo(get);
            linkdao.insertarCreditoaval(credaval);
        }
    }
    
    public List<Anexo> avalesxCredito(Credito cred){        
        CredavalDao linkdao = new CredavalDaoImp();
        credito = cred;
        listafiltrada = linkdao.avales(credito);
        for (int i = 0; i < listafiltrada.size(); i++) {
            Creditoaval get = listafiltrada.get(i);
            avales.add(get.getAnexo());
        }
        return avales;
    }

    public List<Creditoaval> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Creditoaval> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Creditoaval getCredaval() {
        return credaval;
    }

    public void setCredaval(Creditoaval credaval) {
        this.credaval = credaval;
    }

    public List<Creditoaval> getCredavales() {
        return credavales;
    }

    public void setCredavales(List<Creditoaval> credavales) {
        this.credavales = credavales;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Creditoaval> getListaFiltTienda() {
        return listaFiltTienda;
    }

    public void setListaFiltTienda(List<Creditoaval> listaFiltTienda) {
        this.listaFiltTienda = listaFiltTienda;
    }    

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public List<Anexo> getAvales() {
        return avales;
    }

    public void setAvales(List<Anexo> avales) {
        this.avales = avales;
    }

}
