/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.*;
import Model.Tipodocument;
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
public class TipodocumentBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Tipodocument tipoventa;
    private List<Tipodocument> tipoventas;

    public TipodocumentBean() {
        this.tipoventa = new Tipodocument();
    }

    public List<Tipodocument> verTodo() {

        this.session = null;
        this.transaction = null;

        try {

            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.tipoventas = daoperfil.verTodo(this.session);
            this.transaction.commit();
            return tipoventas;

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
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            daoperfil.registrar(this.session, this.tipoventa);
            this.transaction.commit();
            this.tipoventa = new Tipodocument();
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
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tipoventa = daoperfil.verByCodigo(this.session, codigoUsuario);
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
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.tipoventa = daoperfil.verByCodigo(this.session, codigoUsuario);
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

    public void insertarPerfil() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            if (daoperfil.verByDescripcion(this.session, this.tipoventa.getDescripcion()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Perfil ya esta registrado."));
                tipoventa = new Tipodocument();
                return;
            }
            daoperfil.registrar(this.session, this.tipoventa);
            this.transaction.commit();
            this.tipoventa = new Tipodocument();
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

            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();
            daoperfil.eliminar(this.session, this.tipoventa);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Perfil Correctamente."));

            this.tipoventa = new Tipodocument();

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

    public Tipodocument getTipoventa() {
        return tipoventa;
    }

    public void setTipoventa(Tipodocument tipoventa) {
        this.tipoventa = tipoventa;
    }

    public List<Tipodocument> getTipoventas() {
        this.session = null;
        this.transaction = null;
        try {
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.tipoventas = daoperfil.verTodo(this.session);

            this.transaction.commit();

            return tipoventas;

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

    public void setTipoventas(List<Tipodocument> tipoventas) {
        this.tipoventas = tipoventas;
    }

}
