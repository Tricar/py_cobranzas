package Bean;

import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Dao.PagosDao;
import Dao.PagosDaoImp;
import Dao.VehiculoDao;
import Dao.VehiculoDaoImplements;
import Model.Anexo;
import Model.Credito;
import Model.Letras;
import Model.Pagos;
import Model.Vehiculo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utiles.inicial;
import utiles.precio;

@ManagedBean
@SessionScoped
public class creditoBean implements Serializable {

    public Credito credito = new Credito();
    public Vehiculo vehiculo = new Vehiculo();
    public Credito crediton = new Credito();
    public Letras letrasa = new Letras();
    public List<Credito> creditos = new ArrayList();
    LetrasDao letrasdao = new LetrasDaoImplements();
    public List<Letras> letrascredito;
    public List<Credito> filtradafecha;
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();
    private List<Letras> letraslista = new ArrayList();
    private Pagos pago = new Pagos();
    private Letras letra = new Letras();
    private String dni;
    private BigDecimal res;
    private BigDecimal precio;
    private BigDecimal inicia;
    private String codigo;
    private Date fechafin;
    private String nombre;
    private BigDecimal saldo;
    private String generar = "no";

    public creditoBean() {
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Letras getLetrasa() {
        return letrasa;
    }

    public void setLetrasa(Letras letrasa) {
        this.letrasa = letrasa;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Credito> getVentas() {
        CreditoDao linkdao = new CreditoDaoImp();
        creditos = linkdao.mostrarVentas();
        return creditos;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public List<Letras> getLetrascredito() {
        LetrasDao linkdao = new LetrasDaoImplements();
        letrascredito = linkdao.mostrarLetrasXCred(credito);
        return letrascredito;
    }

    public List<Credito> getFiltradafecha() {
        return filtradafecha;
    }

    public void setVentas(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public LetrasDao getLetras() {
        return letrasdao;
    }

    public void setLetras(LetrasDao letrasdao) {
        this.letrasdao = letrasdao;
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

    public List<Letras> getLetraslista() {
        return letraslista;
    }

    public void setLetraslista(List<Letras> letraslista) {
        this.letraslista = letraslista;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public Letras getLetra() {
        return letra;
    }

    public void setLetra(Letras letra) {
        this.letra = letra;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getRes() {
        return res;
    }

    public void setRes(BigDecimal res) {
        this.res = res;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getInicia() {
        return inicia;
    }

    public void setInicia(BigDecimal inicia) {
        this.inicia = inicia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Credito getCrediton() {
        return crediton;
    }

    public void setCrediton(Credito crediton) {
        this.crediton = crediton;
    }

    public String getGenerar() {
        return generar;
    }

    public void setGenerar(String generar) {
        this.generar = generar;
    }

    public void resultadoSaldo() {
        res = precio.subtract(credito.getInicial());
    }

    public void precioModeloCredito() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));
        String distrito = credito.getAnexoByIdanexo().getDistrito();
        String tipov = credito.getVehiculo().getTipovehiculo();
        inicia = Inicial.inicialCredito(distrito, tipov, precio);
        saldo = precio.subtract(inicia);
    }

    public void precioModeloCreditoEspecial() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getVehiculo().getModelo().getModelo()));
    }

    public void precioModeloCotiza() {
        precio Precio = new precio();
        inicial Inicial = new inicial();
        precio = (Precio.precioModelo(credito.getModelo().getModelo()));
        String distrito = credito.getAnexoByIdanexo().getDistrito();
        String tipov = credito.getVehi();
        inicia = Inicial.inicialCredito(distrito, tipov, precio);
        saldo = precio.subtract(inicia);
    }

    public void insertarCredito() {
        CreditoDao creditodao = new CreditoDaoImp();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        credito.setPrecio(precio);
        credito.setInicial(inicia);
        credito.setSaldo(precio.subtract(inicia));
        if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
            Letras letras = new Letras();
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("N");
            credito.setTotaldeuda(BigDecimal.ZERO);
            BigDecimal nletras = new BigDecimal(credito.getNletras());
            BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
            BigDecimal interes = new BigDecimal(0);
            credito.setEstado("AP");
            BigDecimal cien = new BigDecimal(100);
            creditodao.insertarVenta(credito);
            vehiculodao.modificarVehiculo(vehiculo);
            interes = (credito.getInteres().multiply(nletras)).divide(cien);
            Date fechaini = new Date();
            fechaini = credito.getFechareg();
            Date fechafin = new Date();
            fechafin = sumaDias(fechaini, 30);
            BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
            letras.setFecreg(credito.getFechareg());
            for (int i = 1; i <= (credito.getNletras()); i++) {
                letras.setCredito(credito);
                letras.setMontoletra(montoletras);
                letras.setInteres(mtointeres);
                letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                letras.setFecini(fechaini);
                letras.setFecven(fechafin);
                letras.setMora(BigDecimal.ZERO);
                fechaini = fechafin;
                fechafin = sumaDias(fechaini, 30);
                letras.setSaldo(letras.getMonto());
                letras.setEstado("PN");
                letras.setDescripcion("L" + i + "/L" + credito.getNletras());
                credito.setTotaldeuda(credito.getTotaldeuda().add(letras.getSaldo()));
                credito.setDeudactual(credito.getTotaldeuda());
                letrasdao.insertarLetra(letras);
            }
            creditodao.modificarVenta(credito);
//            generar = "generado";
        }
    }

    public void insertarCreditoEspecial() {
        CreditoDao creditodao = new CreditoDaoImp();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
        Vehiculo vehiculo = new Vehiculo();
        credito.setPrecio(precio);
        credito.setSaldo(res);
        credito.setTotaldeuda(credito.getSaldo());
        credito.setDeudactual(credito.getSaldo());
        credito.setInteres(BigDecimal.ZERO);

        if (credito.getSaldo().compareTo(BigDecimal.ZERO) == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese Monto Inicial Mayor."));
            return;
        }

        else if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("N");
            vehiculodao.modificarVehiculo(vehiculo);
            credito.setTotaldeuda(credito.getSaldo());
            Letras letras = new Letras();
            BigDecimal nletras = new BigDecimal(BigInteger.ONE);
            BigDecimal montoletras = credito.getSaldo();
            BigDecimal interes = new BigDecimal(0);
            credito.setEstado("AP");
            creditodao.insertarVenta(credito);
            Date fechaini = new Date();
            fechaini = credito.getFechareg();
            Date fechafin = new Date();
            fechafin = sumaDias(fechaini, 45);
            BigDecimal mtointeres = BigDecimal.ZERO;
            letras.setFecreg(credito.getFechareg());
            letras.setCredito(credito);
            letras.setMontoletra(montoletras);
            letras.setInteres(mtointeres);
            letras.setMonto(credito.getSaldo());
            letras.setFecini(fechaini);
            letras.setFecven(fechafin);
            letras.setMora(BigDecimal.ZERO);
            letras.setSaldo(letras.getMonto());
            letras.setEstado("PN");
            letras.setDescripcion("L1/L1");
            letrasdao.insertarLetra(letras);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro fue satisfactorio."));
        }
    }

    public void refinanciar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        List<Letras> antiguas = new ArrayList();
        Letras nuevas = new Letras();
        crediton.setFechareg(credito.getFechareg());
        crediton.setAnexoByIdaval(credito.getAnexoByIdaval());
        crediton.setAnexoByIdanexo(credito.getAnexoByIdanexo());
        crediton.setAnexoByCodven(credito.getAnexoByCodven());
        crediton.setAnexoByVerificado(credito.getAnexoByVerificado());
        crediton.setCondicionpago(credito.getCondicionpago());
        crediton.setContrato(credito.getContrato());
        crediton.setCronograma(credito.getCronograma());
        crediton.setEmpresa(credito.getEmpresa());
        crediton.setTienda(credito.getTienda());
        crediton.setInicial(BigDecimal.ZERO);
        crediton.setPrecio(BigDecimal.ZERO);
        crediton.setVehiculo(credito.getVehiculo());
        crediton.setSaldo(credito.getDeudactual());
        crediton.setTotaldeuda(BigDecimal.ZERO);
        if (credito.getLiqventa().endsWith("A")) {
            crediton.setLiqventa(credito.getLiqventa() + "A");
        } else {
            crediton.setLiqventa(credito.getLiqventa() + "-A");
        }
        antiguas = letrasdao.mostrarLetrasXCred(credito);
        for (int i = 0; i < antiguas.size(); i++) {
            Letras get = antiguas.get(i);
            get.setSaldo(BigDecimal.ZERO);
            get.setEstado("RF");
            get.setDescripcion("Ref. :" + crediton.getLiqventa());
            letrasdao.modificarLetra(get);
        }
        credito.setDeudactual(BigDecimal.ZERO);
        creditodao.modificarVenta(credito);
        BigDecimal nletras = new BigDecimal(crediton.getNletras());
        BigDecimal montoletras = crediton.getSaldo().divide(nletras, 2);
        BigDecimal interes = new BigDecimal(0);
        crediton.setEstado("AP");
        creditodao.insertarVenta(crediton);
        BigDecimal cien = new BigDecimal(100);
        interes = (crediton.getInteres().multiply(nletras)).divide(cien);
        Date fechaini = new Date();
        fechaini = crediton.getFechareg();
        Date fechafin = new Date();
        fechafin = sumaDias(fechaini, 30);
        BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
        nuevas.setFecreg(crediton.getFechareg());
        for (int i = 1; i <= (crediton.getNletras()); i++) {
            nuevas.setCredito(crediton);
            nuevas.setMontoletra(montoletras);
            nuevas.setInteres(mtointeres);
            nuevas.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
            nuevas.setFecini(fechaini);
            nuevas.setFecven(fechafin);
            nuevas.setMora(BigDecimal.ZERO);
            fechaini = fechafin;
            fechafin = sumaDias(fechaini, 30);
            nuevas.setSaldo(nuevas.getMonto());
            nuevas.setEstado("PN");
            nuevas.setDescripcion("L" + i + "/L" + crediton.getNletras());
            crediton.setTotaldeuda(crediton.getTotaldeuda().add(nuevas.getSaldo()));
            crediton.setDeudactual(crediton.getTotaldeuda());
            letrasdao.insertarLetra(nuevas);
        }
        creditodao.modificarVenta(crediton);
    }

    public void insertarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        credito.setPrecio(precio);
        credito.setInicial(inicia);
        credito.setSaldo(precio.subtract(inicia));
        if (credito.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
            Letras letras = new Letras();
            BigDecimal nletras = new BigDecimal(credito.getNletras());
            BigDecimal montoletras = credito.getSaldo().divide(nletras, 2);
            BigDecimal interes = new BigDecimal(0);
            BigDecimal cien = new BigDecimal(100);
            credito.setEstado("NA");
            creditodao.insertarVenta(credito);
            interes = (credito.getInteres().multiply(nletras)).divide(cien);
            Date fechaini = new Date();
            fechaini = credito.getFechareg();
            Date fechafin = new Date();
            fechafin = sumaDias(fechaini, 30);
            BigDecimal mtointeres = (montoletras.multiply(interes)).setScale(1, RoundingMode.UP);
            letras.setFecreg(credito.getFechareg());
            for (int i = 1; i <= (credito.getNletras()); i++) {
                letras.setCredito(credito);
                letras.setMontoletra(montoletras);
                letras.setInteres(mtointeres);
                letras.setMonto((montoletras.add(mtointeres).setScale(2)).setScale(1, RoundingMode.UP));
                letras.setFecini(fechaini);
                letras.setFecven(fechafin);
                fechaini = fechafin;
                fechafin = sumaDias(fechaini, 30);
                letras.setSaldo(montoletras.add(mtointeres));
                letras.setEstado("NA");
                letras.setDescripcion("L" + i + "/L" + credito.getNletras());
                letrasdao.insertarLetra(letras);
            }
        }
    }

    public void modificar() {
        CreditoDao linkDao = new CreditoDaoImp();
        linkDao.modificarVenta(credito);
        credito = new Credito();
    }

    public void eliminar() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
//        List<Letras> letrita = new ArrayList();
        Vehiculo vehiculo = new Vehiculo();
        VehiculoDao vehiculodao = new VehiculoDaoImplements();
//        letrita = letrasdao.mostrarLetrasXCred(credito);
//        BigDecimal totaldeuda = new BigDecimal(BigInteger.ZERO);
//        for (int i = 0; i < letrita.size(); i++) {
//            Letras get = letrita.get(i);
//            totaldeuda = totaldeuda.add(get.getMonto());
//        }
        if (credito.getDeudactual().compareTo(credito.getTotaldeuda()) == 0) {
            vehiculo = credito.getVehiculo();
            vehiculo.setEstado("D");
            vehiculodao.modificarVehiculo(vehiculo);
            creditodao.eliminarVenta(credito);
        } else {
            System.out.println("Mostrar Error de que el credito ya ha sido cobrado y no se puede borrar");
        }
        credito = new Credito();
        creditos = new ArrayList();
    }

    public void eliminarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        creditodao.eliminarVenta(credito);
        credito = new Credito();
        creditos = new ArrayList();
    }

    public Date sumaDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
    }

    public void filtrarFechas() {
        CreditoDao linkdao = new CreditoDaoImp();
        filtradafecha = linkdao.filtrarFechas(fecha1, fecha2, "AP");
        letraslista = new ArrayList();
    }

    public void cargarCredito(Anexo anexo) {
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal cien = new BigDecimal((BigInteger.TEN).multiply(BigInteger.TEN));
        cinco = cinco.divide(cien);
        credito = creditodao.cargarCreditoxAnexo(anexo);
        LetrasDao letras = new LetrasDaoImplements();
        letritas = letras.mostrarLetrasXCred(credito);
        for (int i = 0; i < letritas.size(); i++) {
            Letras get = letritas.get(i);
            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                get.setEstado("CN");
            } else {
                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
                    if (get.getFecven().after(fecha)) {
                        get.setEstado("PN");
                    } else {
                        get.setEstado("VN");
                    }
                }
            }
            moraant = get.getMora();
            moraact = ((get.getSaldo().multiply(cinco)).setScale(2)).setScale(1, RoundingMode.UP);
            if (get.getEstado().equals("VN")) {
                if (moraact.compareTo(moraant) == -1) {
                    get.setMora(moraant);
                } else {
                    get.setMora(moraact);
                }
            }
            letrasdao.modificarLetra(get);
        }
        letraslista = letras.mostrarLetrasXCred(credito);
    }

    public void cargarLetras(Credito cred) {
        letraslista = new ArrayList();
//        credito = cred;
        CreditoDao creditodao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal cien = new BigDecimal(100);
        cinco = cinco.divide(cien);
//        credito = creditodao.cargarCreditoxAnexo(anexo);
        LetrasDao letrasdao = new LetrasDaoImplements();
        letritas = letrasdao.mostrarLetrasXCred(cred);
        for (int i = 0; i < letritas.size(); i++) {
            Letras get = letritas.get(i);
            if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                get.setEstado("CN");
            } else {
                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
                    if (get.getFecven().after(fecha)) {
                        get.setEstado("PN");
                    } else {
                        get.setEstado("VN");
                    }
                }
            }
            moraant = get.getMora();
            moraact = (get.getSaldo().multiply(cinco)).setScale(1, RoundingMode.UP);
            if (get.getEstado().equals("VN")) {
                if (moraact.compareTo(moraant) == -1) {
                    get.setMora(moraant);
                } else {
                    get.setMora(moraact);
                }
            }
            letrasdao.modificarLetra(get);
        }
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public void cargarLetrasCotiza(Credito cred) {
        letraslista = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        letraslista = letrasdao.mostrarLetrasXCred(cred);
    }

    public void insertarPago() {
        PagosDao linkDao = new PagosDaoImp();
        linkDao.insertarPago(pago);
        pago = new Pagos();
    }

    public void insertarNotaDebito() {
        LetrasDao letrasdao = new LetrasDaoImplements();
        Date d = new Date();
        CreditoDao creditodao = new CreditoDaoImp();
        letra.setCredito(credito);
        letra.setDescripcion("ND");
        letra.setFecreg(d);
        letra.setFecven(fechafin);
        letra.setMontoletra(BigDecimal.ZERO);
        letra.setInteres(BigDecimal.ZERO);
        letra.setSaldo(letra.getMonto());
        letra.setEstado("PN");
        credito.setTotaldeuda(credito.getTotaldeuda().add(letra.getMonto()));
        creditodao.modificarVenta(credito);
        letrasdao.insertarLetra(letra);
    }

    public void cargarAnexoDNI() {
        filtradafecha = new ArrayList();
        letraslista = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Credito> creditosn = creditodao.mostrarVentas();
        try {
            anexos = anexodao.buscarClienteDni(dni, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosn.size(); j++) {
                    if (creditosn.get(j).getAnexoByIdanexo().equals(get)) {
                        Credito get1 = creditosn.get(j);
                        filtradafecha.add(get1);
                    }
                }
            }
        } catch (Exception e) {
        }
        dni = "";
    }

    public void cargarCreditoNombre() {
        filtradafecha = new ArrayList();
        letraslista = new ArrayList();
        AnexoDao anexodao = new AnexoDaoImplements();
        List<Anexo> anexos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        List<Credito> creditosn = creditodao.mostrarVentas();
        try {
            anexos = anexodao.buscarClienteNombre(nombre, "CN", "CJ");
            for (int i = 0; i < anexos.size(); i++) {
                Anexo get = anexos.get(i);
                for (int j = 0; j < creditosn.size(); j++) {
                    if (creditosn.get(j).getAnexoByIdanexo().equals(get)) {
                        Credito get1 = creditosn.get(j);
                        filtradafecha.add(get1);
                    }
                }
            }
        } catch (Exception e) {
        }
        nombre = "";
    }

    public void cargarxCodigoCredito() {
        creditos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEstado(codigo, "AP");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void cargarxCodigoCotiza() {
        creditos = new ArrayList();
        CreditoDao creditodao = new CreditoDaoImp();
        Credito modelocredito = new Credito();
        try {
            modelocredito = creditodao.cargarxCodigoEstado(codigo, "NA");
            creditos.add(modelocredito);
            if (creditos.get(0) == null) {
                creditos = null;
            }
        } catch (Exception e) {
        }
        codigo = "";
    }

    public void aprobarCotiza() {
        CreditoDao creditodao = new CreditoDaoImp();
        LetrasDao letrasdao = new LetrasDaoImplements();
        letrascredito = letrasdao.mostrarLetrasXCred(credito);
        Letras letra = new Letras();
        BigDecimal interes = new BigDecimal(0);
        for (int i = 0; i < credito.getNletras(); i++) {
            letra = (Letras) letrascredito.get(i);
            letra.setFecini(credito.getFechareg());
            letra.setFecven(sumaDias(letra.getFecini(), 30));
            letra.setEstado("PN");
            letrasdao.modificarLetra(letra);
            letra.setFecini(letra.getFecven());
            interes = interes.add(letra.getMonto());
        }
        credito.setTotaldeuda(interes);
        credito.setDeudactual(credito.getTotaldeuda());
        credito.setEstado("AP");
        creditodao.modificarVenta(credito);
        credito = new Credito();
    }

    public void resultadoFecha() {
        fechafin = sumaDias(letra.getFecini(), 30);
    }

    public String nuevo() {
        credito = new Credito();
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        return "/venta/form.xhtml";

    }

    public String pagos() {
        creditos = new ArrayList();
        return "/venta/listarv.xhtml";
    }

    public String nuevoespecial() {
        credito = new Credito();
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        return "/venta/formespecial.xhtml";
    }

    public String index() {
        creditos = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/venta/index.xhtml";
    }

    public String nuevocotiza() {
        credito = new Credito();
        precio = BigDecimal.ZERO;
        inicia = BigDecimal.ZERO;
        res = BigDecimal.ZERO;
        saldo = BigDecimal.ZERO;
        return "/cotiza/form.xhtml";
    }

    public String indexcotiza() {
        creditos = new ArrayList();
        filtradafecha = new ArrayList();
        codigo = "";
        return "/cotiza/index.xhtml";
    }
}
