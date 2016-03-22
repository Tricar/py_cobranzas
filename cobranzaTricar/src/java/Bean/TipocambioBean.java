package Bean;

import Dao.TipocambioDaoImp;
import Model.Tipocambio;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class TipocambioBean implements Serializable{
    
    private Tipocambio tipoc;
    private List<Tipocambio> tipocs;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsTipocambio;

    public TipocambioBean() {
        this.tipoc = new Tipocambio();
    }
    
    public List<Tipocambio> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.tipocs = tipocdao.verTodo(this.session);
            this.transaction.commit();
            return tipocs;
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

    public void modificar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            tipocdao.modificar(this.session, this.tipoc);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
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

    public void cargarTipocambioEditar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();            
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEditarTipocambio:panelEditarTipocambio");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarTipocambio').show()");
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

    public void cargarTipocambioEliminar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();            
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEliminarTipocambio");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarTipocambio').show()");
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

    public void registrar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            TipocambioDaoImp linkDao = new TipocambioDaoImp();            
            Date d = new Date();
            this.tipoc.setFecreg(d);
            linkDao.registrar(this.session, this.tipoc);
            this.transaction.commit();
            this.tipoc = new Tipocambio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
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

    public void eliminar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            tipocdao.eliminar(this.session, this.tipoc);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Tipocambio Correctamente."));
            this.tipoc = new Tipocambio();
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
    
    public Tipocambio getTipoc() {
        return tipoc;
    }

    public void setTipocambio(Tipocambio tipoc) {
        this.tipoc = tipoc;
    }
    
    public List<Tipocambio> getTipocambios() {
        this.session = null;
        this.transaction = null;
        try {
            TipocambioDaoImp tipocdao = new TipocambioDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            tipocs = tipocdao.verTodo(session);            
            return tipocs;
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

    public void setTipocambioes(List<Tipocambio> tipocs) {
        this.tipocs = tipocs;
    }

    public void setSelectItemsTipocambio(List<SelectItem> SelectItemsTipocambio) {
        this.SelectItemsTipocambio = SelectItemsTipocambio;
    }
}
