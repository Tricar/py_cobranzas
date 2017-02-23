package Bean;

import Dao.OcupacionDao;
import Dao.OcupacionDaoImpl;
import Model.Anexo;
import Model.Credito;
import Model.Ocupacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dajoh
 */
@ManagedBean
@SessionScoped
public class ocupacionBean implements Serializable {

    private Ocupacion ocupacion = new Ocupacion();
    private List<Ocupacion> ocupaciones;
    private List<Ocupacion> ocupsxanexo = new ArrayList();
    private boolean opcbol = true;
    private boolean opcfacbol = true;
    private boolean opcrrhh = true;
    private boolean opccons = true;
    private boolean opctprop = true;
    private boolean opclic = true;
    private boolean opcsunat = true;
    private boolean opclicf = true;
    private String casa;
    private String btnGuardar;
    private Anexo anexo;

    /**
     * Creates a new instance of ocupacionBean
     */
    public ocupacionBean() {
    }

    public void limpiarlista() {
        anexo = new Anexo();
        ocupsxanexo = new ArrayList();

    }

    public List cargarIngresos(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        return ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
    }

    public List cargarxCredito(Credito credito) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        return ocupsxanexo = ocudao.ocupacionesxIdventa(credito);
    }

    public void cargarIngSolo(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
    }

    public List insertar(Anexo anexo, Ocupacion ocup) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocup.setAnexo(anexo);
        ocudao.insertarOcupacion(ocup);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        return ocupsxanexo;
    }

    public void insertarSolo(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        try {
            if (btnGuardar.equals("Registrar")) {
                ocupacion.setAnexo(anexo);
                ocupacion.setCredito(null);
                ocudao.insertarOcupacion(ocupacion);
            }
            ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
            RequestContext.getCurrentInstance().update("formTabla");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formOcupacion");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
        }
    }

    public void insertarCredito(Credito cred, Ocupacion ocup) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupacion = ocup;
        ocupacion.setCredito(cred);
        ocupacion.setAnexo(null);
        ocudao.insertarOcupacion(ocupacion);
    }

    public void insert() {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        try {
            ocupacion.setAnexo(anexo);
            ocudao.insertarOcupacion(ocupacion);
            //RequestContext.getCurrentInstance().update("formOcupacion");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formOcupacion");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
        }

    }

    public void limpiarIngreso(Anexo anexo) {
        if (anexo.getCpropia() != null) {
            opcbol = true;
            opccons = true;
            opcfacbol = true;
            opclic = true;
            opclicf = true;
            opcrrhh = true;
            opcsunat = true;
            opctprop = true;
            ocupacion = new Ocupacion();
            btnGuardar = "Registrar";
            RequestContext.getCurrentInstance().update("formOcupacion");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
        } else {
            RequestContext.getCurrentInstance().update("formTabla");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe indicar si el cliente tiene casa propia."));
        }

    }

    public void limpIngreso(Anexo anexito) {
        anexo = anexito;
        opcbol = true;
        //opccons = true;
        opcfacbol = true;
        opclic = true;
        opclicf = true;
        opcrrhh = true;
        opcsunat = true;
        opctprop = true;
        ocupacion = new Ocupacion();
        btnGuardar = "Registrar";
        RequestContext.getCurrentInstance().update("formOcupacion");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void historial(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        RequestContext.getCurrentInstance().update("formhistorial");
        RequestContext.getCurrentInstance().execute("PF('dlghistorial').show()");
    }

    public void actualizar() {        
        if ((ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("FR")) && anexo.getCpropia().equals("SI")) {
            opcbol = false;
            //opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opcsunat = true;
            opctprop = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("FR")) && anexo.getCpropia().equals("NO")) {
            opcbol = false;
            //opccons = true;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opcsunat = true;
            opctprop = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("IF")) && anexo.getCpropia().equals("SI")) {
            opcbol = true;
            //opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = false;
            opcsunat = true;
            opctprop = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("IF")) && anexo.getCpropia().equals("NO")) {
            opcbol = true;
            //opccons = true;
            opcfacbol = true;
            opclic = true;
            opcrrhh = false;
            opcsunat = true;
            opctprop = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("FR")) && anexo.getCpropia().equals("SI")) {
            opcsunat = false;
            opcbol = true;
            //opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opctprop = true;
            opclicf = false;
        }
        if ((ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("FR")) && anexo.getCpropia().equals("NO")) {
            opcsunat = false;
            opcbol = true;
            //opccons = true;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opctprop = true;
            opclicf = false;
        }
        if ((ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("IF")) && anexo.getCpropia().equals("SI")) {
            opctprop = true;
            opclic = true;
            opcfacbol = false;
            opcbol = true;
            //opccons = false;
            opcrrhh = true;
            opcsunat = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("IF")) && anexo.getCpropia().equals("NO")) {
            opctprop = true;
            opclic = true;
            opcfacbol = false;
            opcbol = true;
            //opccons = true;
            opcrrhh = true;
            opcsunat = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("TR") && (ocupacion.getClase().equals("FR") || ocupacion.getClase().equals("IF"))) && anexo.getCpropia().equals("SI")) {
            opctprop = false; //t propiedad
            opclic = false; //lic cond
            opcfacbol = true;
            opcbol = true;
            //opccons = false; //autoavaluo
            opcrrhh = true;
            opcsunat = true;
            opclicf = true;
        }
        if ((ocupacion.getTipo().equals("TR") && (ocupacion.getClase().equals("FR") || ocupacion.getClase().equals("IF"))) && anexo.getCpropia().equals("NO")) {
            opctprop = false; //t propiedad
            opclic = false; //lic cond
            opcfacbol = true;
            opcbol = true;
            //opccons = true;
            opcrrhh = true;
            opcsunat = true;
            opclicf = true;
        }
    }

    public List eliminar(Anexo anexo, Ocupacion ocup) {
        ocupacion = ocup;
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.eliminarOcupacion(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        return ocupsxanexo;
    }

    public void eliminar(Ocupacion ocup) {
        ocupacion = ocup;
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.eliminarOcupacion(ocupacion);
    }

    public void delete() {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.delete(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        RequestContext.getCurrentInstance().update("formTabla");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");

    }

    public void eliminarSolo(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.eliminarOcupacion(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        RequestContext.getCurrentInstance().update("formTabla");
        RequestContext.getCurrentInstance().execute("formTabla");
        RequestContext.getCurrentInstance().execute("PF('dlgeliminar').hide()");
    }

    public void veryIdModify(Integer idocupacion) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupacion = ocudao.verifyIdocup(idocupacion);
        btnGuardar = "Salir";
        RequestContext.getCurrentInstance().update("formOcupacion");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void veryIdDelete(Integer idocupacion) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupacion = ocudao.verifyIdocup(idocupacion);
        RequestContext.getCurrentInstance().update("formEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }

    public String nuevo() {
        anexo = new Anexo();
        ocupacion = new Ocupacion();
        return "/mantenimiento/formIng.xhtml";
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public List<Ocupacion> getOcupaciones() {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupaciones = ocudao.mostrarOcupaciones();
        return ocupaciones;
    }

    public void setOcupaciones(List<Ocupacion> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    public List<Ocupacion> getOcupsxanexo() {
        return ocupsxanexo;
    }

    public void setOcupsxanexo(List<Ocupacion> ocupsxanexo) {
        this.ocupsxanexo = ocupsxanexo;
    }

    public boolean isOpcbol() {
        return opcbol;
    }

    public void setOpcbol(boolean opcbol) {
        this.opcbol = opcbol;
    }

    public boolean isOpcfacbol() {
        return opcfacbol;
    }

    public void setOpcfacbol(boolean opcfacbol) {
        this.opcfacbol = opcfacbol;
    }

    public boolean isOpcrrhh() {
        return opcrrhh;
    }

    public void setOpcrrhh(boolean rrhh) {
        this.opcrrhh = rrhh;
    }

    public boolean isOpccons() {
        return opccons;
    }

    public void setOpccons(boolean opccons) {
        this.opccons = opccons;
    }

    public boolean isOpctprop() {
        return opctprop;
    }

    public void setOpctprop(boolean opctprop) {
        this.opctprop = opctprop;
    }

    public boolean isOpclic() {
        return opclic;
    }

    public void setOpclic(boolean opclic) {
        this.opclic = opclic;
    }

    public boolean isOpcsunat() {
        return opcsunat;
    }

    public void setOpcsunat(boolean opcsunat) {
        this.opcsunat = opcsunat;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public boolean isOpclicf() {
        return opclicf;
    }

    public void setOpclicf(boolean opclicf) {
        this.opclicf = opclicf;
    }

    public String getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(String btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

}
