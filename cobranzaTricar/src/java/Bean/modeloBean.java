package Bean;

import Dao.ModeloDaoImplements;
import Model.Modelo;
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
public class modeloBean implements Serializable {

    private List<SelectItem> SelectItemsmodelo;
    private Session session;
    private Transaction transaction;

    private Modelo model;
    private List<Modelo> modelos;

    public modeloBean() {
        this.model = new Modelo();
    }

    public List<Modelo> verTodo() {

        this.session = null;
        this.transaction = null;

        try {
            ModeloDaoImplements daomodelo = new ModeloDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.modelos = daomodelo.verTodo(this.session);

            this.transaction.commit();

            return modelos;

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

            ModeloDaoImplements linkDao = new ModeloDaoImplements();
            if (linkDao.verByModeloDifer(this.session, this.model.getIdmodelo(), this.model.getModelo()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Modelo ya existe en DB."));
                model = new Modelo();
                return;
            }

            ModeloDaoImplements daomodelo = new ModeloDaoImplements();
            daomodelo.modificar(this.session, this.model);

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

    public void cargarModeloEditar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            ModeloDaoImplements daomodelo = new ModeloDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.model = daomodelo.verByCodigo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditarModelo:panelEditarModelo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarModelo').show()");

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

    public void cargarModeloEliminar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            ModeloDaoImplements daomodelo = new ModeloDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.model = daomodelo.verByCodigo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarModelo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarModelo').show()");

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

            ModeloDaoImplements linkDao = new ModeloDaoImplements();
            if (linkDao.verByModelo(this.session, this.model.getModelo()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Modelo ya esta registrado."));
                model = new Modelo();
                return;
            }

            Date d = new Date();
            this.model.setFecreg(d);
            linkDao.registrar(this.session, this.model);

            this.transaction.commit();

            this.model = new Modelo();

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

            ModeloDaoImplements daomodelo = new ModeloDaoImplements();
            daomodelo.eliminar(this.session, this.model);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Modelo Correctamente."));

            this.model = new Modelo();

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

    public void setSelectItemsmodelo(List<SelectItem> SelectItemsmodelo) {
        this.SelectItemsmodelo = SelectItemsmodelo;
    }

    public List<SelectItem> getSelectItemsmodelo() {
        return SelectItemsmodelo;
    }

    public Modelo getModel() {
        return model;
    }

    public void setModel(Modelo model) {
        this.model = model;
    }

    public List<Modelo> getModelos() {        
        this.session = null;
        this.transaction = null;
        try {
            ModeloDaoImplements daomodelo = new ModeloDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            modelos = daomodelo.verTodo(session);
            
            return modelos;

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

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}
