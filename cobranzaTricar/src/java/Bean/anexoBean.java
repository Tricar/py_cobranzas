package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Anexo;
import Model.Credito;
import Persistencia.HibernateUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@SessionScoped
public class anexoBean implements Serializable {

    private Session session;
    private Transaction transaction;
    public Anexo anexo = new Anexo();
    public List<Anexo> anexos;
    private List<SelectItem> SelectItemsAnexo;
    private List<Anexo> query;
    private List<Anexo> filtradaEnter = new ArrayList();
    private List<Anexo> filtradaCredito = new ArrayList();
    private String text;
    private String nombre;
    private String dni = "";
    private List<Credito> creditos = new ArrayList();
    private String razonsocial;
    private String var;
    private int año;
    private String fecnac;
    public List<Anexo> anexosfiltrados;
    private String numdigitos;
    private Boolean empresa;
    private Boolean persona;
    private Boolean conyuge;
    private String dir;

    public anexoBean() {
    }

    public List<Anexo> verCliente() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = anexoDao.verCliente(this.session);
            this.transaction.commit();
            return anexos;
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

    public List<Anexo> verProveedor() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDao anexoDao = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = anexoDao.verProveedor(this.session);
            this.transaction.commit();
            return anexos;
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

    public List<Anexo> verAval() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = anexoDao.verAval(this.session);
            this.transaction.commit();
            return anexos;
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

    public void nuevoanexo() {
        año = 0;
        fecnac = new String();
        anexo = new Anexo();
        empresa = true;
        persona = true;
        conyuge = true;
        RequestContext.getCurrentInstance().update("formInsertar");
        RequestContext.getCurrentInstance().execute("PF('dlginsert').show()");
    }

    public void dir(String dir) {
        this.dir = dir;
    }

    public void calcularEdad(String fecha) throws ParseException {
        Date fechaNac = null;
//        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
//        Date date;
//        date = (Date) formatter.parse(fecha.toString());
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        String formatedDate = cal.get(Calendar.DATE) + "-"
//                + (cal.get(Calendar.MONTH) + 1)
//                + "-" + cal.get(Calendar.YEAR);
        fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
    }

    public List<Anexo> verEmpleado() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = anexoDao.verEmpleado(this.session);
            this.transaction.commit();
            return anexos;
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

    public List<Anexo> verVendedores() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements anexoDao = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = anexoDao.verVendedor(this.session);
            this.transaction.commit();
            return anexos;
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

    public void insertar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (daotanexo.verByDocumento(this.session, this.anexo.getNumdocumento()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Empleado ya existe en DB."));
                this.anexo = new Anexo();
                return;
            }
            this.anexo.setConyuge("");
            this.anexo.setDniconyu("");
            this.anexo.setTipodocumento("DNI");
            Date d = new Date();
            this.anexo.setFechareg(d);
            daotanexo.registrar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void insertarcliente() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (this.razonsocial != null) {
                this.anexo.setNombre(this.razonsocial);
                this.anexo.setApemat("");
                this.anexo.setApepat("");
            }
            if (this.anexo.getTipodocumento().equals("DNI")) {
                this.anexo.setTipoanexo("CN");
            } else {
                this.anexo.setTipoanexo("CJ");
            }
            Date d = new Date();
            this.anexo.setFechareg(d);
            this.anexo.setCodven("");
            Date fechaNac = null;
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecnac);
            this.anexo.setFechanac(fechaNac);
            calcularEdad(fecnac);
            this.anexo.setEdad(año);
            if (anexo.getEstcivil().equals("CA") || anexo.getEstcivil().equals("CO")) {
                anexo.setDireccionconyu(dir);
            }
            daotanexo.registrar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void insertaraval() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (año < 25 || año > 65) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede ser aval por restricción de edad."));
                this.anexo = new Anexo();
                return;
            }
            this.anexo.setTipodocumento("DNI");
            this.anexo.setTipoanexo("AV");
            Date d = new Date();
            this.anexo.setFechareg(d);
            this.anexo.setCodven("");
            daotanexo.registrar(this.session, this.anexo);
            this.transaction.commit();
            Date fechaNac = null;
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecnac);
            this.anexo.setFechanac(fechaNac);
            calcularEdad(fecnac);
            this.anexo.setEdad(año);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void modificar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (this.razonsocial != null) {
                this.anexo.setNombre(this.razonsocial);
                this.anexo.setApemat("");
                this.anexo.setApepat("");
            }
            if (this.anexo.getTipoanexo() == null) {
                if (this.anexo.getTipodocumento().equals("DNI")) {
                    this.anexo.setTipoanexo("CN");
                } else if (this.anexo.getTipodocumento().equals("RUC")) {
                    this.anexo.setTipoanexo("CJ");
                }
            }
            Date fechaNac = null;
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecnac);
            this.anexo.setFechanac(fechaNac);
            this.anexo.setEdad(año);
            daotanexo.modificar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void modificarProveedor() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (daotanexo.verByDocumentoDifer(this.session, this.anexo.getIdanexo(), this.anexo.getNumdocumento()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Proveedor ya Existe."));
                return;
            }

            daotanexo.modificar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void modificarAval() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            if (daotanexo.verByDocumentoDifer(this.session, this.anexo.getIdanexo(), this.anexo.getNumdocumento()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El Anexo ya Existe."));
                return;
            }

            daotanexo.modificar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La Actualizacion fue satisfactorio."));
            this.anexo = new Anexo();
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "Por favor contacte con su administrador " + e.getMessage()));
            this.anexo = new Anexo();
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
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            daotanexo.eliminar(this.session, this.anexo);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Elimino el Anexo Correctamente."));
            this.anexo = new Anexo();
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

    public void cargarAnexoEditar(int idanexo) throws ParseException {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            System.out.println("ID anexo: " + idanexo);
            anexo = daotanexo.verByIdanexo(this.session, idanexo);
            actualizarCampos(anexo.getTipodocumento());
            datosConyu(anexo.getEstcivil());
            año = anexo.getEdad();
            this.transaction.commit();
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
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fecha.format(anexo.getFechanac());
        fecnac = convertido;
        RequestContext.getCurrentInstance().update("frmEditarAnexo:panelEditarAnexo");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditarAnexo').show()");
    }

    public void cargarAvalEditar(int idanexo) throws ParseException {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            anexo = daotanexo.verByIdanexo(this.session, idanexo);
            this.transaction.commit();
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
        RequestContext.getCurrentInstance().update("frmEditarAnexo:panelEditarAnexo");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditarAnexo').show()");
    }

    public void cargarAnexoEliminar(int idanexo) {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.anexo = daotanexo.verByIdanexo(this.session, idanexo);
            this.transaction.commit();
            RequestContext.getCurrentInstance().update("frmEliminarAnexo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEliminarAnexo').show()");
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

    public List<Anexo> filtrarCliente(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("CN", "CJ");
        for (Anexo tipo : tipos) {
//            var = tipo.getNombre()+" "+tipo.getApepat()+""+tipo.getApemat();
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarClienteUnidad(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("CN", "CJ");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarProveedor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipo("PO");
        for (Anexo tipo : tipos) {
//            var = tipo.getNombre()+" "+tipo.getApepat()+""+tipo.getApemat();
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarVendedor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipo("VE");
        for (Anexo tipo : tipos) {
            if (tipo.getNombres().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarGestor(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("GE", "JE");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> getGestor() {
        List<Anexo> gestors = new ArrayList();
        AnexoDao andao = new AnexoDaoImplements();
        gestors = andao.filtarTipo("GE");
        return gestors;
    }

    public List<Anexo> filtrarJefe(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoDos("JE", "AD");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarAval(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoTres("VE", "AV", "AD");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Anexo> filtrarEmpleado(String name) {
        this.query = new ArrayList<Anexo>();
        AnexoDao anexoDao = new AnexoDaoImplements();
        List<Anexo> tipos = anexoDao.filtarTipoTres("VE", "AS", "AD");
        for (Anexo tipo : tipos) {
            if (tipo.getNombre().startsWith(name.toUpperCase())) {
                query.add(tipo);
            }
        }
        return query;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void filtrarClientEnter() {
        CreditoDao creditodao = new CreditoDaoImp();
        creditos = creditodao.mostrarVentas();
    }

    public List<Anexo> getFiltradaCredito() {
        Credito credito = new Credito();
        AnexoDao anexodao = new AnexoDaoImplements();
        Anexo anexito = new Anexo();
        filtradaCredito = new ArrayList();
        int sw = 0;
        for (int i = 0; i < creditos.size(); i++) {
            credito = creditos.get(i);
            if (dni.equals("")) {
                filtradaCredito = new ArrayList();
            } else if (credito.getAnexo().getNumdocumento().startsWith(dni)) {
                sw = 1;
                anexito = anexodao.cargarxCredito(credito.getAnexo().getIdanexo());
                filtradaCredito.add(anexito);
            }
        }
        if (sw == 1) {
            return filtradaCredito;
        } else {
            filtradaCredito = new ArrayList();
        }
        return filtradaCredito;
    }

    public List<Anexo> getAnexos() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDaoImplements daotanexo = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = daotanexo.verTodo(this.session);
            this.transaction.commit();
            return anexos;
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
    
    public List<Anexo> verTodo() {
        this.session = null;
        this.transaction = null;
        try {
            AnexoDao daocolor = new AnexoDaoImplements();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.anexos = daocolor.verTodo(this.session);
            this.transaction.commit();
            return anexos;
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

    public List<Anexo> completarTipo(String nombre) {
        AnexoDao tipodao = new AnexoDaoImplements();
        anexos = tipodao.buscarxNombre(nombre);
        return anexos;
    }

    public List<SelectItem> getSelectItemsAnexo() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.SelectItemsAnexo = new ArrayList<SelectItem>();
            AnexoDao anexoDao = new AnexoDaoImplements();
            List<Anexo> tipos = anexoDao.verTodo(null);
            for (Anexo tipo : tipos) {
                SelectItem selecItem = new SelectItem(tipo, tipo.getNombre());
                this.SelectItemsAnexo.add(selecItem);
            }
            return SelectItemsAnexo;
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

    public void msjaval() {
        if (anexo.getCpropia().equals("NO")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NOTA:", "Cliente debe presentar aval o garante"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NOTA:", "Cliente NO necesitará aval o garante"));
        }
    }

    public void actualizarCampos(String tipodoc) {
        if (tipodoc.equals("DNI")) {
            numdigitos = "99999999";
            empresa = true;
            persona = false;
        } else {
            numdigitos = "99999999999";
            empresa = false;
            persona = true;
        }
        System.out.println("ejecuté campos");
    }

    public void datosConyu(String estcivil) {
        if (estcivil.equals("CO") || estcivil.equals("CA")) {
            conyuge = false;
        } else {
            conyuge = true;
        }
        System.out.println("ejecuté conyuge");
    }

    public void handleKeyEvent() {
        text = text.toUpperCase();
    }

    public void handleSelect(SelectEvent e) {
        Anexo p = (Anexo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Anexo agregado :: " + p.getNombre() + " " + p.getApepat() + " " + p.getApemat(), ""));
    }

    public void handleUnSelect(UnselectEvent e) {
        Anexo p = (Anexo) e.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Anexo removido :: " + p.getNombre() + " " + p.getApepat() + " " + p.getApemat(), ""));
    }

    public void phaseListener(PhaseEvent e) {
        List<FacesMessage> messages = e.getFacesContext().getMessageList();
        System.out.println(messages.size());
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public List<Anexo> getFiltradaEnter() {
        return filtradaEnter;
    }

    public void setFiltradaEnter(List<Anexo> filtradaEnter) {
        this.filtradaEnter = filtradaEnter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setAnexo(List<Anexo> anexo) {
        this.anexos = anexo;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getFecnac() {
        return fecnac;
    }

    public void setFecnac(String fecnac) {
        this.fecnac = fecnac;
    }

    public List<Anexo> getAnexosfiltrados() {
        return anexosfiltrados;
    }

    public void setAnexosfiltrados(List<Anexo> anexosfiltrados) {
        this.anexosfiltrados = anexosfiltrados;
    }

    public String getNumdigitos() {
        return numdigitos;
    }

    public void setNumdigitos(String numdigitos) {
        this.numdigitos = numdigitos;
    }

    public Boolean getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Boolean empresa) {
        this.empresa = empresa;
    }

    public Boolean getPersona() {
        return persona;
    }

    public void setPersona(Boolean persona) {
        this.persona = persona;
    }

    public Boolean getConyuge() {
        return conyuge;
    }

    public void setConyuge(Boolean conyuge) {
        this.conyuge = conyuge;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
