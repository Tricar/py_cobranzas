package utiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            sql = "select sum(letras.saldo) sumapendientes \n"
                    + "from credito, letras \n"
                    + "where letras.idventa = credito.idventa \n"
                    + "and credito.empresa = '"+empresa+"'\n"
                    + "and credito.tienda = '"+tienda+"'\n"
                    + "and credito.estado = 'DP'\n"
                    + "and letras.estado = '"+estado+"'\n"
                    + "and letras.descripcion < > 'ND'";
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
            sql = "select sum(credito.deudactual) suma \n"
                    + "from credito \n"
                    + "where credito.estado <> 'CN' \n"
                    + "and credito.tienda = '" + tienda + "' \n"
                    + "and credito.empresa = '" + empresa + "' \n"
                    + "and credito.estado = 'DP'";
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

    public BigDecimal totalxMes(String tienda, String anio, String mes) {
        BigDecimal total = new BigDecimal(0);
        try {
            dbManager dbm = new dbManager();
            Connection con = dbm.getConnection();
            String sql = "";
            sql = "select sum(credito.precio) suma from dbo.credito credito where \n"
                    + "DATEPART(yyyy, credito.fechareg) = '" + anio + "' AND DATEPART(mm, credito.fechareg) = '" + mes + "' and credito.estado = 'DP' and credito.tienda='" + tienda + "'";
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

    public BigDecimal calculoBonos(String tienda, String anio, String mes) {
        BigDecimal sum = totalxMes(tienda, anio, mes);
        if (sum.compareTo(BigDecimal.valueOf(90000)) >= 0 && sum.compareTo(BigDecimal.valueOf(149999.99)) < 0) {
            sum = sum.multiply(BigDecimal.valueOf(0.006)).setScale(1, RoundingMode.UP);
        } else {
            if (sum.compareTo(BigDecimal.valueOf(150000)) >= 0 && sum.compareTo(BigDecimal.valueOf(249999.99)) < 0) {
                sum = sum.multiply(BigDecimal.valueOf(0.0075)).setScale(1, RoundingMode.UP);;
            } else {
                if (sum.compareTo(BigDecimal.valueOf(250000)) >= 0) {
                    sum = sum.multiply(BigDecimal.valueOf(0.009)).setScale(1, RoundingMode.UP);;
                } else {
                    sum = BigDecimal.ZERO;
                }
            }
        }
        return sum;
    }

    public BigDecimal calculoBonosW(String tienda, String anio, String mes) {
        BigDecimal sum = totalxMes(tienda, anio, mes);
        if (sum.compareTo(BigDecimal.valueOf(150000)) >= 0 && sum.compareTo(BigDecimal.valueOf(249000)) < 0) {
            sum = BigDecimal.valueOf(300);
        } else {
            if (sum.compareTo(BigDecimal.valueOf(250000)) >= 0) {
                sum = BigDecimal.valueOf(500);
            } else {
                sum = BigDecimal.ZERO;
            }
        }
        return sum;
    }

    public List<Date> getFechasacumulado() {
        return fechasacumulado;
    }

    public void setFechasacumulado(List<Date> fechasacumulado) {
        this.fechasacumulado = fechasacumulado;
    }

}
