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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import utiles.dbManager;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class VentaMBBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Articulo producto;
    private List<Articulo> listaproducto;
    private Operacion venta;
    private List<Operacion> listaventa;
    private Operaciondetalle ventadetalle;
    private List<Operaciondetalle> listaventadetalle;
    public Anexo anexo = new Anexo();
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();

    private String valorCodigoBarras;

    public VentaMBBean() {
        this.venta = new Operacion();
        this.listaventadetalle = new ArrayList<>();
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

    public void agegarListaVentaDetalle(Integer idProducto) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            ArticuloDao productodao = new ArticuloDaoImp();
            this.transaction = this.session.beginTransaction();
            this.producto = productodao.getByIdProducto(this.session, idProducto);
            if (this.producto.getCantidad() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay Stock para el articulo."));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                return;
            }
            for (int i = 0; i < listaventadetalle.size(); i++) {
                Operaciondetalle get = listaventadetalle.get(i);
                if (this.producto.getCodigo().equals(get.getCodigoproducto())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Articulo ya esta en la lista."));
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                    return;
                }
            }
            this.listaventadetalle.add(new Operaciondetalle(null, null, null, this.producto.getCodigo(), this.producto.getDescripcion1(), 0, 1, null, null, null, this.producto.getPrecioventa(), new BigDecimal("0"), null, null));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agrego el producto a la venta."));
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
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

    public void retirarListaVentaDetalle(String codigoBarras) {
        try {
            int i = 0;
            for (Operaciondetalle item : this.listaventadetalle) {
                if (item.getCodigoproducto().equals(codigoBarras)) {
                    this.listaventadetalle.remove(i);
                    break;
                }
                i++;
            }
            BigDecimal totalventa = new BigDecimal(0);
            for (Operaciondetalle item : this.listaventadetalle) {
                BigDecimal totalVentaPorProducto = item.getPreciocompra().multiply(new BigDecimal(item.getCantidad()));
                item.setPreciototal(totalVentaPorProducto);
                totalventa = totalventa.add(totalVentaPorProducto);
            }
            this.venta.setMontototal(totalventa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Producto se quito de la lista."));
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        }
    }

    public void calcularcostos() {
        try {
            BigDecimal totalventa = new BigDecimal(0);
            for (Operaciondetalle item : this.listaventadetalle) {
                BigDecimal totalVentaPorProducto = item.getPrecioventa().multiply(new BigDecimal(item.getCantidad()));
                item.setPreciototal(totalVentaPorProducto);
                totalventa = totalventa.add(totalVentaPorProducto);
            }
            this.venta.setMontototal(totalventa);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        }
    }

    public void realizarVenta(Usuario usuario) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            ArticuloDao productodao = new ArticuloDaoImp();
            OperacionDao ventadao = new OperacionDaoImp();
            OperaciondetalleDao ventadetalledao = new OperaciondetalleDaoImp();
            this.transaction = this.session.beginTransaction();
            if (this.listaventadetalle.size() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No ha ingresado ningun articulo."));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                return;
            }
            for (Operaciondetalle item : this.listaventadetalle) {
                this.producto = productodao.getByCodigoBarras(this.session, item.getCodigoproducto());
                if (item.getCantidad() > this.producto.getCantidad()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Stock de uno de los articulo no es suficiente."));
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                    return;
                } else if (item.getCantidad() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Stock debe ser mayor a cero."));
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                    return;
                } else if (item.getPreciototal().equals(new BigDecimal("0"))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Antes de realizar la venta debe actualizar el monto de la venta."));
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
                    return;
                }
            }
            Date d = new Date();
            this.venta.setCreated(d);
            this.venta.setIdtipooperacioncontable(1);
            this.venta.setEstado(1);
            this.venta.setIdusuario(usuario.getAnexo().getIdanexo());
            ventadao.insertar(this.session, this.venta);
            this.venta = ventadao.getUltimoRegistro(this.session);
            for (Operaciondetalle item : this.listaventadetalle) {
                this.producto = productodao.getByCodigoBarras(this.session, item.getCodigoproducto());
                item.setPrecioventaanterior(this.producto.getPrecioventa());
                item.setPreciocompraanterior(this.producto.getPreciocompra());
                item.setPreciocompra(this.producto.getPreciocompra());
                item.setCostopromedioanterior(this.producto.getCostopromedio());
                item.setCostopromedio(this.producto.getCostopromedio());
                item.setOperacion(this.venta);
                item.setArticulo(this.producto);
                item.setCantidadanterior(this.producto.getCantidad());
                ventadetalledao.insertar(this.session, item);
                this.producto.setCantidad(this.producto.getCantidad() - item.getCantidad());
                productodao.modificar(session, this.producto);
            }
            this.transaction.commit();
            this.listaventadetalle = new ArrayList<>();
            this.venta = new Operacion();
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

    public String index() {
        listaventa = new ArrayList();
        listaventadetalle = new ArrayList();
        return "/venta/ventamb";
    }

    public void nuevoanexo() {
        anexo = new Anexo();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
    }

    public String nuevo() {
        return "/venta/fromventa";
    }

    public List<Operaciondetalle> cargarDetalleArray(Operacion compra) {
        OperaciondetalleDao ventadetalledao = new OperaciondetalleDaoImp();
        listaventadetalle = ventadetalledao.mostrarSoloDetallexCompra(compra);
        return listaventadetalle;
    }

    public void filtrarFechas() {
        OperacionDao ventadao = new OperacionDaoImp();
        listaventa = ventadao.filtrarFechas(fecha1, fecha2, 1);
        listaventadetalle = new ArrayList();
    }

    public void cargarEliminar(int codigo) {
        this.session = null;
        this.transaction = null;

        try {

            OperacionDao ventadao = new OperacionDaoImp();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.venta = ventadao.verByCodigo(this.session, codigo);

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
            this.venta = ventadao.verByCodigo(this.session, codigo);

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

            this.ventadetalle = detalledao.verByCodigo(this.session, codigo);

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
            ventadao.eliminar(this.session, this.venta);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino Correctamente."));
            this.venta = new Operacion();
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

            OperacionDao ventadao = new OperacionDaoImp();
            eliminarOperaciondetalle(this.venta.getIdoperacion());
            ventadao.eliminar(this.session, this.venta);

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Anulo Correctamente."));
            this.venta = new Operacion();
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

    public void eliminarOperaciondetalle(Integer idoperacion) {
        OperaciondetalleDao detalledao = new OperaciondetalleDaoImp();
        listaventadetalle = detalledao.verTodosxId(idoperacion);
        for (int i = 0; i < listaventadetalle.size(); i++) {
            Operaciondetalle get = listaventadetalle.get(i);
            ArticuloDao productodao = new ArticuloDaoImp();
            OperacionDao ventadao = new OperacionDaoImp();
            this.producto = productodao.verByCodigos(get.getArticulo().getIdarticulo());
            this.producto.setCantidad(get.getCantidadanterior());
            this.producto.setPreciocompra(get.getPreciocompraanterior());
            this.producto.setPrecioventa(get.getPrecioventaanterior());
            this.producto.setCostopromedio(get.getCostopromedioanterior());
            productodao.modificarOD(this.producto);
            this.venta = ventadao.verByCodigos(get.getOperacion().getIdoperacion());
            this.venta.setEstado(0);
            this.venta.setMontototal(this.venta.getMontototal().subtract(get.getPreciototal()));
            ventadao.modificarOD(this.venta);

            detalledao.eliminarOD(get.getIdoperaciondetalle());
            producto = new Articulo();
            venta = new Operacion();
        }

    }

    public void eliminarDetalle() {
        this.session = null;
        this.transaction = null;

        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            OperaciondetalleDao detalledao = new OperaciondetalleDaoImp();
            ArticuloDao productodao = new ArticuloDaoImp();
            OperacionDao ventadao = new OperacionDaoImp();
            this.producto = productodao.getByCodigoBarras(this.session, this.ventadetalle.getCodigoproducto());
            this.producto.setCantidad(this.ventadetalle.getCantidadanterior());
            this.producto.setPreciocompra(this.ventadetalle.getPreciocompraanterior());
            this.producto.setPrecioventa(this.ventadetalle.getPrecioventaanterior());
            this.producto.setCostopromedio(this.ventadetalle.getCostopromedioanterior());
            productodao.modificar(session, this.producto);
            this.venta = ventadao.verByCodigo(this.session, this.ventadetalle.getOperacion().getIdoperacion());
            this.venta.setMontototal(this.venta.getMontototal().subtract(this.ventadetalle.getPreciototal()));
            ventadao.modificar(session, this.venta);

            detalledao.eliminar(this.session, this.ventadetalle);

            this.transaction.commit();

            cargarDetalleArray(this.venta);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino Correctamente."));
            this.ventadetalle = new Operaciondetalle();

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

    public void Boleta(String codigo) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numeroorden", codigo);
        File jasper = new File("D:/reporte/boleta.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=BOLETA-" + codigo + ".xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        docxExporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
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

    public Operacion getVenta() {
        return venta;
    }

    public void setVenta(Operacion venta) {
        this.venta = venta;
    }

    public List<Operaciondetalle> getListaventadetalle() {
        return listaventadetalle;
    }

    public void setListaventadetalle(List<Operaciondetalle> listaventadetalle) {
        this.listaventadetalle = listaventadetalle;
    }

    public String getValorCodigoBarras() {
        return valorCodigoBarras;
    }

    public void setValorCodigoBarras(String valorCodigoBarras) {
        this.valorCodigoBarras = valorCodigoBarras;
    }

    public List<Operacion> getListaventa() {
        return listaventa;
    }

    public void setListaventa(List<Operacion> listaventa) {
        this.listaventa = listaventa;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Operaciondetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(Operaciondetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }

}
