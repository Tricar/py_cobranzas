package Bean;

import Dao.CredgestorDao;
import Dao.CredgestorDaoImp;
import Model.Anexo;
import Model.Credito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Creditogestor;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class credgestorBean implements Serializable {

    private List<Creditogestor> listafiltrada = new ArrayList();
    private Creditogestor credgestor = new Creditogestor();
    private List<Creditogestor> credgestores = new ArrayList();
    private String btnguardar;
    private List<Creditogestor> listaFiltTienda = new ArrayList();
    private Credito credito = new Credito();
    private Anexo anexo = new Anexo();
    private List<Anexo> gestores = new ArrayList();

    public credgestorBean() {
    }

    public List todos() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestores = linkdao.mostrarCreditogestors();
        return credgestores;
    }

    public void insertar(Credito cred, Anexo anexo) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor.setCredito(cred);
        credgestor.setAnexo(anexo);
        linkdao.insertarCreditogestor(credgestor);
    }

    public void modificar() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        linkdao.modificarCreditogestor(credgestor);
    }

    public void eliminar(Anexo anexo, Credito credito) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor = linkdao.getbyAnexoCredito(anexo, credito);
        linkdao.eliminarCreditogestor(credgestor);
    }

    public void nueva() {
        credgestor = new Creditogestor();
        btnguardar = "Asignar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdModify(Integer idcredaval) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor = linkdao.getbyId(idcredaval);
        btnguardar = "Modificar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idmovcaja) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor = linkdao.getbyId(idmovcaja);
        RequestContext.getCurrentInstance().update("frmEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }

    public List<Creditogestor> getCreditogestors() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestores = linkdao.mostrarCreditogestors();
        return credgestores;
    }

    public void insertarCreditoGestor(Credito cred, List<Anexo> avals) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credito = cred;
        gestores = avals;
        credgestor.setCredito(credito);
        for (int i = 0; i < gestores.size(); i++) {
            Anexo get = gestores.get(i);
            credgestor.setAnexo(get);
            linkdao.insertarCreditogestor(credgestor);
        }
    }

    public List<Anexo> gestoresxCredito(Credito cred) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credito = cred;        
        listafiltrada = linkdao.gestores(credito);
        if (!listafiltrada.isEmpty()) {
            for (int i = 0; i < listafiltrada.size(); i++) {
                try {                    
                    Creditogestor get = listafiltrada.get(i);
                    gestores.add(get.getAnexo());
                } catch (Exception e) {
                }
            }            
            return gestores;            
        }
        return gestores;
    }
    
    public String ingresar(){
        return "/venta/gestion.xhtml";
    }

    public List<Creditogestor> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Creditogestor> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Creditogestor getCredgestor() {
        return credgestor;
    }

    public void setCredgestor(Creditogestor credgestor) {
        this.credgestor = credgestor;
    }

    public List<Creditogestor> getCredgestores() {
        return credgestores;
    }

    public void setCredgestores(List<Creditogestor> credgestores) {
        this.credgestores = credgestores;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Creditogestor> getListaFiltTienda() {
        return listaFiltTienda;
    }

    public void setListaFiltTienda(List<Creditogestor> listaFiltTienda) {
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

    public List<Anexo> getGestores() {
        return gestores;
    }

    public void setGestores(List<Anexo> gestores) {
        this.gestores = gestores;
    }

    

}
