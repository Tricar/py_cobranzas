/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.MenuDao;
import Dao.MenuDaoImpl;
import Dao.PerfilDao;
import Dao.PerfilDaoImpl;
import Dao.PerfilmenuDao;
import Dao.PerfilmenuDaoImpl;
import Dao.SubmenuDao;
import Dao.SubmenuDaoImpl;
import Model.Menu;
import Model.Perfil;
import Model.Perfilmenu;
import Model.Perfilsubmenu;
import Model.Submenu;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@RequestScoped
public class PerfilBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Perfil perfil;

    private List<Perfil> perfiles;
    private List<Menu> menus;
    private Menu menu;
    private Submenu submenu;
    private Perfilsubmenu perfilsubmenu;
    private Perfilmenu perfilmenu;
    private String[] selecsubmenus;
    private List<String> submenus;

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
            linkDao.registrar(this.session, this.perfil);
            this.transaction.commit();
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
    
    public String[] cargarSubmenus(Integer menu){
            SubmenuDao dao = new SubmenuDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            selecsubmenus = mostrarReqsParaModificar(menu);
            this.transaction.commit();
            return selecsubmenus;
    }
    
    public String[] mostrarReqsParaModificar(Integer menu) {
        List<String> where = new ArrayList<String>();
        SubmenuDao daosubmenu = new SubmenuDaoImpl();
        PerfilmenuDao daoperfilsubmenu = new PerfilmenuDaoImpl();
        submenu = daosubmenu.mostrarRequisitosXCred(menu);
        perfilmenu = daoperfilsubmenu.mostrarRequisitosXCred(menu);
        if (submenu.getIdsubmenu()!= null) {
            where.add(submenu.getSubmenu());
        }
        String[] selectedReqs = new String[where.size()];
        where.toArray(selectedReqs);
        return selectedReqs;
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

    public String[] getSelecsubmenus() {
        return selecsubmenus;
    }

    public void setSelecsubmenus(String[] selecsubmenus) {
        this.selecsubmenus = selecsubmenus;
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

}
