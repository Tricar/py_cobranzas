
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Credito;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import utiles.generadorCodigos;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /**
         * throws SQLException *
         */
        //        List<Date> fechas = new ArrayList();
        //        Date fecha = new Date();
        //        int nletras = 10;
        //        Calendar cal = Calendar.getInstance();
        //        cal.setTime(fecha);
        //        cal.add(cal.DATE, 14);
        //        int sumar = 0;
        //        int inicial = 0;
        //        int fin = 0;
        //        int difdays = 0;
        //        boolean sw = true;
        //        for (int i = 0; i < nletras; i++) {
        //            if (sw) {
        //                inicial = cal.get(cal.DATE);
        //                int j = 1;
        //                cal.add(cal.MONTH, j);
        //                if (esDomingo(cal) == 1){
        //                    Calendar temp = Calendar.getInstance();
        //                    temp = cal;
        //                    temp.add(temp.DATE,1);
        //                    fechas.add(temp.getTime());                    
        //                    cal.add(cal.DATE, -1);
        //                } else {
        //                    fechas.add(cal.getTime());                    
        //                }                
        //                fin = cal.get(cal.DATE);                
        //            }
        //            sw = true;
        //            if (inicial - fin != 0) {                
        //                difdays = inicial - fin;
        //                sumar = fin + difdays;
        //                cal.add(cal.DATE, sumar);
        //                fechas.add(cal.getTime());
        //                inicial = 0;
        //                fin = 0;
        //                sw = false;
        //            }
        //            System.out.println("fecha :"+i+" "+fechas.get(i).toString());
        //        }
//        generadorCodigos gen = new generadorCodigos();
//        String codigo = gen.genCodigoLiquid("V2");
//        System.out.println("codigo: "+codigo);
//        String ultimo = codigo.substring(codigo.length()-2);
//        System.out.println("ultimo: "+ultimo);
//        int corre = Integer.parseInt(ultimo);
//        System.out.println("entero corre: "+corre);

        try {
            Document doc = Jsoup.connect("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias").get();
            System.out.println(doc.title());
            int cont = 0;
            ArrayList<String> downServers = new ArrayList<>();
            Element table = doc.select("table").get(1); //select the first table.
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");

                for (cont = 0; cont < cols.size(); cont++) {
                    downServers.add(cols.get(cont).text());
                }
            }

            String tipoCambioCompra = downServers.get(cont - 2);
            System.out.println("Tipo de Cambio Compra " + tipoCambioCompra);
            String tipoCambioVenta = downServers.get(cont - 1);
            System.out.println("Tipo de Cambio Venta " + tipoCambioVenta);
        } catch (Exception e) {
            System.out.println("Error en la conexión a internet: " + e);
        }

    }

}
