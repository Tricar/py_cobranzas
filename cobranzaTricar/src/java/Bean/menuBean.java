package Bean;

import Dao.MenuDao;
import Dao.MenuDaoImpl;
import Dao.PerfilDao;
import Dao.PerfilDaoImpl;
import Dao.PerfilmenuDao;
import Dao.PerfilmenuDaoImpl;
import Model.Menu;
import Model.Perfil;
import Model.Perfilmenu;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
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

public class menuBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Menu menu;
    private Perfilmenu perfilmenu;
    private List<Menu> listamenu;
    private List<Perfil> listaperfil;
    private List<Menu> listamenufiltrado;

    public Menu getMenu() {
        return menu;
    }

    public menuBean() {
        this.menu = new Menu();
    }

    public List<Menu> verTodo() {

        this.session = null;
        this.transaction = null;

        try {
            MenuDao daotusuario = new MenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listamenu = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listamenu;

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

            MenuDao daotusuario = new MenuDaoImpl();
            if (daotusuario.verByDescripcion(this.session, this.menu.getMenu()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya existe en DB."));
                this.menu = new Menu();
                return;
            }
            Date d = new Date();
            this.menu.setCreated(d);
            daotusuario.registrar(this.session, this.menu);

            this.transaction.commit();
            insertarMenuPerfil(menu);
            this.menu = new Menu();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

            this.menu = new Menu();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.menu = new Menu();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void insertarMenuPerfil(Menu menu) {
        PerfilDao perfilDao = new PerfilDaoImpl();
        PerfilmenuDao perfilmenuDao = new PerfilmenuDaoImpl();
        listaperfil = perfilDao.verTodos();
        perfilmenu = new Perfilmenu();
        for (int i = 0; i < listaperfil.size(); i++) {
            Perfil get = listaperfil.get(i);
            perfilmenu.setPerfil(get);
            perfilmenu.setMenu(menu);
            perfilmenu.setEstado(Boolean.FALSE);
            perfilmenuDao.insertar(perfilmenu);
            perfilmenu = new Perfilmenu();
        }

    }

    public void modificar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            MenuDao daotusuario = new MenuDaoImpl();

            if (daotusuario.verByDifer(this.session, this.menu.getIdmenu(), this.menu.getMenu()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Usuario ya Existe."));
                return;
            }

            daotusuario.modificar(this.session, this.menu);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.menu = new Menu();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.menu = new Menu();
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

            MenuDao daotusuario = new MenuDaoImpl();
            daotusuario.eliminar(this.session, this.menu);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Usuario Correctamente."));

            this.menu = new Menu();

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

            MenuDao daotusuario = new MenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.menu = daotusuario.verByCodigo(this.session, codigoUsuario);

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

            MenuDao daotusuario = new MenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.menu = daotusuario.verByCodigo(this.session, codigoUsuario);

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

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getListamenu() {
        this.session = null;
        this.transaction = null;

        try {
            MenuDao daotusuario = new MenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.listamenu = daotusuario.verTodo(this.session);

            this.transaction.commit();

            return listamenu;

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

    public void setListamenu(List<Menu> listamenu) {
        this.listamenu = listamenu;
    }

    public List<Menu> getListamenufiltrado() {
        return listamenufiltrado;
    }

    public void setListamenufiltrado(List<Menu> listamenufiltrado) {
        this.listamenufiltrado = listamenufiltrado;
    }

    public List<Perfil> getListaperfil() {
        return listaperfil;
    }

    public void setListaperfil(List<Perfil> listaperfil) {
        this.listaperfil = listaperfil;
    }

}
