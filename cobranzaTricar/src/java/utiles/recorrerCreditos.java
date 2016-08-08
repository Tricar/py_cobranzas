package utiles;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class recorrerCreditos {

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
            sql = "select sum(credito.deudactual) suma from credito where credito.estado <> 'CN' and credito.tienda = '"+tienda+"' AND credito.empresa = '"+empresa+"' and credito.estado <> 'EM'";
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
}
