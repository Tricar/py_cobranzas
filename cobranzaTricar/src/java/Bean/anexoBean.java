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
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
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
    private String razonsocial;

    public anexoBean() {
    }
    
    public List<Anexo> verCliente() {

        this.session = null;
        this.transaction = null;

        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.anexos = anexoDao.verCliente(this.session);

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
    
    public List<Anexo> verEmpleado() {

        this.session = null;
        this.transaction = null;

        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.anexos = anexoDao.verEmpleado(this.session);

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public List<Anexo> getQuery() {
        return query;
    }

    public List<Anexo> getFiltradaEnter() {
        return filtradaEnter;
    }

    public String getText() {
        return text;
    }

    public String getRazonsocial() {
        return razonsocial;
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

    public void insertarcliente() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (daotanexo.verByAnexo(this.session, this.anexo.getNombre()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Cliente ya existe en DB."));
                this.anexo = new Anexo();
                return;
            }

            if (!this.razonsocial.equals("")) {
                this.anexo.setNombre(this.razonsocial);
            }
            if(anexo.getTipodocumento().equals("DNI")){
                this.anexo.setTipoanexo("CN");
            }else{
                this.anexo.setTipoanexo("CJ");
            }            
            Date d = new Date();
            this.anexo.setFechareg(d);
            this.anexo.setCodven("");

            daotanexo.registrar(this.session, this.anexo);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

            this.anexo = new Anexo();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));

            this.anexo = new Anexo();

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            AnexoDaoImplements daotanexo = new AnexoDaoImplements();

            if (daotanexo.verByAnexoDifer(this.session, this.anexo.getIdanexo(), this.anexo.getNombre()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Anexo ya Existe."));
                return;
            }
            if(anexo.getTipodocumento().equals("DNI")){
                this.anexo.setTipoanexo("CN");
            }else{
                this.anexo.setTipoanexo("CJ");
            }

            daotanexo.modificar(this.session, this.anexo);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));

            this.anexo = new Anexo();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));

            this.anexo = new Anexo();

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void eliminar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            AnexoDaoImplements daotanexo = new AnexoDaoImplements();

            daotanexo.eliminar(this.session, this.anexo);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Anexo Correctamente."));

            this.anexo = new Anexo();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void cargarAnexoEditar(int idanexo) {
        this.session = null;
        this.transaction = null;

        try {

            AnexoDaoImplements daotanexo = new AnexoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.anexo = daotanexo.verByIdanexo(this.session, idanexo);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditarAnexo:panelEditarAnexo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarAnexo').show()");

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void cargarAnexoEliminar(int idanexo) {
        this.session = null;
        this.transaction = null;

        try {

            AnexoDaoImplements daotanexo = new AnexoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.anexo = daotanexo.verByIdanexo(this.session, idanexo);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarAnexo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarAnexo').show()");

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
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
        int sw = 0;
        for (int i = 0; i < creditos.size(); i++) {
            credito = creditos.get(i);
            if (credito.getAnexoByIdanexo().getNumdocumento().startsWith(dni)) {
                sw = 1;
                anexo = anexito.cargarxCredito(credito.getAnexoByIdanexo().getIdanexo());
                filtradaCredito.add(anexo);
            }
        }
        if (sw == 1) {
            return filtradaCredito;
        } else {
            filtradaCredito = new ArrayList();
        }
        return filtradaCredito;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
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

        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.SelectItemsAnexo = new ArrayList<SelectItem>();
            AnexoDao anexoDao = new AnexoDaoImplements();
            List<Anexo> tipos = anexoDao.verTodo(null);
            for (Anexo tipo : tipos) {
                SelectItem selecItem = new SelectItem(tipo, tipo.getNombre());
                this.SelectItemsAnexo.add(selecItem);
            }
            return SelectItemsAnexo;

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
