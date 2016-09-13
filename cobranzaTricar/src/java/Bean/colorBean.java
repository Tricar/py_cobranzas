package Bean;

import Dao.ColorDao;
import Dao.ColorDaoImplements;
import Model.Color;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class colorBean implements Serializable{
    
    private Color colo;
    private List<Color> colores;
    private List<Color> query;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsColor;

    public colorBean() {
        this.colo = new Color();
    }
    
    public List<Color> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            ColorDaoImplements daocolor = new ColorDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.colores = daocolor.verTodo(this.session);
            this.transaction.commit();
            return colores;
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
            ColorDaoImplements linkDao = new ColorDaoImplements();
            if (linkDao.verByColorDifer(this.session, this.colo.getIdcolor(), this.colo.getColor()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya existe en DB."));
                colo = new Color();
                return;
            }
            ColorDaoImplements daocolor = new ColorDaoImplements();
            daocolor.modificar(this.session, this.colo);
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
            ColorDaoImplements daocolor = new ColorDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.colo = daocolor.verByCodigo(this.session, idcolor);
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
            ColorDaoImplements daocolor = new ColorDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.colo = daocolor.verByCodigo(this.session, idcolor);
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
            ColorDaoImplements linkDao = new ColorDaoImplements();
            if (linkDao.verByColor(this.session, this.colo.getColor()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Color ya esta registrado."));
                colo = new Color();
                return;
            }
            Date d = new Date();
            this.colo.setFecreg(d);
            linkDao.registrar(this.session, this.colo);
            this.transaction.commit();
            this.colo = new Color();
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
            ColorDaoImplements daocolor = new ColorDaoImplements();
            daocolor.eliminar(this.session, this.colo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.colo = new Color();
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
    
    public Color getColo() {
        return colo;
    }

    public void setColor(Color colo) {
        this.colo = colo;
    }
    
    public List<Color> getColores() {
        this.session = null;
        this.transaction = null;
        try {
            ColorDaoImplements daocolor = new ColorDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            colores = daocolor.verTodo(session);            
            return colores;
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

    public void setColores(List<Color> colores) {
        this.colores = colores;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }

    public List<Color> filtrarColor(String name) {
        this.query = new ArrayList<Color>();
        ColorDao colorDao = new ColorDaoImplements();
        List<Color> tipos = colorDao.filtarTipoDos();
        for (Color tipo : tipos) {
            if (tipo.getColor().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }
    
}
