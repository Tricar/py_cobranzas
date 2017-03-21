package Bean;

import Dao.FamiliaDao;
import Dao.FamiliaDaoImplements;
import Model.Familia;
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
public class familiaBean implements Serializable{
    
    private Familia familia;
    private List<Familia> familias;
    private List<Familia> query;
    
    private Session session;
    private Transaction transaction;
    
    private List<SelectItem> SelectItemsColor;

    public familiaBean() {
        this.familia = new Familia();
    }
    
    public List<Familia> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            FamiliaDao daocolor = new FamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.familias = daocolor.verTodo(this.session);
            this.transaction.commit();
            return familias;
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
            FamiliaDao linkDao = new FamiliaDaoImplements();
            if (linkDao.verByFamiliaDifer(this.session, this.familia.getIdfamilia(), this.familia.getFamilia()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La Familia ya existe en DB."));
                familia = new Familia();
                return;
            }
            FamiliaDao daocolor = new FamiliaDaoImplements();
            daocolor.modificar(this.session, this.familia);
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

    public void cargarColorEditar(Integer idfamilia) {
        this.session = null;
        this.transaction = null;
        try {
            FamiliaDao daocolor = new FamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.familia = daocolor.verByCodigo(this.session, idfamilia);
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

    public void cargarColorEliminar(Integer idfamilia) {
        this.session = null;
        this.transaction = null;
        try {
            FamiliaDao daocolor = new FamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.familia = daocolor.verByCodigo(this.session, idfamilia);
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
            FamiliaDao linkDao = new FamiliaDaoImplements();
            if (linkDao.verByFamilia(this.session, this.familia.getFamilia()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La Familia ya esta registrado."));
                familia = new Familia();
                return;
            }
            Date d = new Date();
            this.familia.setCreated(d);
            linkDao.registrar(this.session, this.familia);
            this.transaction.commit();
            this.familia = new Familia();
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
            FamiliaDao daocolor = new FamiliaDaoImplements();
            daocolor.eliminar(this.session, this.familia);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.familia = new Familia();
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
    
    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
    
    public List<Familia> getFamilias() {
        this.session = null;
        this.transaction = null;
        try {
            FamiliaDao daocolor = new FamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            familias = daocolor.verTodo(session);            
            return familias;
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

    public void setColores(List<Familia> familias) {
        this.familias = familias;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }

    public List<Familia> filtrarColor(String name) {
        this.query = new ArrayList<Familia>();
        FamiliaDao colorDao = new FamiliaDaoImplements();
        List<Familia> tipos = colorDao.filtarTipoDos();
        for (Familia tipo : tipos) {
            if (tipo.getFamilia().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }
    
}
