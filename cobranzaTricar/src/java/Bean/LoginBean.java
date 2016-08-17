package Bean;

import Clases.Encrypt;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
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

@ManagedBean(name = "loginbean")
@SessionScoped
public class LoginBean implements Serializable {

    private Session session;
    private Transaction transaction;
    public Usuario usuario;
    private String tusuario;
    private String clave;
    private Integer ventasxdia;
    private Integer ventasxmes;
    private Integer creditoxaprobar;
    private Integer creditoaprobado;
    private Integer pagosxdia;
    private Integer pagosxmes;

    public LoginBean() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(300);
    }

    public void sesionExpired() {
        String ruta = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facescontext.getExternalContext().getSession(false);
        sesion.invalidate();
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }

    public void login(ActionEvent actionEvent) {
        this.session = null;
        this.transaction = null;
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        String ruta = "";
        try {
            UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
            CreditoDao creditodao = new CreditoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            usuario = usuarioDao.verByUsuario(this.session, this.tusuario);
            ventasxdia = creditodao.ventasXdia(this.session);
            ventasxmes = creditodao.ventasXmes(this.session);
            creditoxaprobar = creditodao.creditoXaprobar(this.session);
            creditoaprobado = creditodao.creditoAprobado(this.session);
            pagosxdia = creditodao.pagosxdia(this.session);
            pagosxmes = creditodao.pagosxmes(this.session);
            if (usuario != null) {
                if (usuario.getClave().equals(Encrypt.sha512(this.clave))) {
                    loggedIn = true;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.tusuario);
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.tusuario);
                    ruta = MyUtil.basepathlogin() + "views/index.xhtml";
                }
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

    public String inicio() {
        this.session = null;
        this.transaction = null;
        CreditoDao ventasxdiadao = new CreditoDaoImp();
        ventasxdia = ventasxdiadao.ventasXdia(this.session);
        return "/views/index";
    }

    public String empleado() {
        return "/sistema/empleado";
    }

    public String vendedor() {
        return "/sistema/vendedor";
    }

    public String usuarios() {
        return "/sistema/usuario";
    }

    public String perfil() {
        return "/sistema/perfil";
    }

    public String distrito() {
        return "/sistema/distrito";
    }

    public String cliente() {
        return "/mantenimiento/cliente";
    }

    public String aval() {
        return "/mantenimiento/aval";
    }

    public String articulo() {
        return "/mantenimiento/articulo";
    }

    public String modelo() {
        return "/mantenimiento/modarticulo";
    }

    public String color() {
        return "/mantenimiento/colorart";
    }

    public String porforma() {
        return "/cotiza/index";
    }

    public String credito() {
        return "/venta/index";
    }

    public String pago() {
        return "/venta/listarv";
    }

    public String mensual() {
        return "/reportes/mensual";
    }

    public String moroso() {
        //return "/reportes/moroso";
        return "#";
    }

    public String ingreso() {
        return "/mantenimiento/ingreso";
    }

    public String caja() {
        return "/sistema/caja";
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

    public Integer getVentasxdia() {
        return ventasxdia;
    }

    public void setVentasxdia(Integer ventasxdia) {
        this.ventasxdia = ventasxdia;
    }

    public Integer getVentasxmes() {
        return ventasxmes;
    }

    public void setVentasxmes(Integer ventasxmes) {
        this.ventasxmes = ventasxmes;
    }

    public Integer getCreditoxaprobar() {
        return creditoxaprobar;
    }

    public void setCreditoxaprobar(Integer creditoxaprobar) {
        this.creditoxaprobar = creditoxaprobar;
    }

    public Integer getCreditoaprobado() {
        return creditoaprobado;
    }

    public void setCreditoaprobado(Integer creditoaprobado) {
        this.creditoaprobado = creditoaprobado;
    }

    public Integer getPagosxdia() {
        return pagosxdia;
    }

    public void setPagosxdia(Integer pagosxdia) {
        this.pagosxdia = pagosxdia;
    }

    public Integer getPagosxmes() {
        return pagosxmes;
    }

    public void setPagosxmes(Integer pagosxmes) {
        this.pagosxmes = pagosxmes;
    }

}
