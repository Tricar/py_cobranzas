/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.*;
import Model.*;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class PerfilBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Perfil perfil;

    private List<Perfil> perfiles;
    private List<Menu> menus;
    private List<Submenu> submenus;
    private Menu menu;
    private Submenu submenu;
    private Perfilsubmenu perfilsubmenu;
    private Perfilmenu perfilmenu;
    public List<Perfilmenu> perfilmenus;
    public List<Perfilsubmenu> perfilsubmenus;

    public PerfilBean() {
        this.perfil = new Perfil();
    }

    public List<Perfil> verTodo() {

        this.session = null;
        this.transaction = null;

        try {

            PerfilDaoImpl daoperfil = new PerfilDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.perfiles = daoperfil.verTodo(this.session);
            this.transaction.commit();
            return perfiles;

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

    public void actualizarPerfil() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            MenuDao menudao = new MenuDaoImpl();
            SubmenuDao submenudao = new SubmenuDaoImpl();
            menudao.registrar(this.session, this.menu);
            submenudao.registrar(this.session, this.submenu);
            this.transaction.commit();
            this.menu = new Menu();
            this.submenu = new Submenu();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
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

    public void cargarPerfilEditar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {
            PerfilDaoImpl perfilDao = new PerfilDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.perfil = perfilDao.verByCodigo(this.session, codigoUsuario);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEditarPerfil:panelEditarPerfil");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarPerfil').show()");
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

    public void cargarPerfilEliminar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {
            PerfilDaoImpl perfilDao = new PerfilDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.perfil = perfilDao.verByCodigo(this.session, codigoUsuario);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEliminarPerfil");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarPerfil').show()");
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

    public void cargarMenuAsignar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            PerfilDao perfilDao = new PerfilDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.perfil = perfilDao.verByCodigo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmAsignarMenu");
            RequestContext.getCurrentInstance().execute("PF('dialogoAsignarMenu').show()");

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

    public void insertarPerfil() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            PerfilDao linkDao = new PerfilDaoImpl();
            if (linkDao.verByDescripcion(this.session, this.perfil.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Perfil ya esta registrado."));
                perfil = new Perfil();
                return;
            }
            Date d = new Date();
            this.perfil.setCreated(d);
            linkDao.registrar(this.session, this.perfil);
            this.transaction.commit();
            insertarperfilmenu(perfil);
            this.perfil = new Perfil();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

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

    public void insertarperfilmenu(Perfil perfil) {
        MenuDao menuDao = new MenuDaoImpl();
        PerfilmenuDao perfilmenuDao = new PerfilmenuDaoImpl();
        PerfilsubmenuDao perfsubmendao = new PerfilsubmenuDaoImpl();
        SubmenuDao submendao = new SubmenuDaoImpl();
        menus = menuDao.verTodos();
        perfilmenu = new Perfilmenu();
        perfilsubmenu = new Perfilsubmenu();
        for (int i = 0; i < menus.size(); i++) {
            Menu get = menus.get(i);
            perfilmenu.setMenu(get);
            perfilmenu.setPerfil(perfil);
            perfilmenu.setEstado(Boolean.FALSE);
            perfilmenuDao.insertar(perfilmenu);
            submenus = submendao.verTodosxId(get);
            for (int j = 0; j < submenus.size(); j++) {
                Submenu get1 = submenus.get(j);
                perfilsubmenu.setPerfilmenu(perfilmenu);
                perfilsubmenu.setSubmenu(get1);
                perfilsubmenu.setEstado(Boolean.FALSE);
                perfsubmendao.insertar(perfilsubmenu);
                perfilsubmenu = new Perfilsubmenu();
            }
            submenus = new ArrayList();
            perfilmenu = new Perfilmenu();
        }

    }

    public void eliminarPerfil() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            PerfilDaoImpl perfilDao = new PerfilDaoImpl();
            perfilDao.eliminarPerfil(this.session, this.perfil);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Perfil Correctamente."));

            this.perfil = new Perfil();

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

    public List<Perfilmenu> cargarPerfilmenus(Integer perfil) {
        this.session = null;
        this.transaction = null;

        try {
            PerfilmenuDao dao = new PerfilmenuDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.perfilmenus = dao.verTodoByPerfilmenu(this.session, perfil);
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

    public List<Perfilsubmenu> cargarPerfilsubmenus(Integer menu) {
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfiles() {
        this.session = null;
        this.transaction = null;
        try {
            PerfilDaoImpl daoperfil = new PerfilDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.perfiles = daoperfil.verTodo(this.session);

            this.transaction.commit();

            return perfiles;

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

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Menu> getMenus() {
        this.session = null;
        this.transaction = null;
        try {
            MenuDao daoperfil = new MenuDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.menus = daoperfil.verTodo(this.session);

            this.transaction.commit();

            return menus;

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

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Submenu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Submenu submenu) {
        this.submenu = submenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    public List<Submenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(List<Submenu> submenus) {
        this.submenus = submenus;
    }

}
