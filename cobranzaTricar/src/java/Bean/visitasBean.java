package Bean;

import Dao.VisitasDao;
import Dao.VisitasDaoImp;
import Model.Visitas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class visitasBean implements Serializable {

    private List<Visitas> listafiltrada = new ArrayList();
    private Visitas visitas = new Visitas();
    private List<Visitas> cajas = new ArrayList();
    private String btnguardar;
    private List<Visitas> listaFiltTienda = new ArrayList();
    private List<Visitas> visitasporgestor = new ArrayList();

    public visitasBean() {
    }

    public List todos() {
        VisitasDao cajadao = new VisitasDaoImp();
        cajas = cajadao.mostrarVisitas();
        return cajas;
    }

    public void insertar(Visitas visitas) {
        VisitasDao cajadao = new VisitasDaoImp();
        Date fecha = new Date();
        visitas.setFecreg(fecha);
        cajadao.insertarVisitas(visitas);
    }

    public void modificar() {
        VisitasDao cajadao = new VisitasDaoImp();
        cajadao.modificarVisitas(visitas);
    }

    public void eliminar() {
        VisitasDao cajadao = new VisitasDaoImp();
        cajadao.eliminarVisitas(visitas);
    }

    public void nueva() {
        visitas = new Visitas();
        btnguardar = "Registrar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdModify(Integer idvisitas) {
        VisitasDao cajadao = new VisitasDaoImp();
        visitas = cajadao.getbyId(idvisitas);
        btnguardar = "Modificar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idvisitas) {
        VisitasDao cajadao = new VisitasDaoImp();
        visitas = cajadao.getbyId(idvisitas);
        RequestContext.getCurrentInstance().update("frmEliminar");
        RequestContext.getCurrentInstance().execute("PF('dlgEliminar').show()");
    }
    
    public List<Visitas> verHistorial(Date fecha1, Date fecha2){
        VisitasDao visdao = new VisitasDaoImp();
        List <Visitas> lista = visdao.historial(fecha1, fecha2);
        return lista;
    }

    public List<Visitas> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Visitas> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Visitas getVisitas() {
        return visitas;
    }

    public void setVisitas(Visitas visitas) {
        this.visitas = visitas;
    }

    public List<Visitas> getCajas() {
        return cajas;
    }

    public void setCajas(List<Visitas> cajas) {
        this.cajas = cajas;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public List<Visitas> getListaFiltTienda() {
        return listaFiltTienda;
    }

    public void setListaFiltTienda(List<Visitas> listaFiltTienda) {
        this.listaFiltTienda = listaFiltTienda;
    }

    public List<Visitas> getVisitasporgestor() {
        return visitasporgestor;
    }

    public void setVisitasporgestor(List<Visitas> visitasporgestor) {
        this.visitasporgestor = visitasporgestor;
    }
}
