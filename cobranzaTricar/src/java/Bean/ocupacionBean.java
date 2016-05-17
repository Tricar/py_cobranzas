package Bean;

import Dao.OcupacionDao;
import Dao.OcupacionDaoImpl;
import Model.Anexo;
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

    /**
     * Creates a new instance of ocupacionBean
     */
    public ocupacionBean() {
    }

    public void limpiarlista() {
        ocupsxanexo = new ArrayList();
    }

    public List cargarIngresos(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        return ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
    }

    public List insertar(Anexo anexo, Ocupacion ocup) {
        OcupacionDao ocudao = new OcupacionDaoImpl();        
        ocup.setAnexo(anexo);
        ocudao.insertarOcupacion(ocup);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        return ocupsxanexo;
    }

    public void actualizar() {
        if (ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("FR")) {
            opcbol = false;
            opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opcsunat = true;
            opctprop = true;
        }
        if (ocupacion.getTipo().equals("DP") && ocupacion.getClase().equals("IF")) {
            opcbol = true;
            opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = false;
            opcsunat = true;
            opctprop = true;
        }
        if (ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("FR")) {
            opcsunat = false;
            opcbol = true;
            opccons = false;
            opcfacbol = true;
            opclic = true;
            opcrrhh = true;
            opctprop = true;
        }
        if (ocupacion.getTipo().equals("IN") && ocupacion.getClase().equals("IF")) {
            opctprop = false;
            opclic = false;
            opcfacbol = false;
            opcbol = true;
            opccons = false;
            opcrrhh = true;
            opcsunat = true;
        }
    }

    public void modificar() {

    }

    public List eliminar(Anexo anexo, Ocupacion ocup) {
        ocupacion = ocup;
        OcupacionDao ocudao = new OcupacionDaoImpl();
        System.out.println("Ocupacion: "+ocupacion.getDescripcion());
        ocudao.eliminarOcupacion(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
        return ocupsxanexo;
    }
    
    public void eliminarsolo(Anexo anexo){
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.eliminarOcupacion(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
    }
    
    public Ocupacion veryId(Integer idocupacion){
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupacion = ocudao.verifyIdocup(idocupacion);
        return ocupacion;
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

}
