package utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class generadorCodigos {

    private String vcerosart = "";
    private String vcodigoart = "";
    private String vcodigofinalarticulo = "";

    public String genCodProf() throws SQLException {
        int vcorre = 1;
        String sql = "";
        vcodigoart = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException();
            }
         //   sql = "SELECT correlativo FROM articulo where codtipart='" + objtipoarticulo.getCodtipart() + "' ORDER BY correlativo";
            PreparedStatement st = con.prepareStatement(sql, 1005, 1007);
            ResultSet rs = st.executeQuery();
            rs.afterLast();
            if (rs.previous()) {
                vcorre = Integer.parseInt(rs.getString("correlativo"));
                vcorre++;
            }
            for (int i = 1; i < 4 - String.valueOf(vcorre).length(); i++) {
                vcerosart = vcerosart + "0";
            }

            vcodigoart = vcerosart + vcorre;
            rs.close();
            st.close();
            con.close();
            System.out.println(vcerosart + vcorre);

           // vcodigofinalarticulo = objtipoarticulo.getAbreviatura() + vcodigoart;

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }


    public String getVcodigoart() {
        return vcodigoart;
    }

    public void setVcodigoart(String vcodigoart) {
        this.vcodigoart = vcodigoart;
    }
}
