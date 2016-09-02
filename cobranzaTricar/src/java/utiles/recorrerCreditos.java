package utiles;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class recorrerCreditos {

    private List<Date> fechasacumulado;

    public BigDecimal montosDet(String tienda, String empresa, String estado) {
        BigDecimal total = new BigDecimal(0);
        try {

            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            String sql = "";
            sql = "select sum(letras.saldo) sumapendientes from anexo INNER JOIN (letras INNER JOIN credito ON letras.idventa = credito.idventa and credito.tienda = '" + tienda + "' and credito.empresa='" + empresa + "' AND letras.estado ='" + estado + "' and letras.descripcion <> 'ND') ON anexo.idanexo = credito.idanexo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal("sumapendientes");
            }
            if (total == null) {
                total = new BigDecimal(0);
            }
            rs.close();
            st.close();
            dbm.close(con);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return total;
    }

    public BigDecimal montosTotal(String tienda, String empresa) {
        BigDecimal total = new BigDecimal(0);
        try {

            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            String sql = "";
            sql = "select sum(credito.deudactual) suma from credito where credito.estado <> 'CN' and credito.tienda = '" + tienda + "' AND credito.empresa = '" + empresa + "' and credito.estado <> 'EM'";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal("suma");
            }
            if (total == null) {
                total = new BigDecimal(0);
            }
            rs.close();
            st.close();
            dbm.close(con);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return total;
    }

    public List<Date> devolverfechasAcum() {
        fechasacumulado = new ArrayList();
        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            String sql = "";
            sql = "select distinct morosidad.fecreg lista from dbo.morosidad";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                Date fecha = new Date();
                fecha = rs.getDate("lista");                
                fechasacumulado.add(fecha);
            }
            if (fechasacumulado == null) {
                fechasacumulado = new ArrayList();
            }
            rs.close();
            st.close();
            dbm.close(con);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return fechasacumulado;
    }

    public List<Date> getFechasacumulado() {
        return fechasacumulado;
    }

    public void setFechasacumulado(List<Date> fechasacumulado) {
        this.fechasacumulado = fechasacumulado;
    }

}
