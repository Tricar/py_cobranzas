package utiles;

import java.sql.*;

/**
 *
 * @author Administrador
 */
public class dbManager {

    private String error;

    public String geterror() {
        return error;
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=cobranzas", "sa", "tricar2012");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
