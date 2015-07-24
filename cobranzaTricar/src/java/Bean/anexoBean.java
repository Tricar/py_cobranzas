package Bean;

import Dao.AbstractDao;
import Dao.AnexoDaoImplements;
import Model.Anexo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
        AbstractDao linkDao = new AnexoDaoImplements();
        anexos = linkDao.mostrarAnexo();
        return anexos;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public anexoBean() {
    }
    
    public void insertar (){
        AbstractDao linkDao = new AnexoDaoImplements();
        linkDao.insertarAnexo(anexo);
        anexo = new Anexo();
    }
    
    public void modificar (){
        AbstractDao linkDao = new AnexoDaoImplements();
        linkDao.modificarAnexo(anexo);
        anexo = new Anexo();
    }
    
    public void eliminar (){
        AbstractDao linkDao = new AnexoDaoImplements();
        linkDao.eliminarAnexo(anexo);
        anexo = new Anexo();
    }
}
