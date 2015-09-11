package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Anexo;
import Model.Credito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ViewScoped
public class anexoBean implements Serializable {

    public Anexo anexo = new Anexo();
    public List<Anexo> anexos;
    private List<SelectItem> SelectItemsAnexo;
    private List<Anexo> query;
    private List<Anexo> filtradaEnter = new ArrayList();
    private List<Anexo> filtradaCredito = new ArrayList();
    private String text;
    private String nombre;
    private List<Credito> creditos = new ArrayList();

    public Anexo getAnexo() {
        return anexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Anexo> filtrarCliente(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipo("CL");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarVendedor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipo("VE");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarGestor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipo("GE");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarAval(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("VE", "GE", "CL");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().toLowerCase().startsWith(name)) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Credito> getCreditos() {        
        return creditos;
    }

    public void filtrarClientEnter() {
        AnexoDao anex = new AnexoDaoImplements();
        filtradaEnter = anex.buscarCliente(nombre, "CL");
        CreditoDao linkdao = new CreditoDaoImp();
        creditos = linkdao.mostrarVentas();
    }

    public List<Anexo> getFiltradaCredito() {
        Credito credito = new Credito();
        AnexoDao anexito = new AnexoDaoImplements();
        int sw=0;
        for (int i = 0; i < creditos.size(); i++) {
            credito = creditos.get(i);            
            if(credito.getAnexoByIdanexo().getNombre().startsWith(nombre)){
                sw=1;                
                anexo = anexito.cargarxCredito(credito.getAnexoByIdanexo().getIdanexo());
                filtradaCredito.add(anexo);
            }
        }
        if (sw==1){
            return filtradaCredito;
        }else {
            filtradaCredito= new ArrayList();
        }
        return filtradaCredito;        
    }

    public void handleKeyEvent() {
        text = text.toUpperCase();
    }

    public void handleSelect(SelectEvent e) {
        Anexo p = (Anexo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Anexo agregado :: " + p.getNombre(), ""));
    }

    public void handleUnSelect(UnselectEvent e) {
        Anexo p = (Anexo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Remove Player :: Player Info: ID :: " + p.getIdanexo() + " :: Name :: " + p.getNombre(), ""));
    }

    public void phaseListener(PhaseEvent e) {
        List<FacesMessage> messages = e.getFacesContext().getMessageList();
        System.out.println(messages.size());
    }
}
