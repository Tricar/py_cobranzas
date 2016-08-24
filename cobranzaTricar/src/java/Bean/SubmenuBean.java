package Bean;

import Dao.SubmenuDao;
import Dao.SubmenuDaoImpl;
import Model.Submenu;
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

public class SubmenuBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Submenu submenu;
    private List<Submenu> listasubmenu;
    private List<Submenu> listasubmenufiltrado;

    public Submenu getSubmenu() {
        return submenu;
    }

    public SubmenuBean() {
        this.submenu = new Submenu();
    }

    public List<Submenu> verTodo() {

        this.session = null;
        this.transaction = null;

        try {
            SubmenuDao daotusuario = new SubmenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listasubmenu = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listasubmenu;

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

            SubmenuDao daotusuario = new SubmenuDaoImpl();
            if (daotusuario.verByDescripcion(this.session, this.submenu.getSubmenu()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya existe en DB."));
                this.submenu = new Submenu();
                return;
            }
            
            daotusuario.registrar(this.session, this.submenu);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

            this.submenu = new Submenu();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.submenu = new Submenu();
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

            SubmenuDao daotusuario = new SubmenuDaoImpl();

            if (daotusuario.verByDifer(this.session, this.submenu.getIdsubmenu(), this.submenu.getSubmenu()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya Existe."));
                return;
            }
            
            daotusuario.modificar(this.session, this.submenu);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.submenu = new Submenu();
            
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.submenu = new Submenu();
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

            SubmenuDao daotusuario = new SubmenuDaoImpl();
            daotusuario.eliminar(this.session, this.submenu);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Usuario Correctamente."));

            this.submenu = new Submenu();

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

    public void cargarUsuarioEditar(int codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            SubmenuDao daotusuario = new SubmenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.submenu = daotusuario.verByCodigo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditarUsuario:panelEditarUsuario");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarUsuario').show()");

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

    public void cargarUsuarioEliminar(int codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            SubmenuDao daotusuario = new SubmenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.submenu = daotusuario.verByCodigo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarUsuario");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarUsuario').show()");

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

    public void setTusuario(Submenu submenu) {
        this.submenu = submenu;
    }

    public List<Submenu> getListatusuario() {        
        this.session = null;
        this.transaction = null;

        try {
            SubmenuDao daotusuario = new SubmenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listasubmenu = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listasubmenu;

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

    public void setListasubmenu(List<Submenu> listasubmenu) {
        this.listasubmenu = listasubmenu;
    }

    public List<Submenu> getListasubmenufiltrado() {
        return listasubmenufiltrado;
    }

    public void setListatusuariofiltrado(List<Submenu> listasubmenufiltrado) {
        this.listasubmenufiltrado = listasubmenufiltrado;
    }
}
