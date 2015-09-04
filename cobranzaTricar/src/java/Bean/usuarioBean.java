package Bean;

import Clases.Encrypt;
import Dao.UsuarioDaoImpl;
import Model.Usuario;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@RequestScoped

public class usuarioBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Usuario tusuario;
    private List<Usuario> listatusuario;
    private List<Usuario> listatusuariofiltrado;
    
    private LoginBean loginbeans;

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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Correo ya existe en DB."));
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
            if (daotusuario.verByUsuarioDifer(this.session, this.tusuario.getUsuario(), this.tusuario.getClave()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya Existe."));
                return;
            }
            daotusuario.modificar(this.session, this.tusuario);

            this.transaction.commit();
            
            this.loginbeans.setTusuario(this.tusuario.getUsuario());

            HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpsession.setAttribute("correoElectronico", this.tusuario.getUsuario());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));

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

    public void eliminar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            UsuarioDaoImpl daotusuario = new UsuarioDaoImpl();
            daotusuario.eliminar(this.session, this.tusuario);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Perfil Correctamente."));

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

    public void setTusuario(Usuario tusuario) {
        this.tusuario = tusuario;
    }

    public List<Usuario> getListatusuario() {
        return listatusuario;
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
}
