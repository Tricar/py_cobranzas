package Bean;

import Dao.SubfamiliaDao;
import Dao.SubfamiliaDaoImplements;
import Model.Subfamilia;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped

public class SubfamiliaBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Subfamilia subfamilia;
    private Subfamilia subfamilias;
    private List<Subfamilia> listasubfamilia;
    private List<Subfamilia> listasubfamiliafiltrado;

    public Subfamilia getSubfamilia() {
        return subfamilia;
    }

    public SubfamiliaBean() {
        this.subfamilia = new Subfamilia();
    }

    public List<Subfamilia> verTodo() {

        this.session = null;
        this.transaction = null;

        try {
            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listasubfamilia = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listasubfamilia;

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
            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();
            if (daotusuario.verBySubfamilia(this.session, this.subfamilia.getSubfamilia()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "La Subfamilia ya existe en DB."));
                this.subfamilia = new Subfamilia();
                return;
            }
            Date d = new Date();
            this.subfamilia.setCreated(d);
            daotusuario.registrar(this.session, this.subfamilia);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.subfamilia = new Subfamilia();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.subfamilia = new Subfamilia();
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

            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();

            if (daotusuario.verBySubfamiliaD(this.session, this.subfamilia.getIdsubfamilia(), this.subfamilia.getSubfamilia()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Submenu ya Existe."));
                return;
            }

            daotusuario.modificar(this.session, this.subfamilia);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.subfamilia = new Subfamilia();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.subfamilia = new Subfamilia();
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

            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();
            daotusuario.eliminar(this.session, this.subfamilia);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Usuario Correctamente."));

            this.subfamilia = new Subfamilia();

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

            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.subfamilia = daotusuario.verByCodigo(this.session, codigoUsuario);

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

            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.subfamilia = daotusuario.verByCodigo(this.session, codigoUsuario);

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

    public void setTusuario(Subfamilia subfamilia) {
        this.subfamilia = subfamilia;
    }

    public List<Subfamilia> getListatusuario() {
        this.session = null;
        this.transaction = null;

        try {
            SubfamiliaDao daotusuario = new SubfamiliaDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listasubfamilia = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listasubfamilia;

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

    public void setListasubfamilia(List<Subfamilia> listasubfamilia) {
        this.listasubfamilia = listasubfamilia;
    }

    public List<Subfamilia> getListasubfamiliafiltrado() {
        return listasubfamiliafiltrado;
    }

    public void setListatusuariofiltrado(List<Subfamilia> listasubfamiliafiltrado) {
        this.listasubfamiliafiltrado = listasubfamiliafiltrado;
    }

    public Subfamilia getSubfamilias() {
        return subfamilias;
    }

    public void setSubfamilias(Subfamilia subfamilias) {
        this.subfamilias = subfamilias;
    }

}
