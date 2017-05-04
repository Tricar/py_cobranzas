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

/**
 *
 * @author Ricardo
 */
@ManagedBean
@RequestScoped
public class TipodocumentBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Tipodocument tipodocument;
    private List<Tipodocument> tipodocuments;

    public TipodocumentBean() {
        this.tipodocument = new Tipodocument();
    }

    public List<Tipodocument> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            TipodocumentDao daoperfil = new TipodocumentDaoImpl();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.tipodocuments = daoperfil.verTodo(this.session);
            this.transaction.commit();
            return tipodocuments;
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

    public Tipodocument getTipodocument() {
        return tipodocument;
    }

    public void setTipodocument(Tipodocument tipodocument) {
        this.tipodocument = tipodocument;
    }

    public List<Tipodocument> getTipodocuments() {
        this.session = null;
        this.transaction = null;
        try {
            TipodocumentDaoImpl daoperfil = new TipodocumentDaoImpl();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.tipodocuments = daoperfil.verTodo(this.session);

            this.transaction.commit();

            return tipodocuments;

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

    public void setTipodocuments(List<Tipodocument> tipodocuments) {
        this.tipodocuments = tipodocuments;
    }

}
