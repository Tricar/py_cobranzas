package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Model.Anexo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dajoh
 */
@ManagedBean
@ViewScoped
public class anexoBean {

    public Anexo anexo = new Anexo();
    public List<Anexo> anexos;

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
    
    public List<Anexo> getAnexos() {
        AnexoDao linkDao = new AnexoDaoImplements();
        anexos = linkDao.mostrarAnexo();
        return anexos;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public anexoBean() {
    }
    
    public void insertar (){
        AnexoDao linkDao = new AnexoDaoImplements();
        linkDao.insertarAnexo(anexo);
        anexo = new Anexo();
    }
    
    public void modificar (){
        AnexoDao linkDao = new AnexoDaoImplements();
        linkDao.modificarAnexo(anexo);
        anexo = new Anexo();
    }
    
    public void eliminar (){
        AnexoDao linkDao = new AnexoDaoImplements();
        linkDao.eliminarAnexo(anexo);
        anexo = new Anexo();
    }
}
