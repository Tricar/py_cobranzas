package Bean;

import Dao.*;
import Model.Articulo;
import Model.Familia;
import Model.Modelo;
import Model.Subfamilia;
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
    private Familia familia;
    private List<Articulo> articulos;
    private List<Subfamilia> subfamilia;
    private Modelo modelo;
    private Subfamilia subfamiliacodigo;

    private Session session;
    private Transaction transaction;

    private List<SelectItem> SelectItemsColor;

    public void onCountryChange() {
        if (familia != null && !familia.equals("")) {
            subfamilia = null;
        } else {
            subfamilia = ListaSubFamilia(this.articulo.getFamilia().getIdfamilia());
        }
    }

    public List<Subfamilia> ListaSubFamilia(Integer idfamilia) {
        this.session = null;
        this.transaction = null;
        try {
            SubfamiliaDao daocolor = new SubfamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.subfamilia = daocolor.verByFamilia(this.session, idfamilia);
            this.transaction.commit();
            return subfamilia;
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

    public void registrar() {
        this.session = null;
        this.transaction = null;
        String descripcionarticulo = "";
        String codmodelo = "";
        String codfamilia = "";
        String codsubfamilia = "";
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            ArticuloDao linkDao = new ArticuloDaoImp();

            if (this.articulo.getModelo() == null) {
                codmodelo = "00";
            } else {
                codmodelo = this.articulo.getModelo().getNumero();
            }

            if (this.articulo.getFamilia() == null) {
                codfamilia = "00";
            } else {
                codfamilia = this.articulo.getFamilia().getNumero();
            }

            if (this.articulo.getSubfamilia() == null) {
                codsubfamilia = "00";
            } else {
                codsubfamilia = this.articulo.getSubfamilia().getNumero();
            }

            if (linkDao.verByDescripcion(this.session, descripcionarticulo) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Repuesto/Servicio ya esta registrado."));
                articulo = new Articulo();
                return;
            }

            if (this.articulo.getModelo() == null) {
                descripcionarticulo = this.articulo.getDescripcion2();
            } else {
                descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo();
            }

            String codigo = generar_codigo_Articulo(this.articulo, codmodelo, codfamilia, codsubfamilia);
            Integer numero = generar_consecutivo(this.articulo);

            Date d = new Date();
            this.articulo.setCreated(d);
            this.articulo.setConsecutivo(numero);
            this.articulo.setCodigo(codigo);
            this.articulo.setDescripcion1(descripcionarticulo);
            this.articulo.setCostopromedio(BigDecimal.ZERO);
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

    public void modificar() {
        this.session = null;
        this.transaction = null;
        String descripcionarticulo = "";
        String codmodelo = "";
        String codfamilia = "";
        String codsubfamilia = "";
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            ArticuloDao linkDao = new ArticuloDaoImp();

            if (this.articulo.getModelo() == null) {
                codmodelo = "00";
            } else {
                codmodelo = this.articulo.getModelo().getNumero();
            }

            if (this.articulo.getFamilia() == null) {
                codfamilia = "00";
            } else {
                codfamilia = this.articulo.getFamilia().getNumero();
            }

            if (this.articulo.getSubfamilia() == null) {
                codsubfamilia = "00";
            } else {
                codsubfamilia = this.articulo.getSubfamilia().getNumero();
            }

            if (this.articulo.getPrecioventa().equals(new BigDecimal("0")) || this.articulo.getPrecioventa().equals(new BigDecimal("0.00")) || this.articulo.getPrecioventa().equals(new BigDecimal("0.0"))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio debe ser mayor a cero."));
                articulo = new Articulo();
                return;
            }

            if (this.articulo.getModelo() == null) {
                descripcionarticulo = this.articulo.getDescripcion2();
            } else {
                descripcionarticulo = this.articulo.getDescripcion2() + " " + this.articulo.getModelo().getModelo();
            }

            String codigo = actualizar_codigo_Articulo(this.articulo, codmodelo, codfamilia, codsubfamilia);
            Integer numero = actualizar_consecutivo(this.articulo);

            this.articulo.setConsecutivo(numero);
            this.articulo.setCodigo(codigo);
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

    public Integer generar_consecutivo(Articulo objarticulo) {
        int vcorre = 0;
        String sql = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            if (objarticulo.getTipoarticulo().getIdtipoarticulo() == 1) {
                sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "' and idmodelo ='" + objarticulo.getModelo().getIdmodelo()
                        + "' and idfamilia ='" + objarticulo.getFamilia().getIdfamilia() + "' and idsubfamilia ='" + objarticulo.getSubfamilia().getIdsubfamilia() + "'";
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
            } else {
                sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "'";
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
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcorre);
    }

    public String generar_codigo_Articulo(Articulo objarticulo, String codmodelo, String codfamilia, String codsubfamilia) {
        int vcorre = 0;
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
            if (objarticulo.getTipoarticulo().getIdtipoarticulo() == 1) {
                sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "' and idmodelo ='" + objarticulo.getModelo().getIdmodelo()
                        + "' and idfamilia ='" + objarticulo.getFamilia().getIdfamilia() + "' and idsubfamilia ='" + objarticulo.getSubfamilia().getIdsubfamilia() + "'";
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
            } else {
                sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "'";
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
            }

            vcodigofinalarticulo = objarticulo.getTipoarticulo().getAbreviado() + codmodelo + codfamilia + codsubfamilia + vcodigoart;

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }

    public Integer actualizar_consecutivo(Articulo objarticulo) {
        int vcorre = 0;
        String sql = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            if (!modelo.getIdmodelo().equals(objarticulo.getModelo().getIdmodelo()) || !familia.getIdfamilia().equals(objarticulo.getFamilia().getIdfamilia()) || !subfamiliacodigo.getIdsubfamilia().equals(objarticulo.getSubfamilia().getIdsubfamilia())) {
                if (objarticulo.getTipoarticulo().getIdtipoarticulo() == 1) {
                    sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "' and idmodelo ='" + objarticulo.getModelo().getIdmodelo()
                            + "' and idfamilia ='" + objarticulo.getFamilia().getIdfamilia() + "' and idsubfamilia ='" + objarticulo.getSubfamilia().getIdsubfamilia() + "'";
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
                } else {
                    sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "'";
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
                }
            } else {
                vcorre = objarticulo.getConsecutivo();
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcorre);
    }

    public String actualizar_codigo_Articulo(Articulo objarticulo, String codmodelo, String codfamilia, String codsubfamilia) {
        int vcorre = 0;
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
            if (!modelo.getIdmodelo().equals(objarticulo.getModelo().getIdmodelo()) || !familia.getIdfamilia().equals(objarticulo.getFamilia().getIdfamilia()) || !subfamiliacodigo.getIdsubfamilia().equals(objarticulo.getSubfamilia().getIdsubfamilia())) {
                if (objarticulo.getTipoarticulo().getIdtipoarticulo() == 1) {
                    sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "' and idmodelo ='" + objarticulo.getModelo().getIdmodelo()
                            + "' and idfamilia ='" + objarticulo.getFamilia().getIdfamilia() + "' and idsubfamilia ='" + objarticulo.getSubfamilia().getIdsubfamilia() + "'";
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
                } else {
                    sql = "SELECT consecutivo FROM articulo where idtipoarticulo='" + objarticulo.getTipoarticulo().getIdtipoarticulo() + "'";
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
                }
            } else {
                for (int i = 1; i < 4 - String.valueOf(objarticulo.getConsecutivo()).length(); i++) {
                    vcerosart = vcerosart + "0";
                }
                vcodigoart = vcerosart + objarticulo.getConsecutivo();
            }
            vcodigofinalarticulo = objarticulo.getTipoarticulo().getAbreviado() + codmodelo + codfamilia + codsubfamilia + vcodigoart;
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }

    public void cargarColorEditar(Integer idcolor) {
        this.session = null;
        this.transaction = null;
        try {
            ArticuloDao daocolor = new ArticuloDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.articulo = daocolor.verByCodigo(this.session, idcolor);
            if (this.articulo.getModelo() != null) {
                this.modelo = this.articulo.getModelo();
            } else {
                this.modelo.setIdmodelo(0);
            }
            if (this.articulo.getFamilia() != null) {
                this.familia = this.articulo.getFamilia();
            } else {
                this.familia.setIdfamilia(0);
            }
            if (this.articulo.getSubfamilia() != null) {
                this.subfamiliacodigo = this.articulo.getSubfamilia();
            } else {
                this.subfamiliacodigo.setIdsubfamilia(0);
            }
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

    public List<Subfamilia> getSubfamilia() {
        this.session = null;
        this.transaction = null;
        try {
            SubfamiliaDao daocolor = new SubfamiliaDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            subfamilia = daocolor.verByFamilia(session, this.articulo.getFamilia().getIdfamilia());
            return subfamilia;
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

    public void setSubfamilia(List<Subfamilia> subfamilia) {
        this.subfamilia = subfamilia;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void setSelectItemsColor(List<SelectItem> SelectItemsColor) {
        this.SelectItemsColor = SelectItemsColor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Subfamilia getSubfamiliacodigo() {
        return subfamiliacodigo;
    }

    public void setSubfamiliacodigo(Subfamilia subfamiliacodigo) {
        this.subfamiliacodigo = subfamiliacodigo;
    }

}
