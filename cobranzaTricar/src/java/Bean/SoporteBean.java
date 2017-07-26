/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.*;
import Model.*;
import Persistencia.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import utiles.dbManager;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class SoporteBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Articulo producto;
    private List<Articulo> listaproducto;
    private Soporte soporte;
    private List<Soporte> listasoporte;
    public Anexo anexo;
    public List<Vehiculoanexo> vehiculoanexo;
    String recibo = new String();

    private String valorCodigoBarras;
    private Boolean boton = false;

    public SoporteBean() {
        this.soporte = new Soporte();
        this.listasoporte = new ArrayList<>();
    }

    public String index() {
        listasoporte = new ArrayList();
        return "/operacion/lista";
    }

    public void cargarVentaAtendida() {
        SoporteDao operaciondao = new SoporteDaoImpl();
        try {
            listasoporte = operaciondao.cargarxEstado(1);
        } catch (Exception e) {
        }
    }

    public String nuevo() {
        String codigo = generar_codigo_Articulo();
        this.soporte.setRecibo(codigo);
        return "/operacion/formgarantia";
    }

    public Integer generar_consecutivo() {
        int vcorre = 1;
        String sql = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            sql = "SELECT conteo FROM soporte";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("conteo"));
                vcorre++;
            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcorre);
    }

    public String generar_codigo_Articulo() {
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
            sql = "SELECT conteo FROM soporte";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("conteo"));
                vcorre++;
            }
            for (int i = 1; i < 5 - String.valueOf(vcorre).length(); i++) {
                vcerosart = vcerosart + "0";
            }
            vcodigoart = vcerosart + vcorre;
            rs.close();
            st.close();
            con.close();

            vcodigofinalarticulo = "001-" + vcodigoart;

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }

    public void onCountryChange() {
        if (anexo != null && !anexo.equals("")) {
            vehiculoanexo = null;
        } else {
            vehiculoanexo = ListaSubFamilia(this.soporte.getAnexo().getIdanexo());
        }
    }

    public List<Vehiculoanexo> ListaSubFamilia(Integer idanexo) {
        this.session = null;
        this.transaction = null;
        try {
            VehiculoanexoDao daocolor = new VehiculoanexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.vehiculoanexo = daocolor.verByFamilia(this.session, idanexo);
            this.transaction.commit();
            return vehiculoanexo;
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

    public List<Articulo> getAllProducto() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            ArticuloDao productodao = new ArticuloDaoImp();
            this.transaction = this.session.beginTransaction();
            this.listaproducto = productodao.verTodo(this.session);
            this.transaction.commit();
            return listaproducto;
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

    public void realizarSoporte(Usuario usuario) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            SoporteDao soportedao = new SoporteDaoImpl();
            this.transaction = this.session.beginTransaction();
            if (soportedao.verByDistrito(this.session, this.soporte.getReporte()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No ha ingresado ningun reporte."));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                return;
            }
            Integer numero = generar_consecutivo();
            this.soporte.setConteo(numero);
            Date d = new Date();
            this.soporte.setIngreso(d);
            this.soporte.setCreated(d);
            this.soporte.setEstado(1);
            this.soporte.setUsuario(usuario.getAnexo());
            soportedao.registrar(session, soporte);
            this.transaction.commit();
            this.soporte = new Soporte();
            this.boton = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se registro la venta."));
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

    public void nuevoanexo() {
        anexo = new Anexo();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
    }

    public void cargarEliminar(int codigo) {
        this.session = null;
        this.transaction = null;

        try {

            OperacionDao ventadao = new OperacionDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            //this.soporte = ventadao.verByCodigo(this.session, codigo);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarCompra");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarCompra').show()");

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

    public void cargarAnular(int codigo) {
        this.session = null;
        this.transaction = null;

        try {

            OperacionDao ventadao = new OperacionDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            //this.venta = ventadao.verByCodigo(this.session, codigo);

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmAnularCompra");
            RequestContext.getCurrentInstance().execute("PF('dialogoAnularCompra').show()");

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

    public void cargarEliminarDetalle(int codigo, Usuario usuario) {
        this.session = null;
        this.transaction = null;

        try {

            OperaciondetalleDao detalledao = new OperaciondetalleDaoImp();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            if (!usuario.getPerfil().getAbrev().equals("AD")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos."));
                RequestContext.getCurrentInstance().update("formMostrar");
                RequestContext.getCurrentInstance().update("formModificar");
                return;
            }

//            this.ventadetalle = detalledao.verByCodigo(this.session, codigo);
            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEliminarDetalle");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarDetalle').show()");

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

    public void eliminar() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            OperacionDao ventadao = new OperacionDaoImp();
//            ventadao.eliminar(this.session, this.venta);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino Correctamente."));
            this.soporte = new Soporte();
            RequestContext.getCurrentInstance().update("formMostrar");

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

    public void anular() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

//            OperacionDao ventadao = new OperacionDaoImp();
//            eliminarOperaciondetalle(this.venta.getIdoperacion());
//            ventadao.eliminar(this.session, this.venta);
            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Anulo Correctamente."));
            this.soporte = new Soporte();
            RequestContext.getCurrentInstance().update("formMostrar");

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

    public void CargarDetalle(Operacion cred) {
        OperaciondetalleDao ventadetalledao = new OperaciondetalleDaoImp();
        List<Operaciondetalle> lista = new ArrayList();
        lista = ventadetalledao.mostrarSoloDetallexCompra(cred);
        if (lista.isEmpty() == false) {
            RequestContext.getCurrentInstance().update("formModificar");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarVenta').show()");
        } else {
            RequestContext.getCurrentInstance().update("formMostrar");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "No existen Detalle de la venta."));
        }
    }

    public void cargarSalida(int codigoUsuario) {
        this.session = null;
        this.transaction = null;

        try {

            OperacionDao ventadao = new OperacionDaoImp();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

//            this.venta = ventadao.verByCodigos(codigoUsuario);
            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmEditar:panelEditar");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditar').show()");

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

    public void Imprimir(Integer idsoporte) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        SoporteDao ventadao = new SoporteDaoImpl();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        this.soporte = ventadao.verByCodigos(idsoporte);
        parametros.put("liqventa", idsoporte);
        File jasper = new File("D:/reporte/ordenservicio.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ORDEN-" + this.soporte.getRecibo() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
        this.soporte = new Soporte();
    }

    public Articulo getProducto() {
        return producto;
    }

    public void setProducto(Articulo producto) {
        this.producto = producto;
    }

    public List<Articulo> getListaproducto() {
        return listaproducto;
    }

    public void setListaproducto(List<Articulo> listaproducto) {
        this.listaproducto = listaproducto;
    }

    public Soporte getSoporte() {
        return soporte;
    }

    public void setSoporte(Soporte soporte) {
        this.soporte = soporte;
    }

    public String getValorCodigoBarras() {
        return valorCodigoBarras;
    }

    public void setValorCodigoBarras(String valorCodigoBarras) {
        this.valorCodigoBarras = valorCodigoBarras;
    }

    public Boolean getBoton() {
        return boton;
    }

    public void setBoton(Boolean boton) {
        this.boton = boton;
    }

    public List<Vehiculoanexo> getVehiculoanexo() {
        return vehiculoanexo;
    }

    public void setVehiculoanexo(List<Vehiculoanexo> vehiculoanexo) {
        this.vehiculoanexo = vehiculoanexo;
    }

    public List<Soporte> getListasoporte() {
        return listasoporte;
    }

    public void setListasoporte(List<Soporte> listasoporte) {
        this.listasoporte = listasoporte;
    }
    
    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

}
