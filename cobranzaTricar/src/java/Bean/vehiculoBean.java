package Bean;

import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Vehiculo;
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

@ManagedBean
@SessionScoped

public class vehiculoBean implements Serializable {

    private Session session;
    private Transaction transaction;

    public Vehiculo vehiculo;
    public List<Vehiculo> vehiculos;
    private List<Vehiculo> query;

    public vehiculoBean() {
        this.vehiculo = new Vehiculo();
    }

    public void registrar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            VehiculoDaoImplements dao = new VehiculoDaoImplements();
            if (dao.verBySerie(this.session, this.vehiculo.getSerie()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Articulo ya existe en DB."));
                this.vehiculo = new Vehiculo();
                return;
            }

            Date d = new Date();
            vehiculo.setFechareg(d);
            dao.registrar(this.session, this.vehiculo);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));

            this.vehiculo = new Vehiculo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.vehiculo = new Vehiculo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            VehiculoDaoImplements dao = new VehiculoDaoImplements();

            if (dao.verBySerieDifer(this.session, this.vehiculo.getIdvehiculo(), this.vehiculo.getSerie()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Articulo ya Existe."));
                return;
            }

            dao.modificar(this.session, this.vehiculo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.vehiculo = new Vehiculo();

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.vehiculo = new Vehiculo();
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

            VehiculoDaoImplements dao = new VehiculoDaoImplements();
            dao.eliminar(this.session, this.vehiculo);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Articulo Correctamente."));

            this.vehiculo = new Vehiculo();

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

    public void cargarArticuloEditar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            VehiculoDaoImplements dao = new VehiculoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.vehiculo = dao.verByIdvehiculo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditarArticulo:panelEditarArticulo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarArticulo').show()");

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

    public void cargarArticuloEliminar(Integer codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            VehiculoDaoImplements dao = new VehiculoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            this.vehiculo = dao.verByIdvehiculo(this.session, codigoUsuario);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarArticulo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarArticulo').show()");

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

    public Vehiculo getVehiculo() {
        if (vehiculo == null) {
            vehiculo = new Vehiculo();
        }
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Vehiculo> getVehiculos() {
        this.session = null;
        this.transaction = null;

        try {
            VehiculoDaoImplements dao = new VehiculoDaoImplements();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();

            this.vehiculos = dao.verTodo(this.session);

            this.transaction.commit();

            return vehiculos;

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

    public List<Vehiculo> getQuery() {
        return query;
    }

    public void setQuery(List<Vehiculo> query) {
        this.query = query;
    }

    public List<Vehiculo> completarTipo(String nombre) {
        VehiculoDao tipodao = new VehiculoDaoImplements();
        vehiculos = tipodao.buscarxNombre(nombre);
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Vehiculo> filtrarDisponible(String name) {
        this.query = new ArrayList<Vehiculo>();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        List<Vehiculo> tipos = vehiculodao.filtarDisponible("D");        
        for (Vehiculo tipo : tipos) {            
            if (tipo.getSerie().toUpperCase().startsWith(name)) {
                query.add(tipo);
            }
        }
        return query;
    }
}
