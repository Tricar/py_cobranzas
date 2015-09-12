/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.PerfilDaoImpl;
import Model.Perfil;
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
            
            PerfilDaoImpl linkDao = new PerfilDaoImpl();
            if (linkDao.verByPerfilDifer(this.session, this.perfil.getIdperfil(), this.perfil.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Perfil ya existe en DB."));
                perfil = new Perfil();
                return;
            }

            if (perfil.getSisPer() == true || perfil.getSisUsu() == true) {
                perfil.setSistema(true);
            } else if (perfil.getSisPer() == false && perfil.getSisUsu() == false) {
                perfil.setSistema(false);
            }

            if (perfil.getManArt() == true || perfil.getManCli() == true || perfil.getManCol() == true
                    || perfil.getManMod() == true) {
                perfil.setMante(true);
            } else if (perfil.getManArt() == false && perfil.getManCli() == false && perfil.getManCol() == false
                    &&perfil.getManMod() == false) {
                perfil.setMante(false);
            }

            if (perfil.getVenLis() == true || perfil.getVenReg() == true) {
                perfil.setVenta(true);
            } else if (perfil.getVenLis() == false && perfil.getVenReg() == false) {
                perfil.setVenta(false);
            }

            PerfilDaoImpl perfilDao = new PerfilDaoImpl();
            perfilDao.modificar(this.session, this.perfil);

            this.transaction.commit();

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

            PerfilDaoImpl perfilDao = new PerfilDaoImpl();

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

            PerfilDaoImpl linkDao = new PerfilDaoImpl();
            if (linkDao.verByDescripcion(this.session, this.perfil.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Perfil ya esta registrado."));
                perfil = new Perfil();
                return;
            }

            perfil.setSistema(false);
            perfil.setSisPer(false);
            perfil.setSisUsu(false);
            perfil.setMante(false);
            perfil.setManArt(false);
            perfil.setManCli(false);
            perfil.setManCol(false);
            perfil.setManMod(false);
            perfil.setVenta(false);
            perfil.setVenLis(false);
            perfil.setVenReg(false);
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

}
