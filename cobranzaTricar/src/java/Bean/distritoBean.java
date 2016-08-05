package Bean;

import Clases.Encrypt;
import Dao.DistritoDaoImpl;
import Model.Distrito;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped

public class distritoBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Distrito tdistrito;
    private List<Distrito> listatdistrito;
    private List<Distrito> listatdistritofiltrado;

    public Distrito getTdistrito() {
        return tdistrito;
    }

    public distritoBean() {
        this.tdistrito = new Distrito();
    }

    public List<Distrito> verTodo() {

        this.session = null;
        this.transaction = null;

        try {
            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listatdistrito = daotdistrito.verTodo(this.session);

            this.transaction.commit();

            return listatdistrito;

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

    public void registrar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();
            if (daotdistrito.verByDistrito(this.session, this.tdistrito.getDistrito()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Distrito ya existe en DB."));
                this.tdistrito = new Distrito();
                return;
            }
            
            daotdistrito.registrar(this.session, this.tdistrito);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

            this.tdistrito = new Distrito();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.tdistrito = new Distrito();
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

            DistritoDaoImpl daotusuario = new DistritoDaoImpl();

            if (daotusuario.verByDistritoDifer(this.session, this.tdistrito.getIddistrito(), this.tdistrito.getDistrito()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya Existe."));
                return;
            }
            
            daotusuario.modificar(this.session, this.tdistrito);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.tdistrito = new Distrito();
            
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.tdistrito = new Distrito();
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

            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();
            daotdistrito.eliminar(this.session, this.tdistrito);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Usuario Correctamente."));

            this.tdistrito = new Distrito();

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

    public void cargarDistritoEditar(int id) {
        this.session = null;
        this.transaction = null;

        try {

            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.tdistrito = daotdistrito.verById(this.session, id);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditarDistrito:panelEditarDistrito");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarDistrito').show()");

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

    public void cargarDistritoEliminar(int id) {
        this.session = null;
        this.transaction = null;

        try {

            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.tdistrito = daotdistrito.verById(this.session, id);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarDistrito");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarDistrito').show()");

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

    public void setTdistrito(Distrito tdistrito) {
        this.tdistrito = tdistrito;
    }

    public List<Distrito> getListatdistrito() {        
        this.session = null;
        this.transaction = null;

        try {
            DistritoDaoImpl daotdistrito = new DistritoDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listatdistrito = daotdistrito.verTodo(this.session);

            this.transaction.commit();

            return listatdistrito;

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

    public void setListatdistrito(List<Distrito> listatdistrito) {
        this.listatdistrito = listatdistrito;
    }

    public List<Distrito> getListatusuariofiltrado() {
        return listatdistritofiltrado;
    }

    public void setListatdistritofiltrado(List<Distrito> listatdistritofiltrado) {
        this.listatdistritofiltrado = listatdistritofiltrado;
    }
    
}
