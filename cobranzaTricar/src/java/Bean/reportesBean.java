package Bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class reportesBean implements Serializable {

    private String codigo;

    /**
     * Creates a new instance of reportesBean
     */
    public reportesBean() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void exportarPDF(String codigo) throws JRException, NamingException, SQLException, IOException {
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
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Crear proforma primero."));
        }
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

    }

}
