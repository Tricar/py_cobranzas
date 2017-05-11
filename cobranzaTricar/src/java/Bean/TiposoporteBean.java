package Bean;

import Dao.*;
import Model.Tiposoporte;
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
public class TiposoporteBean implements Serializable{
    
    private Tiposoporte tiposoporte;
    private List<Tiposoporte> tipoarticulos;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsColor;

    public TiposoporteBean() {
        this.tiposoporte = new Tiposoporte();
    }
    
    public List<Tiposoporte> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.tipoarticulos = daocolor.verTodo(this.session);
            this.transaction.commit();
            return tipoarticulos;
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
        tiposoporte = new Tiposoporte();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }
    
    public String codigo() {
        return "/repuesto/codigo";
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            TipoarticuloDao linkDao = new TipoarticuloDaoImp();
            if (linkDao.verByDescripcionDifer(this.session, this.tiposoporte.getIdtiposoporte(), this.tiposoporte.getTiposoporte()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya existe en DB."));
                tiposoporte = new Tiposoporte();
                return;
            }
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            daocolor.modificar(this.session, this.tiposoporte);
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

    public void cargarEditar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tiposoporte = daocolor.verByCodigo(this.session, idcolor);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEditar:panelEditar");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditar').show()");
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

    public void cargarEliminar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tiposoporte = daocolor.verByCodigo(this.session, idcolor);
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
            TiposoporteDao linkDao = new TiposoporteDaoImp();
            if (linkDao.verByDescripcion(this.session, this.tiposoporte.getTiposoporte()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya esta registrado."));
                tiposoporte = new Tiposoporte();
                return;
            }
            Date d = new Date();
            this.tiposoporte.setCreated(d);
            linkDao.registrar(this.session, this.tiposoporte);
            this.transaction.commit();
            this.tiposoporte = new Tiposoporte();
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
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            daocolor.eliminar(this.session, this.tiposoporte);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.tiposoporte = new Tiposoporte();
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
    
    public Tiposoporte getTiposoporte() {
        return tiposoporte;
    }

    public void setTiposoporte(Tiposoporte tiposoporte) {
        this.tiposoporte = tiposoporte;
    }
    
    public List<Tiposoporte> getTiposervicios() {
        this.session = null;
        this.transaction = null;
        try {
            TiposoporteDao daocolor = new TiposoporteDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            tipoarticulos = daocolor.verTodo(session);            
            return tipoarticulos;
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

    public void setTiposervicios(List<Tiposoporte> tipoarticulos) {
        this.tipoarticulos = tipoarticulos;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }
}
