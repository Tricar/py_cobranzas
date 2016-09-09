package Bean;

import Clases.Encrypt;
import Dao.UsuarioDaoImpl;
import Model.Usuario;
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

public class usuarioBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Usuario tusuario;
    private List<Usuario> listatusuario;
    private List<Usuario> listatusuariofiltrado;

    private String txtclavevacia;

    public Usuario getTusuario() {
        return tusuario;
    }

    public usuarioBean() {
        this.tusuario = new Usuario();
    }

    public List<Usuario> verTodo() {

        this.session = null;
        this.transaction = null;
        try {
            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.listatusuario = daotusuario.verTodo(this.session);
            this.transaction.commit();
            return listatusuario;
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
            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            if (daotusuario.verByUsuario(this.session, this.tusuario.getUsuario()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya existe en DB."));
                this.tusuario = new Usuario();
                return;
            }
            this.tusuario.setClave(Encrypt.sha512(this.tusuario.getClave()));
            daotusuario.registrar(this.session, this.tusuario);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.tusuario = new Usuario();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.tusuario = new Usuario();
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
            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            if (daotusuario.verByUsuarioDifer(this.session, this.tusuario.getIdusuario(), this.tusuario.getUsuario()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya Existe."));
                return;
            }            
            if(!this.txtclavevacia.equals("")){
                this.tusuario.setClave(Encrypt.sha512(this.txtclavevacia));
            }            
            daotusuario.modificar(this.session, this.tusuario);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.tusuario = new Usuario();
            
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.tusuario = new Usuario();
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
            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            daotusuario.eliminar(this.session, this.tusuario);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Usuario Correctamente."));
            this.tusuario = new Usuario();
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
            UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tusuario = usuarioDao.verByIdusuario(this.session, codigoUsuario);
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
            UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tusuario = usuarioDao.verByIdusuario(this.session, codigoUsuario);
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

    public void setTusuario(Usuario tusuario) {
        this.tusuario = tusuario;
    }

    public List<Usuario> getListatusuario() {        
        this.session = null;
        this.transaction = null;
        try {
            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.listatusuario = daotusuario.verTodo(this.session);
            this.transaction.commit();
            return listatusuario;
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

    public void setListatusuario(List<Usuario> listatusuario) {
        this.listatusuario = listatusuario;
    }

    public List<Usuario> getListatusuariofiltrado() {
        return listatusuariofiltrado;
    }

    public void setListatusuariofiltrado(List<Usuario> listatusuariofiltrado) {
        this.listatusuariofiltrado = listatusuariofiltrado;
    }

    public String getTxtclavevacia() {
        return txtclavevacia;
    }

    public void setTxtclavevacia(String txtclavevacia) {
        this.txtclavevacia = txtclavevacia;
    }
}
