package Bean;

import Dao.*;
import Model.Repuesto;
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
public class repuestoBean implements Serializable{
    
    private Repuesto repuesto;
    private List<Repuesto> repuestos;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsColor;

    public repuestoBean() {
        this.repuesto = new Repuesto();
    }
    
    public List<Repuesto> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            repuestoDao daocolor = new repuestoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.repuestos = daocolor.verTodo(this.session);
            this.transaction.commit();
            return repuestos;
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
    
    public void nuevo() {
        repuesto = new Repuesto();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            repuestoDao linkDao = new repuestoDaoImp();
            if (linkDao.verByDescripcionDifer(this.session, this.repuesto.getIdrepuesto(), this.repuesto.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya existe en DB."));
                repuesto = new Repuesto();
                return;
            }
            repuestoDao daocolor = new repuestoDaoImp();
            daocolor.modificar(this.session, this.repuesto);
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

    public void cargarColorEditar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            repuestoDao daocolor = new repuestoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.repuesto = daocolor.verByCodigo(this.session, idcolor);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEditarColor:panelEditarColor");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarColor').show()");
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

    public void cargarColorEliminar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            repuestoDao daocolor = new repuestoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.repuesto = daocolor.verByCodigo(this.session, idcolor);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEliminarColor");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarColor').show()");
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
            repuestoDao linkDao = new repuestoDaoImp();
            if (linkDao.verByDescripcion(this.session, this.repuesto.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya esta registrado."));
                repuesto = new Repuesto();
                return;
            }
            Date d = new Date();
            this.repuesto.setCreated(d);
            this.repuesto.setCantidad(0);
            linkDao.registrar(this.session, this.repuesto);
            this.transaction.commit();
            this.repuesto = new Repuesto();
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
            repuestoDao daocolor = new repuestoDaoImp();
            daocolor.eliminar(this.session, this.repuesto);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.repuesto = new Repuesto();
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
    
    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }
    
    public List<Repuesto> getRepuestos() {
        this.session = null;
        this.transaction = null;
        try {
            repuestoDao daocolor = new repuestoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            repuestos = daocolor.verTodo(session);            
            return repuestos;
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

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }
}
