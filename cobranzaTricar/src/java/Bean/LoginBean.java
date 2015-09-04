package Bean;

import Dao.UsuarioDaoImpl;
import Model.Usuario;
import Persistencia.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import Persistencia.MyUtil;
import java.io.Serializable;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Session session;
    private Transaction transaction;
    
    private Usuario usuario;

    private String tusuario;
    private String clave;

    public LoginBean() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(3000);
    }

    public void login(ActionEvent actionEvent) {
        this.session = null;
        this.transaction = null;
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean loggedIn;
        String ruta = "";

        try {
            UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            usuario = usuarioDao.verByUsuario(this.session, this.tusuario);

            if (usuario != null) {
                loggedIn = true;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.tusuario);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.tusuario);
                ruta = MyUtil.basepathlogin() + "views/index.xhtml";
            } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y/o Clave es incorrecta");
            }

            this.transaction.commit();

            this.tusuario = null;
            this.clave = null;

            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("ruta", ruta);

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

    public void logout() {
        String ruta = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facescontext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) facescontext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }

    public String getTusuario() {
        return tusuario;
    }

    public void setTusuario(String tusuario) {
        this.tusuario = tusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
