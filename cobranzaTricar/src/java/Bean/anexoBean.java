package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Model.Anexo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class anexoBean implements Serializable {

    public Anexo anexo = new Anexo();
    public List<Anexo> anexos;
    private List<SelectItem> SelectItemsAnexo;
    private List<Anexo> query;

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

    public List<Anexo> completarTipo(String nombre) {
        AnexoDao tipodao = new AnexoDaoImplements();
        anexos = tipodao.buscarxNombre(nombre);
        return anexos;
    }

    public List<SelectItem> getSelectItemsAnexo() {
        this.SelectItemsAnexo = new ArrayList<SelectItem>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.mostrarAnexo();
        for (Anexo tipo : tipos) {
            SelectItem selecItem = new SelectItem(tipo, tipo.getNombre());
            this.SelectItemsAnexo.add(selecItem);
        }
        return SelectItemsAnexo;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public anexoBean() {
    }

    public void insertar() {
        AnexoDao linkDao = new AnexoDaoImplements();
        Date d = new Date();
        anexo.setFechareg(d);
        linkDao.insertarAnexo(anexo);
        anexo = new Anexo();
    }

    public void modificar() {
        AnexoDao linkDao = new AnexoDaoImplements();
        linkDao.modificarAnexo(anexo);
        anexo = new Anexo();
    }

    public void eliminar() {
        AnexoDao linkDao = new AnexoDaoImplements();
        linkDao.eliminarAnexo(anexo);
        anexo = new Anexo();
    }

    public List<Anexo> filtrarTipoAnexo(int tipo) {
        AnexoDao linkDao = new AnexoDaoImplements();
        anexos = linkDao.filtarTipoAnexo(tipo);
        return anexos;
    }

    public List<Anexo> getQueryCliente(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoCliente(10,24);
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);                
            }           
        }        
        return query;
    }
    
    public List<Anexo> getQueryVendedor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoAnexo(22);
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);                
            }           
        }        
        return query;
    }
}