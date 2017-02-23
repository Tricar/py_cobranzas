package utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class generadorCodigos {

    public String genCodigoLiquid(String tienda) throws SQLException {
        int vcorre = 1;
        String sql = "";
        String correlativo = "";
        String ceros = "";
        String codigo = "";
        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException();
            }
            sql = "SELECT correlativo FROM credito where DATEPART(month, fecaprob) = DATEPART(month, getdate()) AND DATEPART(year, fecaprob) = DATEPART(year, GETDATE()) AND tienda = '" + tienda + "' ORDER BY correlativo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("correlativo"));
                vcorre++;
            }
            rs.close();
            st.close();
            con.close();
            // vcodigofinalarticulo = objtipoarticulo.getAbreviatura() + vcodigoart;
        } catch (Exception e) {
            e.getMessage();
            System.out.println("aver");
            System.out.println(e.getMessage());
        }
        for (int i = 1; i < 4 - String.valueOf(vcorre).length(); i++) {
            ceros = ceros + "0";
        }
        correlativo = ceros + vcorre;
        Calendar fecha = Calendar.getInstance();
        int variable = fecha.get(Calendar.YEAR);
        String añoletras = Integer.toString(variable);
        añoletras = añoletras.substring(2);
        if (tienda.equals("V1")) {
            codigo = "VA";
        }
        if (tienda.equals("V2")) {
            codigo = "S";
        }
        if (tienda.equals("V3")) {
            codigo = "MB";
        }
        codigo = codigo.concat(añoletras);
        variable = fecha.get(Calendar.MONTH) + 1;
        String mesletras = Integer.toString(variable);
        codigo = codigo.concat(mesletras).concat(correlativo);
        return (codigo);
    }

    public String genCorreComp(String tipodoc, String tienda, String empresa) throws SQLException {
        int vcorre = 1;
        String sql = "";
        String serie = "";
        String abrev = "";
        String correlativo = "";
        String ceros = "";
        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException();
            }
            sql = "SELECT abrev as abrev, serie as serie, correlativo FROM tipodoc where tipodoc= '" + tipodoc + "' AND tienda = '" + tienda + "' AND empresa = '" + empresa + "' ORDER BY correlativo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                abrev = rs.getString("abrev");
                serie = rs.getString("serie");
                vcorre = Integer.parseInt(rs.getString("correlativo"));
                vcorre++;
            }
            for (int i = 1; i < 6 - String.valueOf(vcorre).length(); i++) {
                ceros = ceros + "0";
            }
            correlativo = abrev + serie + "-" + ceros + vcorre;
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (correlativo);
    }
}
