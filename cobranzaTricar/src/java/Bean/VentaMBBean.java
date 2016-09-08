/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utiles.dbManager;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class VentaMBBean implements Serializable {

    /**
     * Creates a new instance of VentaMBBean
     */
    public VentaMBBean() {
    }

    public String generar_codigo_Articulo(Tipoarticulo objtipoarticulo) {
        int vcorre = 1;
        String sql = "";
        vcodigoart = "";

        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            if (con == null) {
                throw new NullPointerException(dbm.geterror());
            }
            sql = "SELECT correlativo FROM articulo where codtipart='" + objtipoarticulo.getCodtipart() + "' ORDER BY correlativo";
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

            vcodigofinalarticulo = objtipoarticulo.getAbreviatura() + vcodigoart;

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return (vcodigofinalarticulo);
    }

}
