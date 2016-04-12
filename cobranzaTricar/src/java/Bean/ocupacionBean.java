package Bean;

import Dao.OcupacionDao;
import Dao.OcupacionDaoImpl;
import Model.Anexo;
import Model.Ocupacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    /**
     * Creates a new instance of ocupacionBean
     */
    public ocupacionBean() {
    }
    
    public void limpiarlista(){
        ocupsxanexo = new ArrayList();
    }

    public void cargarIngresos(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
    }

    public void insertar(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        try {
            OcupacionDao ocupdao = new OcupacionDaoImpl();
            ocupacion.setIdanexo(anexo);
            ocupdao.insertarOcupacion(ocupacion);
            ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
            RequestContext.getCurrentInstance().update("formOcupacion");
            RequestContext.getCurrentInstance().execute("PF('dlginsertar').hide()");
        } catch (Exception e) {
        }

    }

    public void modificar() {

    }

    public void eliminar(Anexo anexo) {
        OcupacionDao ocudao = new OcupacionDaoImpl();
        ocudao.eliminarOcupacion(ocupacion);
        ocupsxanexo = ocudao.ocupacionesxIdanexo(anexo);
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

}
