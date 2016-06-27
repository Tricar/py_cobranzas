package Bean;

import Dao.ConceptosDao;
import Dao.ConceptosDaoImp;
import Model.Anexo;
import Model.Credito;
import Model.Conceptos;
import java.io.Serializable;
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

public class conceptosBean implements Serializable {

    public Conceptos concepto = new Conceptos();
    public List<Conceptos> conceptos;
    public List<Conceptos> conceptosventa;
    private Credito credito = new Credito();
    private Anexo anexo = new Anexo();

    /**
     * Creates a new instance of letrasBean
     */
    public conceptosBean() {
    }

    public List<Conceptos> getConceptos() {
        ConceptosDao linkdao = new ConceptosDaoImp();
        conceptos = linkdao.mostrarConceptos();
        return conceptos;
    }

    public List<Conceptos> getConceptosventa() {
        ConceptosDao linkdao = new ConceptosDaoImp();
        conceptosventa = linkdao.mostrarConceptosXCred(credito);
        return conceptosventa;
    }
    
    public List<Conceptos> mostrarSoloConceptosxCred(Credito cred){
        credito = cred;
        ConceptosDao letdao = new ConceptosDaoImp();
        conceptos = letdao.mostrarConceptosXCred(credito);
        return conceptos;
    }

    public void insertar(Credito credito) {
        ConceptosDao linkDao = new ConceptosDaoImp();
        linkDao.insertarConcepto(concepto);
        concepto = new Conceptos();
    }

    public void cargarPorIdModify(Integer idconcepto) {
        ConceptosDao linkdao = new ConceptosDaoImp();
        concepto = linkdao.veryId(idconcepto);        
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }
    
    public void cargarPorIdDelete(Integer idconcepto) {
        ConceptosDao linkdao = new ConceptosDaoImp();
        concepto = linkdao.veryId(idconcepto);        
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }
    
    public void eliminar() {
        ConceptosDao linkdao = new ConceptosDaoImp();
        linkdao.eliminarConcepto(concepto);
    }

    public void setConceptos(List<Conceptos> letras) {
        this.conceptos = letras;
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

    public Conceptos getConcepto() {
        return concepto;
    }

    public void setConcepto(Conceptos concepto) {
        this.concepto = concepto;
    }
}
