package Bean;

import Dao.*;
import Model.Articulo;
import Model.Tipoarticulo;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import utiles.dbManager;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class ArticuloBean implements Serializable {

    private Articulo articulo;
    private List<Articulo> articulos;

    private Session session;
    private Transaction transaction;

    private List<SelectItem> SelectItemsColor;

    public ArticuloBean() {
        this.articulo = new Articulo();
    }

    public List<Articulo> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            ArticuloDao daocolor = new ArticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.articulos = daocolor.verTodo(this.session);
            this.transaction.commit();
            return articulos;
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

    public void nuevo() {
        articulo = new Articulo();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;
        String descripcionarticulo = "";
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            ArticuloDao linkDao = new ArticuloDaoImp();
            if (this.articulo.getModelo() == null) {
                if (this.articulo.getColor() == null) {
                    descripcionarticulo = this.articulo.getDescripcion2();
                } else {
                    descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getColor().getColor();
                }
            } else if (this.articulo.getColor() == null) {
                if (this.articulo.getModelo() == null) {
                    descripcionarticulo = this.articulo.getDescripcion2();
                } else {
                    descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo();
                }                
            } else {
                descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo() + " " + this.articulo.getColor().getColor();
            }
            if (this.articulo.getPrecioventa().equals(new BigDecimal("0")) || this.articulo.getPrecioventa().equals(new BigDecimal("0.00")) || this.articulo.getPrecioventa().equals(new BigDecimal("0.0"))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio debe ser mayor a cero."));
                articulo = new Articulo();
                return;
            }
            this.articulo.setDescripcion1(descripcionarticulo);
            linkDao.modificar(this.session, this.articulo);
            this.transaction.commit();
            this.articulo = new Articulo();
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

    public void cargarColorEditar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            ArticuloDao daocolor = new ArticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.articulo = daocolor.verByCodigo(this.session, idcolor);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEditarColor:panelEditarColor");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarColor').show()");
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

    public void cargarColorEliminar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            ArticuloDao daocolor = new ArticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.articulo = daocolor.verByCodigo(this.session, idcolor);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEliminarColor");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarColor').show()");
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

    public List<Articulo> filtrarArticulo(String name) {
        List<Articulo> query = new ArrayList<Articulo>();
        ArticuloDao anexoDao = new ArticuloDaoImp();
        List<Articulo> tipos = anexoDao.filtarTipo(1);
        for (Articulo tipo : tipos) {
            if (tipo.getDescripcion1().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public void handleSelect(SelectEvent e) {
        Articulo p = (Articulo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Articulo agregado :: " + p.getDescripcion1(), ""));
    }

    public void handleUnSelect(UnselectEvent e) {
        Articulo p = (Articulo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Articulo removido :: " + p.getDescripcion1(), ""));
    }

    public void registrar() {
        this.session = null;
        this.transaction = null;
        String descripcionarticulo = "";
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            ArticuloDao linkDao = new ArticuloDaoImp();
            if (this.articulo.getModelo() == null) {
                if (this.articulo.getColor() == null) {
                    descripcionarticulo = this.articulo.getDescripcion2();
                } else {
                    descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getColor().getColor();
                }
            } else if (this.articulo.getColor() == null) {
                if (this.articulo.getModelo() == null) {
                    descripcionarticulo = this.articulo.getDescripcion2();
                } else {
                    descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo();
                }                
            } else {
                descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo() + " " + this.articulo.getColor().getColor();
            }
            if (linkDao.verByDescripcion(this.session, descripcionarticulo) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Repuesto/Servicio ya esta registrado."));
                articulo = new Articulo();
                return;
            }
            String codigo = generar_codigo_Articulo(this.articulo.getTipoarticulo());
            Integer numero = generar_consecutivo(this.articulo.getTipoarticulo());
            if (codigo == null) {
                this.articulo.setConsecutivo(1);
                this.articulo.setCodigo(this.articulo.getTipoarticulo().getAbreviado() + "0001");
            } else {
                this.articulo.setConsecutivo(numero);
                this.articulo.setCodigo(codigo);
            }
            Date d = new Date();
            this.articulo.setCreated(d);
            this.articulo.setDescripcion1(descripcionarticulo);
            this.articulo.setCostopromedio(BigDecimal.ZERO);
            this.articulo.setPreciocompra(BigDecimal.ZERO);
            this.articulo.setCantidad(0);
            linkDao.registrar(this.session, this.articulo);
            this.transaction.commit();
            this.articulo = new Articulo();
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

    public Integer generar_consecutivo(Tipoarticulo objtipoarticulo) {
        int vcorre = 1;
        String sql = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objtipoarticulo.getIdtipoarticulo() + "'";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("consecutivo"));
                vcorre++;
            }
            rs.close();
            st.close();
            con.close();
            System.out.println(vcorre);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcorre);
    }

    public String generar_codigo_Articulo(Tipoarticulo objtipoarticulo) {
        int vcorre = 1;
        String sql = "";
        String vcodigoart = "";
        String vcerosart = "";
        String vcodigofinalarticulo = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objtipoarticulo.getIdtipoarticulo() + "'";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("consecutivo"));
                vcorre++;
            }
            for (int i = 1; i < 4 - String.valueOf(vcorre).length(); i++) {
                vcerosart = vcerosart + "0";
            }

            vcodigoart = vcerosart + vcorre;
            rs.close();
            st.close();
            con.close();
            System.out.println(vcerosart + vcorre);

            vcodigofinalarticulo = objtipoarticulo.getAbreviado() + vcodigoart;

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }

    public void eliminar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            ArticuloDao daocolor = new ArticuloDaoImp();
            daocolor.eliminar(this.session, this.articulo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Color Correctamente."));
            this.articulo = new Articulo();
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public List<Articulo> getArticulos() {
        this.session = null;
        this.transaction = null;
        try {
            ArticuloDao daocolor = new ArticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            articulos = daocolor.verTodo(session);
            return articulos;
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

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }
}
