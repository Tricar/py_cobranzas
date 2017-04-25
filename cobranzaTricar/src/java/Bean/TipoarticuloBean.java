package Bean;

import Dao.*;
import Model.Tipoarticulo;
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
public class TipoarticuloBean implements Serializable{
    
    private Tipoarticulo tipoarticulo;
    private List<Tipoarticulo> tipoarticulos;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsColor;

    public TipoarticuloBean() {
        this.tipoarticulo = new Tipoarticulo();
    }
    
    public List<Tipoarticulo> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
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
        tipoarticulo = new Tipoarticulo();
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
            if (linkDao.verByDescripcionDifer(this.session, this.tipoarticulo.getIdtipoarticulo(), this.tipoarticulo.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya existe en DB."));
                tipoarticulo = new Tipoarticulo();
                return;
            }
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
            daocolor.modificar(this.session, this.tipoarticulo);
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
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tipoarticulo = daocolor.verByCodigo(this.session, idcolor);
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
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tipoarticulo = daocolor.verByCodigo(this.session, idcolor);
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
            TipoarticuloDao linkDao = new TipoarticuloDaoImp();
            if (linkDao.verByDescripcion(this.session, this.tipoarticulo.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya esta registrado."));
                tipoarticulo = new Tipoarticulo();
                return;
            }
            Date d = new Date();
            this.tipoarticulo.setCreated(d);
            linkDao.registrar(this.session, this.tipoarticulo);
            this.transaction.commit();
            this.tipoarticulo = new Tipoarticulo();
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
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
            daocolor.eliminar(this.session, this.tipoarticulo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.tipoarticulo = new Tipoarticulo();
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
    
    public Tipoarticulo getTipoarticulo() {
        return tipoarticulo;
    }

    public void setTipoarticulo(Tipoarticulo tipoarticulo) {
        this.tipoarticulo = tipoarticulo;
    }
    
    public List<Tipoarticulo> getTiposervicios() {
        this.session = null;
        this.transaction = null;
        try {
            TipoarticuloDao daocolor = new TipoarticuloDaoImp();
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

    public void setTiposervicios(List<Tipoarticulo> tipoarticulos) {
        this.tipoarticulos = tipoarticulos;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }
}
