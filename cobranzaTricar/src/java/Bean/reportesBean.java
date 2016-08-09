package Bean;

import Dao.CredavalDao;
import Dao.CredavalDaoImp;
import Model.Anexo;
import Model.Credito;
import Model.Creditoaval;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void cierreIngreso() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (mes == null || ano == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar mes y/o año."));
        } else {
            parametros.put("mes", mes);
            parametros.put("ano", ano);
            File jasper = new File("D:/reporte/proforma.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Proforma" + mes + "-" + ano + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
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

    public void exportarConsolidadoExcel() throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        recorrerCreditos recorre = new recorrerCreditos();
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
        File jasper = new File("D:/reporte/consolidado/consolidado.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Consolidado.xls");
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

}
