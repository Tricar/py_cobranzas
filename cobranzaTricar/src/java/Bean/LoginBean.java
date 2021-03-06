package Bean;

import Clases.Encrypt;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.PerfilmenuDao;
import Dao.PerfilmenuDaoImpl;
import Dao.PerfilsubmenuDao;
import Dao.PerfilsubmenuDaoImpl;
import Dao.UsuarioDaoImpl;
import Model.Perfilmenu;
import Model.Perfilsubmenu;
import Model.Usuario;
import Persistencia.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import Persistencia.MyUtil;
import java.io.Serializable;
import java.util.List;
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
    private Integer ventasvx3;
    private Integer creditoxaprobar;
    private Integer creditoaprobado;
    private Integer creditoxapvx3;
    private Integer pagosxdia;
    private Integer pagosxmes;
    private Integer ventaRSxdia;
    private Integer ventaRSxmes;
    public List<Perfilsubmenu> perfilsubmenus;
    public List<Perfilmenu> perfilmenus;
    private HttpSession miSession;

    public LoginBean() {
        miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(900);        
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
            PerfilmenuDao pdao = new PerfilmenuDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            usuario = usuarioDao.verByUsuario(this.session, this.tusuario);
            ventasxdia = creditodao.ventasXdia(this.session);
            ventasxmes = creditodao.ventasXmes(this.session);
            ventasvx3 = creditodao.ventasvx3(this.session);
            creditoxaprobar = creditodao.creditoXaprobar(this.session);
            creditoaprobado = creditodao.creditoAprobado(this.session);
            creditoxapvx3 = creditodao.creditoxaprobvx3(this.session);
            pagosxdia = creditodao.pagosxdia(this.session);
            pagosxmes = creditodao.pagosxmes(this.session);           
            ventaRSxmes = creditodao.ventaRSXmes(this.session);
            ventaRSxdia = creditodao.ventaRSXdia(this.session); 
            if (usuario != null) {
                if (usuario.getClave().equals(Encrypt.sha512(this.clave))) {
                    loggedIn = true;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.tusuario);                    
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido ", usuario.getAnexo().getNombre());
                    ruta = MyUtil.basepathlogin() + "views/index.xhtml";
                    this.perfilmenus = pdao.verTodoByPerfilmenu(this.session, usuario.getPerfil().getIdperfil(), Boolean.TRUE);
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
    
    public void actualizarxAprobar (){
        this.session = null;
        this.transaction = null;
        try {
            CreditoDao creditodao = new CreditoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();            
            creditoxaprobar = creditodao.creditoXaprobar(this.session);            
            this.transaction.commit();            
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
    
    public void actualizarxDespachar (){
        this.session = null;
        this.transaction = null;
        try {
            CreditoDao creditodao = new CreditoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();            
            creditoaprobado = creditodao.creditoAprobado(this.session);            
            this.transaction.commit();            
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
    
    public void actualizarVentasxDia (){
        this.session = null;
        this.transaction = null;
        try {
            CreditoDao creditodao = new CreditoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();            
            ventasxdia = creditodao.ventasXdia(this.session);            
            this.transaction.commit();            
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
    
    public void actualizarVentasxMes (){
        this.session = null;
        this.transaction = null;
        try {
            CreditoDao creditodao = new CreditoDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();            
            ventasxmes = creditodao.ventasXmes(this.session);            
            this.transaction.commit();            
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
    
    public List<Perfilmenu> cargarPerfilmenus(Integer perfil){
        this.session = null;
        this.transaction = null;

        try {
            PerfilmenuDao dao = new PerfilmenuDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.perfilmenus = dao.verTodoByPerfilmenu(this.session, perfil, Boolean.TRUE);
            this.transaction.commit();
            return perfilmenus;

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
    
    public List<Perfilsubmenu> cargarPerfilsubmenus(Integer menu){
        this.session = null;
        this.transaction = null;

        try {
            PerfilsubmenuDao dao = new PerfilsubmenuDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.perfilsubmenus = dao.verTodoByPerfilsubmenu(this.session, menu);
            this.transaction.commit();
            return perfilsubmenus;

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

    public String url( String urls ) {
        System.out.println("url"+urls);
        return urls;
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

    public List<Perfilmenu> getPerfilmenus() {
        return perfilmenus;
    }

    public void setPerfilmenus(List<Perfilmenu> perfilmenus) {
        this.perfilmenus = perfilmenus;
    }

    public Integer getVentasvx3() {
        return ventasvx3;
    }

    public void setVentasvx3(Integer ventasvx3) {
        this.ventasvx3 = ventasvx3;
    }

    public Integer getCreditoxapvx3() {
        return creditoxapvx3;
    }

    public void setCreditoxapvx3(Integer creditoxapvx3) {
        this.creditoxapvx3 = creditoxapvx3;
    }

    public Integer getVentaRSxdia() {
        return ventaRSxdia;
    }

    public void setVentaRSxdia(Integer ventaRSxdia) {
        this.ventaRSxdia = ventaRSxdia;
    }

    public Integer getVentaRSxmes() {
        return ventaRSxmes;
    }

    public void setVentaRSxmes(Integer ventaRSxmes) {
        this.ventaRSxmes = ventaRSxmes;
    }

    public HttpSession getMiSession() {
        return miSession;
    }

    public void setMiSession(HttpSession miSession) {
        this.miSession = miSession;
    }

}
