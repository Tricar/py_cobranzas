package Bean;

import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Credito;
import Model.Modelo;
import Model.Usuario;
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
    private String idmodelo;
    public List<Vehiculo> vehiculosfiltrados;

    public String getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(String idmodelo) {
        this.idmodelo = idmodelo;
    }

    public vehiculoBean() {
        this.vehiculo = new Vehiculo();
    }

    public List<Vehiculo> getVehiculosfiltrados() {
        return vehiculosfiltrados;
    }

    public void setVehiculosfiltrados(List<Vehiculo> vehiculosfiltrados) {
        this.vehiculosfiltrados = vehiculosfiltrados;
    }

    public void nuevo(Usuario usuario) {
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("AS")) {
            vehiculo = new Vehiculo();
            RequestContext.getCurrentInstance().update("formInsertar");
            RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
        } else {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para Editar.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void registrar(Usuario usuario) {
        System.out.println("modelo: " + vehiculo.getModelo().getModelo());
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("AS")) {
            this.session = null;
            this.transaction = null;
            try {
                this.session = HibernateUtil.getSessionFactory().openSession();
                this.transaction = session.beginTransaction();
                VehiculoDao dao = new VehiculoDaoImplements();
                if (dao.verBySerie(this.session, this.vehiculo.getSerie()) != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Articulo ya existe en DB."));
                    this.vehiculo = new Vehiculo();
                    return;
                }
                Date d = new Date();
                vehiculo.setFechareg(d);
                vehiculo.setTipovehiculo(vehiculo.getModelo().getTipo());
                System.out.println("modelo: " + vehiculo.getModelo().getModelo());
                vehiculo.setMarca(obtenerMarca(vehiculo.getModelo().getModelo()));
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
        } else {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para Editar.");
        }
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            VehiculoDao dao = new VehiculoDaoImplements();
            if (dao.verBySerieDifer(this.session, this.vehiculo.getIdvehiculo(), this.vehiculo.getSerie()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Articulo ya Existe."));
                return;
            }
            vehiculo.setTipovehiculo(vehiculo.getModelo().getTipo());
            vehiculo.setMarca(obtenerMarca(vehiculo.getModelo().getModelo()));
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
            VehiculoDao dao = new VehiculoDaoImplements();
            dao.eliminar(this.session, this.vehiculo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
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

    public void cargarArticuloEditar(Integer codigoUsuario, Usuario usuario) {
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("AS")) {
            this.session = null;
            this.transaction = null;

            try {

                VehiculoDao dao = new VehiculoDaoImplements();
                this.session = HibernateUtil.getSessionFactory().openSession();
                this.transaction = session.beginTransaction();
                System.out.println("idvehiculo: " + codigoUsuario);
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
        } else {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para Editar."));
        }
    }

    public void cargarArticuloEliminar(Integer codigoUsuario, Usuario usuario) {
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("AS")) {
            this.session = null;
            this.transaction = null;

            try {

                VehiculoDao dao = new VehiculoDaoImplements();
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
        } else {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No cuenta con permisos para Editar."));
        }
    }

    public Vehiculo getVehiculo() {
        if (vehiculo == null) {
            vehiculo = new Vehiculo();
        }
        return vehiculo;
    }

    public char obtenerMarca(String modelo) {
        char marca = 'v';
        char ab = modelo.charAt(0);
        switch (ab) {
            case 'V':
                marca = 'V';
                break;
            case 'M':
                marca = 'V';
                break;
            case 'T':
                marca = 'T';
                break;
            case 'G':
                marca = 'T';
                break;
            case 'X':
                marca = 'Y';
                break;
            case 'F':
                marca = 'Y';
                break;
        }
        return marca;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Vehiculo> getVehiculos() {
        this.session = null;
        this.transaction = null;

        try {
            VehiculoDao dao = new VehiculoDaoImplements();
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

    public List<Vehiculo> filtrarDisponibleModelo(String name) {
        this.query = new ArrayList<Vehiculo>();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        List<Vehiculo> tipos = vehiculodao.filtarDisponibleCotiza('D', idmodelo);
        for (Vehiculo tipo : tipos) {
            if (tipo.getSerie().endsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public void cargarModelo(Credito cred) {
        Integer modelo = cred.getModelo().getIdmodelo();
        idmodelo = Integer.toString(modelo);
    }

    public void cargarModeloVehi(Modelo mod) {
        Integer modelo = mod.getIdmodelo();
        idmodelo = Integer.toString(modelo);
    }
}
