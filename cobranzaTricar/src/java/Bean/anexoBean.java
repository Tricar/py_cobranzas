package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Anexo;
import Model.Credito;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ViewScoped
public class anexoBean implements Serializable {
    
    private Session session;
    private Transaction transaction;

    public Anexo anexo = new Anexo();
    public List<Anexo> anexos;
    private List<SelectItem> SelectItemsAnexo;
    private List<Anexo> query;
    private List<Anexo> filtradaEnter = new ArrayList();
    private List<Anexo> filtradaCredito = new ArrayList();
    private String text;
    private String nombre;
    private String dni;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public List<Anexo> getAnexos() {
        
        this.session = null;
        this.transaction = null;

        try {
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.anexos = daotanexo.verTodo(this.session);

            this.transaction.commit();

            return anexos;

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public List<Anexo> completarTipo(String nombre) {
        AnexoDao tipodao = new AnexoDaoImplements();
        anexos = tipodao.buscarxNombre(nombre);
        return anexos;
    }

    public List<SelectItem> getSelectItemsAnexo() {
//        this.SelectItemsAnexo = new ArrayList<SelectItem>();
//        AnexoDao anexoDao = new AnexoDaoImplements();
//        List<Anexo> tipos = anexoDao.verTodo(null);
//        for (Anexo tipo : tipos) {
//            SelectItem selecItem = new SelectItem(tipo, tipo.getNombre());
//            this.SelectItemsAnexo.add(selecItem);
//        }
//        return SelectItemsAnexo;
        return null;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public anexoBean() {
    }

    public void insertar() {
        return;
    }

    public void modificar() {
        return;
    }

    public void eliminar() {
        return;
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
    
    public List<Anexo> filtrarEmpleado(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("VE", "GE", "AD");
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
//        AnexoDao anex = new AnexoDaoImplements();
//        filtradaEnter = anex.buscarClientexDoc(dni, "CN", "CJ");        
        CreditoDao linkdao = new CreditoDaoImp();        
        creditos = linkdao.mostrarVentas();        
    }

    public List<Anexo> getFiltradaCredito() {
        Credito credito = new Credito();
        AnexoDao anexito = new AnexoDaoImplements();
        this.filtradaCredito = new ArrayList();
        int sw=0;
        for (int i = 0; i < creditos.size(); i++) {
            credito = creditos.get(i);            
            if(credito.getAnexoByIdanexo().getNumdocumento().startsWith(dni)){
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
