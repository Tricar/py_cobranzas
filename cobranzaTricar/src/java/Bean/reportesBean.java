package Bean;

import Dao.CredavalDao;
import Dao.CredavalDaoImp;
import Model.Anexo;
import Model.Credito;
import Model.Creditoaval;
import Model.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.apache.commons.lang3.StringUtils;
import utiles.dbManager;
import utiles.recorrerCreditos;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class reportesBean implements Serializable {

    private String codigo;
    private List<Anexo> avales = new ArrayList();
    private Integer mes;
    private Integer ano;
    private String empresa;
    private Date fecha = new Date();
    private Date fecha1 = new Date();
    private Date fecha2 = new Date();

    /**
     * Creates a new instance of reportesBean
     */
    public reportesBean() {
    }

    public void calcularConsolidado() {

    }

    public void exportarProf(String codigo) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(codigo)) {
            parametros.put("codigo", codigo);
            File jasper = new File("D:/reporte/proforma.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Proforma" + codigo + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Crear proforma primero."));
        }
        con.close();
    }

    public void exportarVenta() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha", fecha);
        File jasper = new File("D:/reporte/venta.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Venta-" + fecha + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void cierreIngreso() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Integer tienda1 = null;
        Integer tienda2 = null;
        Integer tienda3 = null;
        String tienda = null;
        String meses = null;
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (mes == null || ano == null || empresa == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar mes, año y/o empresa."));
        } else {
            if (empresa.equals("CR")) {
                tienda1 = 4;
                tienda2 = 5;
                tienda3 = 10;
                tienda = "CASCO RED";
            } else if (empresa.equals("TR")) {
                tienda1 = 8;
                tienda2 = 0;
                tienda3 = 11;
                tienda = "TRICAR";
            } else {
                tienda1 = 0;
                tienda2 = 9;
                tienda3 = 0;
                tienda = "SEDNA";
            }
            switch (mes) {
                case 1:
                    meses = "ENERO";
                    break;
                case 2:
                    meses = "FEBRERO";
                    break;
                case 3:
                    meses = "MARZO";
                    break;
                case 4:
                    meses = "ABRIL";
                    break;
                case 5:
                    meses = "MAYO";
                    break;
                case 6:
                    meses = "JUNIO";
                    break;
                case 7:
                    meses = "JULIO";
                    break;
                case 8:
                    meses = "AGOSTO";
                    break;
                case 9:
                    meses = "SETIEMBRE";
                    break;
                case 10:
                    meses = "OCTUBRE";
                    break;
                case 11:
                    meses = "NOVIEMBRE";
                    break;
                case 12:
                    meses = "DICIEMBRE";
                    break;
            }
            parametros.put("mes", mes);
            parametros.put("ano", ano);
            parametros.put("tienda1", tienda1);
            parametros.put("tienda2", tienda2);
            parametros.put("tienda3", tienda3);
            parametros.put("tienda", tienda);
            parametros.put("meses", meses);
            File jasper = new File("D:/reporte/cierreingreso.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=CierreIngreso-" + mes + "-" + ano + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }
        con.close();
    }

    public void comisiones(Usuario usuario) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Integer tienda1 = null;
        Integer tienda2 = null;
        Integer tienda3 = null;
        BigDecimal comiprimer;
        BigDecimal comiseg;
        BigDecimal comiter;
        BigDecimal comicua;
        String tienda = null;
        String meses = null;
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (usuario.getPerfil().getAbrev().equals("AD") || usuario.getPerfil().getAbrev().equals("JE")) {
            if (mes == null || ano == null || empresa == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar mes, año y/o empresa."));
            } else {
                if (empresa.equals("CR")) {
                    tienda1 = 4;
                    tienda2 = 5;
                    tienda3 = 10;
                    comiprimer = BigDecimal.valueOf(0.035);
                    comiseg = BigDecimal.valueOf(0.062);
                    comiter = BigDecimal.valueOf(0.078);
                    comicua = BigDecimal.valueOf(0.092);
                    tienda = "CASCO-RED";
                } else {
                    tienda1 = 8;
                    tienda2 = 9;
                    tienda3 = 11;
                    comiprimer = BigDecimal.valueOf(0.02);
                    comiseg = BigDecimal.valueOf(0.05);
                    comiter = BigDecimal.valueOf(0.08);
                    comicua = BigDecimal.valueOf(0.12);
                    tienda = "TRICAR/SEDNA";
                }
                switch (mes) {
                    case 1:
                        meses = "ENERO";
                        break;
                    case 2:
                        meses = "FEBRERO";
                        break;
                    case 3:
                        meses = "MARZO";
                        break;
                    case 4:
                        meses = "ABRIL";
                        break;
                    case 5:
                        meses = "MAYO";
                        break;
                    case 6:
                        meses = "JUNIO";
                        break;
                    case 7:
                        meses = "JULIO";
                        break;
                    case 8:
                        meses = "AGOSTO";
                        break;
                    case 9:
                        meses = "SETIEMBRE";
                        break;
                    case 10:
                        meses = "OCTUBRE";
                        break;
                    case 11:
                        meses = "NOVIEMBRE";
                        break;
                    case 12:
                        meses = "DICIEMBRE";
                        break;
                }
                parametros.put("mes", mes);
                parametros.put("anio", ano);
                parametros.put("caja1", tienda1);
                parametros.put("caja2", tienda2);
                parametros.put("caja3", tienda3);
                parametros.put("tienda", tienda);
                parametros.put("comiprimer", comiprimer);
                parametros.put("comiseg", comiseg);
                parametros.put("comiter", comiter);
                parametros.put("comicua", comicua);
                parametros.put("meses", meses);
                if (empresa.equals("CR")) {
                    File jasper = new File("D:/reporte/consolidado/comisionescasco.jasper");
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    response.addHeader("Content-disposition", "attachment; filename=Comisiones" + tienda + "" + mes + "" + ano + ".xls");
                    ServletOutputStream stream = response.getOutputStream();

                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                    exporter.exportReport();

                    stream.flush();
                    stream.close();
                    FacesContext.getCurrentInstance().responseComplete();
                } else {
                    File jasper = new File("D:/reporte/consolidado/comisionestrse.jasper");
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    response.addHeader("Content-disposition", "attachment; filename=Comisiones" + tienda + "" + mes + "" + ano + ".xls");
                    ServletOutputStream stream = response.getOutputStream();

                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                    exporter.exportReport();

                    stream.flush();
                    stream.close();
                    FacesContext.getCurrentInstance().responseComplete();
                }

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No tiene permisos para acceder a este reporte."));
        }
        con.close();
    }

    public void exportarLiq(String codigo, Credito cred) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        CredavalDao linkdao = new CredavalDaoImp();
        List<Creditoaval> listafiltrada = new ArrayList();
        avales = new ArrayList();
        String naval1 = null;
        String dniaval1 = null;
        String naval2 = null;
        String dniaval2 = null;
        try {
            listafiltrada = linkdao.avales(cred);
            for (int i = 0; i < listafiltrada.size(); i++) {
                Creditoaval get = listafiltrada.get(i);
                avales.add(get.getAnexo());
            }
            if (avales.size() >= 0) {
                naval1 = avales.get(0).getNombre().concat(" ").concat(avales.get(0).getApepat()).concat(" ").concat(avales.get(0).getApemat());
                dniaval1 = avales.get(0).getNumdocumento();
                naval2 = avales.get(1).getNombre().concat(" ").concat(avales.get(1).getApepat()).concat(" ").concat(avales.get(1).getApemat());
                dniaval2 = avales.get(1).getNumdocumento();
            }
        } catch (Exception e) {
        }
        if (naval1 == null) {
            naval1 = " ";
        }
        if (dniaval1 == null) {
            dniaval1 = " ";
        }
        if (naval2 == null) {
            naval2 = " ";
        }
        if (dniaval2 == null) {
            dniaval2 = " ";
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(codigo)) {
            parametros.put("liqventa", codigo);
            parametros.put("nombresaval1", naval1);
            parametros.put("dniaval1", dniaval1);
            parametros.put("nombresaval2", naval2);
            parametros.put("dniaval2", dniaval2);
            File jasper = new File("D:/reporte/liquicredito.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Liquidacion" + codigo + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Genere el crédito primero"));
        }
        con.close();
    }

    public void exportarFormato(String codigo, Credito cred) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (cred.getEstado().equals("AP")) {
            if (StringUtils.isNotBlank(codigo)) {
                parametros.put("codigo", codigo);
                File jasper = new File("D:/reporte/form_aproba.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=Formato" + codigo + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Genere el crédito primero"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El credito debe estar aprobado"));
        }

        con.close();
    }

    public void exportarExcelFormato(String codigo, Credito credito) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (credito.getEstado().equals("AP")) {
            parametros.put("codigo", codigo);
            //File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/proforma.jasper"));
            File jasper = new File("D:/reporte/form_aproba.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Formato" + codigo + ".xls");
            ServletOutputStream stream = response.getOutputStream();

            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
            con.close();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El credito debe estar aprobado"));
        }
    }

    public void exportarCronograma(String codigo, Credito cred) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        CredavalDao linkdao = new CredavalDaoImp();
        List<Creditoaval> listafiltrada = new ArrayList();
        avales = new ArrayList();
        String naval1 = null;
        String apaval1 = null;
        String dniaval1 = null;
        String naval2 = null;
        String apaval2 = null;
        String dniaval2 = null;
        try {
            listafiltrada = linkdao.avales(cred);
            for (int i = 0; i < listafiltrada.size(); i++) {
                Creditoaval get = listafiltrada.get(i);
                avales.add(get.getAnexo());
            }
            if (avales.size() >= 0) {
                naval1 = avales.get(0).getNombre();
                apaval1 = avales.get(0).getApepat().concat(" ").concat(avales.get(0).getApemat());
                dniaval1 = avales.get(0).getNumdocumento();
                naval2 = avales.get(1).getNombre();
                apaval2 = avales.get(1).getApepat().concat(" ").concat(avales.get(1).getApemat());
                dniaval2 = avales.get(1).getNumdocumento();
            }
        } catch (Exception e) {
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (cred.getEstado().equals("AP")) {
            if (StringUtils.isNotBlank(codigo)) {
                parametros.put("codigo", codigo);
                parametros.put("nombreaval1", naval1);
                parametros.put("apesaval1", apaval1);
                parametros.put("dniaval1", dniaval1);
                parametros.put("nombreaval2", naval2);
                parametros.put("apesaval2", apaval2);
                parametros.put("dniaval2", dniaval2);
                File jasper = new File("D:/reporte/cronograma.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=Cronograma" + codigo + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Genere el crédito primero"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El credito debe estar aprobado"));
        }
        con.close();
    }

    public void exportarExcel(String codigo) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codigo", codigo);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/proforma.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Proforma" + codigo + ".xls");
        ServletOutputStream stream = response.getOutputStream();

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarConsolidadoCasco() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        recorrerCreditos recorre = new recorrerCreditos();
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = format.format(d);
        BigDecimal penv1 = recorre.montosDet("V1", "CA", "PN");
        BigDecimal venv1 = recorre.montosDet("V1", "CA", "VN");
        BigDecimal totv1 = recorre.montosTotal("V1", "CA");
        BigDecimal penv2 = recorre.montosDet("V2", "CA", "PN");
        BigDecimal venv2 = recorre.montosDet("V2", "CA", "VN");
        BigDecimal totv2 = recorre.montosTotal("V2", "CA");
        BigDecimal penv3 = recorre.montosDet("YA", "CA", "PN");
        BigDecimal venv3 = recorre.montosDet("YA", "CA", "VN");
        BigDecimal totv3 = recorre.montosTotal("YA", "CA");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pendienteV1", penv1);
        parametros.put("vencidaV1", venv1);
        parametros.put("totalV1", totv1);
        parametros.put("pendienteV2", penv2);
        parametros.put("vencidaV2", venv2);
        parametros.put("totalV2", totv2);
        parametros.put("pendienteV3", penv3);
        parametros.put("vencidaV3", venv3);
        parametros.put("totalV3", totv3);
        parametros.put("fecha", fecha);
        File jasper = new File("D:/reporte/consolidado/consolidado.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ConsolidadoCasco.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarConsolidadoTricar() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        recorrerCreditos recorre = new recorrerCreditos();
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = format.format(d);
        BigDecimal penv1 = recorre.montosDet("V1", "TR", "PN");
        BigDecimal venv1 = recorre.montosDet("V1", "TR", "VN");
        BigDecimal totv1 = recorre.montosTotal("V1", "TR");
        BigDecimal penv2 = recorre.montosDet("V2", "TR", "PN");
        BigDecimal venv2 = recorre.montosDet("V2", "TR", "VN");
        BigDecimal totv2 = recorre.montosTotal("V2", "TR");
        BigDecimal penv3 = recorre.montosDet("YA", "TR", "PN");
        BigDecimal venv3 = recorre.montosDet("YA", "TR", "VN");
        BigDecimal totv3 = recorre.montosTotal("YA", "TR");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pendienteV1", penv1);
        parametros.put("vencidaV1", venv1);
        parametros.put("totalV1", totv1);
        parametros.put("pendienteV2", penv2);
        parametros.put("vencidaV2", venv2);
        parametros.put("totalV2", totv2);
        parametros.put("pendienteV3", penv3);
        parametros.put("vencidaV3", venv3);
        parametros.put("totalV3", totv3);
        parametros.put("fecha", fecha);
        File jasper = new File("D:/reporte/consolidado/consolidado.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ConsolidadoTricar.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarConsolidadoSedna() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        recorrerCreditos recorre = new recorrerCreditos();
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = format.format(d);
        BigDecimal penv1 = recorre.montosDet("V1", "SE", "PN");
        BigDecimal venv1 = recorre.montosDet("V1", "SE", "VN");
        BigDecimal totv1 = recorre.montosTotal("V1", "SE");
        BigDecimal penv2 = recorre.montosDet("V2", "SE", "PN");
        BigDecimal venv2 = recorre.montosDet("V2", "SE", "VN");
        BigDecimal totv2 = recorre.montosTotal("V2", "SE");
        BigDecimal penv3 = recorre.montosDet("T ", "SE", "PN");
        BigDecimal venv3 = recorre.montosDet("T ", "SE", "VN");
        BigDecimal totv3 = recorre.montosTotal("T ", "SE");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pendienteV1", penv1);
        parametros.put("vencidaV1", venv1);
        parametros.put("totalV1", totv1);
        parametros.put("pendienteV2", penv2);
        parametros.put("vencidaV2", venv2);
        parametros.put("totalV2", totv2);
        parametros.put("pendienteV3", penv3);
        parametros.put("vencidaV3", venv3);
        parametros.put("totalV3", totv3);
        parametros.put("fecha", fecha);
        File jasper = new File("D:/reporte/consolidado/consolsedna.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ConsolidadoSedna.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarMorosidad() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        recorrerCreditos recorre = new recorrerCreditos();
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = format.format(d);
        BigDecimal penv1 = recorre.montosDet("V1", "CA", "PN");
        BigDecimal venv1 = recorre.montosDet("V1", "CA", "VN");
        BigDecimal totv1 = recorre.montosTotal("V1", "CA");
        BigDecimal penv2 = recorre.montosDet("V2", "CA", "PN");
        BigDecimal venv2 = recorre.montosDet("V2", "CA", "VN");
        BigDecimal totv2 = recorre.montosTotal("V2", "CA");
        BigDecimal penv3 = recorre.montosDet("YA", "CA", "PN");
        BigDecimal venv3 = recorre.montosDet("YA", "CA", "VN");
        BigDecimal totv3 = recorre.montosTotal("YA", "CA");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pendienteV1", penv1);
        parametros.put("vencidaV1", venv1);
        parametros.put("totalV1", totv1);
        parametros.put("pendienteV2", penv2);
        parametros.put("vencidaV2", venv2);
        parametros.put("totalV2", totv2);
        parametros.put("pendienteV3", penv3);
        parametros.put("vencidaV3", venv3);
        parametros.put("totalV3", totv3);
        parametros.put("fecha", fecha);
        parametros.put("tienda1", "V1");
        parametros.put("tienda2", "V2");
        parametros.put("tienda3", "YA");
        parametros.put("empresa", "CA");
        File jasper = new File("D:/reporte/consolidado/morosidad.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=morosidad.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarTotalExcel() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, true);
        File jasper = new File("D:/reporte/varios/filtrar.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cuentas.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarTotal() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, true);
        File jasper = new File("D:/reporte/varios/filtrarosa.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cuentas.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarCobrosTotal() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha1", fecha1);
        parametros.put("fecha2", fecha2);
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, true);
        File jasper = new File("D:/reporte/diario/pagos.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Totalcobranza.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarIniContado() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha1", fecha1);
        parametros.put("fecha2", fecha2);
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, true);
        File jasper = new File("D:/reporte/diario/inicontado.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=TotalInicialContado.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public void exportarAnticipos() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha1", fecha1);
        parametros.put("fecha2", fecha2);
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, true);
        File jasper = new File("D:/reporte/diario/anticipos.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=TotalAnticipos.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        con.close();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Anexo> getAvales() {
        return avales;
    }

    public void setAvales(List<Anexo> avales) {
        this.avales = avales;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
