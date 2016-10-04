package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CredgestorDao;
import Dao.CredgestorDaoImp;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Anexo;
import Model.Credito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Creditogestor;
import Model.Usuario;
import Model.Visitas;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class credgestorBean implements Serializable {

    private List<Creditogestor> listafiltrada = new ArrayList();
    private Creditogestor credgestor = new Creditogestor();
    private List<Creditogestor> credgestores = new ArrayList();
    private String btnguardar;
    private Credito credito = new Credito();
    private List<Credito> creditos = new ArrayList();
    private List<Credito> selectedcreditos = new ArrayList();
    private List<Credito> selectedcreditosmostrar = new ArrayList();
    private Anexo anexo = new Anexo();
    private List<Anexo> gestores = new ArrayList();
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();
    private String dni;
    private String nombre;
    private List<Creditogestor> filtrada = new ArrayList();
    private List<Creditogestor> selectedfiltrada = new ArrayList();
    private List<Creditogestor> creditosgest = new ArrayList();
    private Visitas visita;
    private Boolean value;
    private String btnpago;
        private List<Visitas> visitashistorial = new ArrayList();

    public credgestorBean() {
    }

    public List todos() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestores = linkdao.mostrarCreditogestors();
        return credgestores;
    }

    public void insertar(Credito cred, Anexo anexo) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor.setCredito(cred);
        credgestor.setAnexo(anexo);
        linkdao.insertarCreditogestor(credgestor);
    }

    public void modificar() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        linkdao.modificarCreditogestor(credgestor);
    }

    public void eliminar(Anexo anexo, Credito credito) {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestor = linkdao.getbyAnexoCredito(anexo, credito);
        linkdao.eliminarCreditogestor(credgestor);
    }

    public void nueva() {
        credgestor = new Creditogestor();
        btnguardar = "Asignar";
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public String asignar() {        
        CreditoDao credao = new CreditoDaoImp();
        btnguardar = "Asignar";
        creditos = credao.cargarxEstadoDistinto("CN");
        return "/venta/asignar.xhtml";
    }

    public String verGestores(Usuario usuario) {
        if (usuario.getPerfil().getAbrev().equals("JE") || usuario.getPerfil().getAbrev().equals("AD")) {
            return "/venta/vergestores.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No cuenta con permisos para esta operación");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public String index() {
        credgestor = new Creditogestor();
        return "/venta/indexg.xhtml";
    }

    public String verPorGestor(Anexo anexo) {
        selectedcreditos = new ArrayList();
        this.anexo = anexo;
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestores = linkdao.getbyAnexo(anexo);
        for (int i = 0; i < credgestores.size(); i++) {
            Creditogestor get = credgestores.get(i);
            Credito cred = get.getCredito();
            selectedcreditos.add(cred);
        }
        selectedcreditosmostrar = selectedcreditos;
        return "/venta/verporgestor.xhtml";
    }

    public void verHistorial(Usuario usuario) {
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("JE")) {
            visitasBean vbean = new visitasBean();
            visitashistorial = vbean.verHistorial(fecha1, fecha2);
        }
    }

    public List<Creditogestor> getCreditogestors() {
        CredgestorDao linkdao = new CredgestorDaoImp();
        credgestores = linkdao.mostrarCreditogestors();
        return credgestores;
    }

    public void insertarCreditoGestor(Anexo gest, List<Credito> creds) {
        try {
            int contador1;
            int contador2 = 0;
            if (creds.size() == selectedcreditosmostrar.size()) {
                for (int i = 0; i < creds.size(); i++) {
                    Credito get = creds.get(i);
                    contador1 = 0;
                    for (int j = 0; j < selectedcreditosmostrar.size(); j++) {
                        Credito get1 = selectedcreditosmostrar.get(j);
                        if (get.getLiqventa().equals(get1.getLiqventa())) {
                            contador1++;
                        }
                    }
                    if (contador1 == 1) {
                        contador2++;
                    }
                }
                if (contador2 == creds.size()) {
                    showMessageIncorrecto();
                } else {
                    insertarBorrar(creds, selectedcreditosmostrar, gest);
                    showMessageCorrecto();
                }
            } else {
                if (creds.size() > selectedcreditosmostrar.size()) {
                    List<Credito> addlist = new ArrayList();
                    for (int i = 0; i < creds.size(); i++) {
                        Credito get = creds.get(i);
                        contador1 = 0;
                        for (int j = 0; j < selectedcreditosmostrar.size(); j++) {
                            Credito get1 = selectedcreditosmostrar.get(j);
                            if (get.getLiqventa().equals(get1.getLiqventa())) {
                                contador1++;
                            }
                        }
                        if (contador1 == 0) {
                            addlist.add(get);
                        }
                    }
                    insertar(addlist, gest);
                    showMessageCorrecto();
                } else {
                    List<Credito> removelist = new ArrayList();
                    for (int i = 0; i < selectedcreditosmostrar.size(); i++) {
                        Credito get = selectedcreditosmostrar.get(i);
                        contador1 = 0;
                        for (int j = 0; j < creds.size(); j++) {
                            Credito get1 = creds.get(j);
                            if (get.getLiqventa().equals(get1.getLiqventa())) {
                                contador1++;
                            }
                        }
                        if (contador1 == 0) {
                            removelist.add(get);
                        }
                    }
                    borrar(removelist, gest);
                    showMessageCorrecto();
                }
            }
            if (selectedcreditos.isEmpty() && !creds.isEmpty()) {
                insertar(creds, gest);
                showMessageCorrecto();
            }
        } catch (Exception e) {
        }
    }

    public void showMessageCorrecto() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Créditos asignados correctamente.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void showMessageIncorrecto() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay nada para actualizar");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String insertarBorrar(List<Credito> lista1, List<Credito> lista2, Anexo gest) {
        borrar(lista2, gest);
        insertar(lista1, gest);
        return "/venta/vergestores.xhtml";
    }

    public void insertar(List<Credito> lista, Anexo gest) {
        Date fecha = new Date();
        Creditogestor creditogestor = new Creditogestor();
        CredgestorDao credgestdao = new CredgestorDaoImp();
        for (int i = 0; i < lista.size(); i++) {
            Credito get = lista.get(i);
            creditogestor.setAnexo(gest);
            creditogestor.setFecreg(fecha);
            creditogestor.setCredito(get);
            credgestdao.insertarCreditogestor(creditogestor);
        }
    }

    public void borrar(List<Credito> lista, Anexo gest) {
        Creditogestor creditogestor = new Creditogestor();
        CredgestorDao credgestdao = new CredgestorDaoImp();
        for (int i = 0; i < lista.size(); i++) {
            Credito get = lista.get(i);
            creditogestor = credgestdao.getbyAnexoCredito(gest, get);
            credgestdao.eliminarCreditogestor(creditogestor);
        }
    }

    public void imprimirListaCredito(List<Credito> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Credito get = lista.get(i);
            System.out.println("Credito: " + i + " " + get.getLiqventa());
        }
    }

    public String ingresar(Anexo anexo) {
        CredgestorDao credgestor = new CredgestorDaoImp();
        credgestores = credgestor.getbyAnexo(anexo);
        creditosgest = credgestores;
        return "/venta/gestion.xhtml";
    }

    public List<SelectItem> getTiendas() {
        List<SelectItem> tiendas = new ArrayList<SelectItem>();
        tiendas.add(new SelectItem(null, "Seleccione"));
        tiendas.add(new SelectItem("T ", "TARAPOTO"));
        tiendas.add(new SelectItem("V1", "VX1"));
        tiendas.add(new SelectItem("V2", "VX2"));
        tiendas.add(new SelectItem("YA", "YARINA"));
        return tiendas;
    }

    public List<SelectItem> getEmpresas() {
        List<SelectItem> empresas = new ArrayList<SelectItem>();
        empresas.add(new SelectItem(null, "Seleccione"));
        empresas.add(new SelectItem("SE", "SEDNA"));
        empresas.add(new SelectItem("TR", "TRICAR"));
        empresas.add(new SelectItem("CA", "CASCO"));
        return empresas;
    }

    public void cargarAnexoDNI() {
        filtrada = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CredgestorDao credgestor = new CredgestorDaoImp();
        credgestores = new ArrayList();
        try {
            anexos = anexodao.buscarClienteDni(dni, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosgest.size(); j++) {
                    if (creditosgest.get(j).getCredito().getAnexo().equals(get)) {
                        Creditogestor get1 = creditosgest.get(j);
                        credgestores.add(get1);
                    }
                }
            }
        } catch (Exception e) {
        }
        dni = "";
    }

    public void cargarCreditoNombre() {
        filtrada = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CredgestorDao credgestor = new CredgestorDaoImp();
        credgestores = new ArrayList();
        try {
            anexos = anexodao.buscarClienteNombre(nombre, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosgest.size(); j++) {
                    if (creditosgest.get(j).getCredito().getAnexo().equals(get)) {
                        Creditogestor get1 = creditosgest.get(j);
                        filtrada.add(get1);
                    }
                }
            }
            RequestContext.getCurrentInstance().update("formCliente");
            RequestContext.getCurrentInstance().execute("PF('dlgcargar').show()");
        } catch (Exception e) {
        }

        nombre = "";
    }

    public void pasarCliente(Creditogestor cred) {
        credgestores = new ArrayList();
        credgestores.add(cred);
    }

    public void verTodos(Anexo anexo) {
        CredgestorDao credao = new CredgestorDaoImp();
        credgestores = new ArrayList();
        credgestores = credao.getbyAnexo(anexo);
    }

    public void visitas(Creditogestor credgestor) {
        visita = new Visitas();
        this.credgestor = credgestor;
        value = true;
        btnpago = "Registrar";
        RequestContext.getCurrentInstance().update("formvisita");
        RequestContext.getCurrentInstance().execute("PF('dlgvisita').show()");
    }

    public void compromiso(String abrev) {
        switch (abrev) {
            case "CT":
                value = false;
                break;
            case "CC":
                value = false;
                break;
            default:
                value = true;
                break;
        }
    }

    public void ingresarVisita(Usuario usuario) {
        try {
            visitasBean vbean = new visitasBean();
            visita.setUsuario(usuario.getIdusuario());
            visita.setDescripcion(descripcion(visita.getAbrev()));
            if (visita.getAbrev().equals("CC") || visita.getAbrev().equals("CT")){
                visita.setCompromiso(true);
            } else {
                visita.setCompromiso(false);
            }
            visita.setCreditogestor(credgestor);
            vbean.insertar(visita);
            RequestContext.getCurrentInstance().update("formvisita");
            RequestContext.getCurrentInstance().execute("PF('dlgvisita').hide()");
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("formvisita");
            RequestContext.getCurrentInstance().execute("PF('dlgvisita').show()");
        }
    }

    public String descripcion(String abrev) {
        String desc = new String();
        switch (abrev) {
            case "CT":
                desc = "Contacto con Titular";
                break;
            case "CC":
                desc = "Contacto con Cónyuge";
                break;
            case "CF":
                desc = "Contacto con Familiar";
                break;
            case "C3":
                desc = "Contacto con Terceros";
                break;
            case "FA":
                desc = "Fallecido";
                break;
            case "BP":
                desc = "Bajo puerta";
                break;
            case "IN":
                desc = "Inubicable";
                break;

        }
        return desc;
    }

    public List<Creditogestor> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Creditogestor> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

    public Creditogestor getCredgestor() {
        return credgestor;
    }

    public void setCredgestor(Creditogestor credgestor) {
        this.credgestor = credgestor;
    }

    public List<Creditogestor> getCredgestores() {
        return credgestores;
    }

    public void setCredgestores(List<Creditogestor> credgestores) {
        this.credgestores = credgestores;
    }

    public String getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(String btnguardar) {
        this.btnguardar = btnguardar;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public List<Anexo> getGestores() {
        AnexoDao andao = new AnexoDaoImplements();
        gestores = andao.filtarTipo("GE");
        return gestores;
    }

    public void setGestores(List<Anexo> gestores) {
        this.gestores = gestores;
    }

    public List<Credito> getCreditos() {
//        CreditoDao credao = new CreditoDaoImp();
//        creditos = credao.cargarxEstadoDistinto("CN");
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public List<Credito> getSelectedcreditos() {
        return selectedcreditos;
    }

    public void setSelectedcreditos(List<Credito> selectedcreditos) {
        this.selectedcreditos = selectedcreditos;
    }

    public List<Credito> getSelectedcreditosmostrar() {
        return selectedcreditosmostrar;
    }

    public void setSelectedcreditosmostrar(List<Credito> selectedcreditosmostrar) {
        this.selectedcreditosmostrar = selectedcreditosmostrar;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Creditogestor> getFiltrada() {
        return filtrada;
    }

    public void setFiltrada(List<Creditogestor> filtrada) {
        this.filtrada = filtrada;
    }

    public List<Creditogestor> getSelectedfiltrada() {
        return selectedfiltrada;
    }

    public void setSelectedfiltrada(List<Creditogestor> selectedfiltrada) {
        this.selectedfiltrada = selectedfiltrada;
    }

    public List<Creditogestor> getCreditosgest() {
        return creditosgest;
    }

    public void setCreditosgest(List<Creditogestor> creditosgest) {
        this.creditosgest = creditosgest;
    }

    public Visitas getVisita() {
        return visita;
    }

    public void setVisita(Visitas visita) {
        this.visita = visita;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getBtnpago() {
        return btnpago;
    }

    public void setBtnpago(String btnpago) {
        this.btnpago = btnpago;
    }

    public List<Visitas> getVisitashistorial() {
        return visitashistorial;
    }

    public void setVisitashistorial(List<Visitas> visitashistorial) {
        this.visitashistorial = visitashistorial;
    }

}
