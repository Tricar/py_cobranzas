package utiles;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class recorrerCreditos {

    public BigDecimal montosxTienda(String tienda, String empresa, String estado) {
        BigDecimal totalpendiente = new BigDecimal(0);
        try {

            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            String sql = "";
            sql = "select sum(letras.saldo) sumapendientes from anexo INNER JOIN (letras INNER JOIN credito ON letras.idventa = credito.idventa and credito.tienda = '" + tienda + "' and credito.empresa='" + empresa + "' AND letras.estado ='" + estado + "' and letras.descripcion <> 'ND') ON anexo.idanexo = credito.idanexo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalpendiente = rs.getBigDecimal("sumapendientes");
            }
            if (totalpendiente == null) {
                totalpendiente = new BigDecimal(0);
            }
            rs.close();
            st.close();
            dbm.close(con);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return totalpendiente;
    }
}
