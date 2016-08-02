package Bean;

import Dao.RequisitosDao;
import Dao.RequisitosDaoImp;
import Model.Credito;
import Model.Requisitos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped

public class requisitosBean implements Serializable {

    public Requisitos requisito = new Requisitos();
    public List<Requisitos> requisitos;
    private Credito credito = new Credito();
    private List<String> reqs;
    
    @PostConstruct
    public void init() {
        reqs = new ArrayList<String>();
        reqs.add("Miami");
        reqs.add("London");        
    }

    public requisitosBean() {
    }

    public List<Requisitos> getRequisitos() {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisitos = linkdao.mostrarRequisitos();
        return requisitos;
    }

    public List<Requisitos> mostrarSoloRequisitosxCred(Credito cred){
        credito = cred;
        RequisitosDao letdao = new RequisitosDaoImp();
        requisitos = letdao.mostrarRequisitosXCred(credito);
        return requisitos;
    }

    public void insertar(Credito credito) {
        RequisitosDao linkDao = new RequisitosDaoImp();
        requisito.setCredito(credito);
        linkDao.insertarRequisito(requisito);                
        requisito = new Requisitos();
    }

    public void cargarPorIdModify(Integer idrequisito) {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisito = linkdao.veryId(idrequisito);        
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }
    
    public void cargarPorIdDelete(Integer idrequisito) {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisito = linkdao.veryId(idrequisito);        
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }
    
    public void eliminar() {
        RequisitosDao linkdao = new RequisitosDaoImp();
        linkdao.eliminarRequisito(requisito);
    }

    public Requisitos getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisitos requisito) {
        this.requisito = requisito;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public List<String> getReqs() {
        return reqs;
    }
    
}