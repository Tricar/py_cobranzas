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
        String codigo="";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException();
            }
            sql = "SELECT correlativo FROM credito where DATEPART(month, fechareg) = DATEPART(month, getdate()) AND DATEPART(year, fechareg) = DATEPART(year, GETDATE()) AND tienda = '" + tienda + "' ORDER BY correlativo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("correlativo"));
                vcorre++;
            }
            for (int i = 1; i < 4 - String.valueOf(vcorre).length(); i++) {
                ceros = ceros + "0";
            }
            correlativo = ceros + vcorre;
            rs.close();
            st.close();
            con.close();
            // vcodigofinalarticulo = objtipoarticulo.getAbreviatura() + vcodigoart;
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        Calendar fecha = Calendar.getInstance();
        int variable= fecha.get(Calendar.YEAR);
        String añoletras= Integer.toString(variable);
        añoletras = añoletras.substring(2);
        if(tienda.equals("V1")){
            codigo = "VA";
        } else {
            codigo = "S";
        }
        codigo = codigo.concat(añoletras);
        variable = fecha.get(Calendar.MONTH)+1;
        String mesletras = Integer.toString(variable);
        codigo = codigo.concat(mesletras).concat(correlativo);        
        System.out.println("Codigo final: "+codigo);
        return (codigo);
    }
}
