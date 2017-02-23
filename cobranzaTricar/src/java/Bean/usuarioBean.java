package Bean;

import Clases.Encrypt;
import Dao.UsuarioDaoImpl;
import Model.Usuario;
import Persistencia.HibernateUtil;
import java.io.Serializable;
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

public class usuarioBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Usuario tusuario;
    private List<Usuario> listatusuario;
    private List<Usuario> listatusuariofiltrado;
    private String clave;
    private String clave2;

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
            if (this.clave.equals("") || this.clave2.equals("")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Clave no puede ser vacío");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            if (clave.equals(clave2)) {
                this.tusuario.setClave(Encrypt.sha512(clave));
                daotusuario.registrar(this.session, this.tusuario);
                this.transaction.commit();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se registró al usuario.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                this.tusuario = new Usuario();
                RequestContext.getCurrentInstance().update("formMostrar");
                RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

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
            if (this.clave.equals("") || this.clave2.equals("")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Clave no puede ser vacío");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            if (clave.equals(clave2)) {
                this.tusuario.setClave(Encrypt.sha512(clave));
                daotusuario.modificar(this.session, this.tusuario);
                this.transaction.commit();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se modificaron los datos del usuario");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                this.tusuario = new Usuario();
                RequestContext.getCurrentInstance().update("formMostrar");
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarUsuario').hide()");
            }else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }
}
