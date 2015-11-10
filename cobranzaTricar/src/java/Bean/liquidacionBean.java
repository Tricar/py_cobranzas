/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.StringUtils;
import utiles.dbManager;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@SessionScoped
public class liquidacionBean implements Serializable{

    private String liqventa;

    public String getLiqventa() {
        return liqventa;
    }

    public void setLiqventa(String liqventa) {
        this.liqventa = liqventa;
    }
    
    public liquidacionBean() {
    }
    
    public void exportarPDF(String liqventa) throws JRException, NamingException, SQLException, IOException {
        dbManager conn = new dbManager();
        Connection con = null;
        con = conn.getConnection();
        Map<String, Object> parametros = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(liqventa)) {
            parametros.put("liqventa", liqventa);
            File jasper = new File("D:/reporte/liquidacion.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Proforma" + liqventa + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Crear proforma primero."));
        }
    }
    
}
