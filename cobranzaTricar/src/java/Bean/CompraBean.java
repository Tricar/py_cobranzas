/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.*;
import Model.*;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class CompraBean implements Serializable {

    private Session session;
    private Transaction transaction;

    private Articulo producto;
    private List<Articulo> listaproducto;
    private Operacion venta;
    private List<Operaciondetalle> listaventadetalle;
    public Anexo anexo = new Anexo();

    private String valorCodigoBarras;

    public CompraBean() {
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
            this.listaventadetalle.add(new Operaciondetalle(null, null, null, this.producto.getCodigo(), this.producto.getDescripcion1(), 1, this.producto.getPrecioventa(), new BigDecimal("0")));
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
                BigDecimal totalVentaPorProducto = item.getPrecio().multiply(new BigDecimal(item.getCantidad()));
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
                BigDecimal totalVentaPorProducto = item.getPrecio().multiply(new BigDecimal(item.getCantidad()));
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

    public void realizarVenta() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            ArticuloDao productodao = new ArticuloDaoImp();
            OperacionDao ventadao = new OperacionDaoImp();
            OperaciondetalleDao ventadetalledao = new OperaciondetalleDaoImp();
            this.transaction = this.session.beginTransaction();
            ventadao.insertar(this.session, this.venta);
            this.venta = ventadao.getUltimoRegistro(this.session);
            for (Operaciondetalle item : this.listaventadetalle) {
                this.producto = productodao.getByCodigoBarras(this.session, item.getCodigoproducto());
                item.setOperacion(this.venta);
                item.setArticulo(this.producto);
                ventadetalledao.insertar(this.session, item);
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

    public void nuevoanexo() {
        anexo = new Anexo();
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
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

}
