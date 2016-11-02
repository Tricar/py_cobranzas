package Bean;

import Dao.PerfilDao;
import Dao.PerfilDaoImpl;
import Dao.PerfilmenuDao;
import Dao.PerfilmenuDaoImpl;
import Dao.PerfilsubmenuDao;
import Dao.PerfilsubmenuDaoImpl;
import Dao.SubmenuDao;
import Dao.SubmenuDaoImpl;
import Model.Perfil;
import Model.Perfilmenu;
import Model.Perfilsubmenu;
import Model.Submenu;
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

public class SubmenuBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Submenu submenu;
    private Submenu submenus;
    private Perfil perfil;
    private Perfilmenu perfilmenu;
    private Perfilmenu perfilmenus;
    private Perfilsubmenu perfilsubmenu;
    private List<Perfilmenu> listaperfilmenu;
    private List<Perfilsubmenu> listaperfilsubmenu;
    private List<Submenu> listasubmenu;
    private List<Perfil> listaperfil;
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
            Date d = new Date();
            this.submenu.setCreated(d);
            daotusuario.registrar(this.session, this.submenu);
            this.transaction.commit();
            insertarSubmenuMenu(submenu);
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

    public void insertarSubmenuMenu(Submenu submenu) {
        PerfilDao perfilDao = new PerfilDaoImpl();
        PerfilmenuDao perfilmenuDao = new PerfilmenuDaoImpl();
        PerfilsubmenuDao perfilsubmenuDao = new PerfilsubmenuDaoImpl();
        listaperfil = perfilDao.verTodos();
        perfilsubmenu = new Perfilsubmenu();
        for (int i = 0; i < listaperfil.size(); i++) {
            Perfil get = listaperfil.get(i);
            perfilmenu = perfilmenuDao.verTodos(get, submenu.getMenu());
            perfilsubmenu.setPerfilmenu(perfilmenu);
            perfilsubmenu.setSubmenu(submenu);
            perfilsubmenu.setEstado(Boolean.FALSE);
            perfilsubmenuDao.insertar(perfilsubmenu);
            perfilmenu = new Perfilmenu();
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Submenu ya Existe."));
                return;
            }

            daotusuario.modificar(this.session, this.submenu);
            this.transaction.commit();
            modificarSubmenuMenu(submenu);
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

    public void modificarSubmenuMenu(Submenu submenu) {
        PerfilDao perfilDao = new PerfilDaoImpl();
        PerfilmenuDao perfilmenuDao = new PerfilmenuDaoImpl();
        PerfilsubmenuDao perfilsubmenuDao = new PerfilsubmenuDaoImpl();
        SubmenuDao submenudao = new SubmenuDaoImpl();
        listaperfil = perfilDao.verTodos();
        perfilsubmenu = new Perfilsubmenu();
        for (int i = 0; i < listaperfil.size(); i++) {
            Perfil get = listaperfil.get(i);
            perfilmenu = perfilmenuDao.verTodos(get, submenu.getMenu());
            perfilmenus = perfilmenuDao.verPerfilMenu(get, submenu.getIdmenuanterior());
            perfilsubmenu = perfilsubmenuDao.verTodos(perfilmenus, submenu);
            perfilsubmenu.setPerfilmenu(perfilmenu);
            perfilsubmenuDao.modificarSolo(perfilsubmenu);
            perfilmenu = new Perfilmenu();
            perfilmenus = new Perfilmenu();
            perfilsubmenu = new Perfilsubmenu();
        }
        submenu.setIdmenuanterior(submenu.getMenu().getIdmenu());
        submenudao.modificarSolo(submenu);
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

    public Perfilsubmenu getPerfilsubmenu() {
        return perfilsubmenu;
    }

    public void setPerfilsubmenu(Perfilsubmenu perfilsubmenu) {
        this.perfilsubmenu = perfilsubmenu;
    }

    public Perfilmenu getPerfilmenu() {
        return perfilmenu;
    }

    public void setPerfilmenu(Perfilmenu perfilmenu) {
        this.perfilmenu = perfilmenu;
    }

    public List<Perfilmenu> getListaperfilmenu() {
        return listaperfilmenu;
    }

    public void setListaperfilmenu(List<Perfilmenu> listaperfilmenu) {
        this.listaperfilmenu = listaperfilmenu;
    }

    public List<Perfil> getListaperfil() {
        return listaperfil;
    }

    public void setListaperfil(List<Perfil> listaperfil) {
        this.listaperfil = listaperfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfilsubmenu> getListaperfilsubmenu() {
        return listaperfilsubmenu;
    }

    public void setListaperfilsubmenu(List<Perfilsubmenu> listaperfilsubmenu) {
        this.listaperfilsubmenu = listaperfilsubmenu;
    }

    public Submenu getSubmenus() {
        return submenus;
    }

    public void setSubmenus(Submenu submenus) {
        this.submenus = submenus;
    }

    public Perfilmenu getPerfilmenus() {
        return perfilmenus;
    }

    public void setPerfilmenus(Perfilmenu perfilmenus) {
        this.perfilmenus = perfilmenus;
    }

}
