
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Model.Credito;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import utiles.generadorCodigos;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
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
        generadorCodigos gen = new generadorCodigos();
        String codigo = gen.genCodigoLiquid("V2");
        System.out.println("codigo: "+codigo);
        String ultimo = codigo.substring(codigo.length()-2);
        System.out.println("ultimo: "+ultimo);
        int corre = Integer.parseInt(ultimo);
        System.out.println("entero corre: "+corre);
        
                

    }

//    public static int esDomingo(Calendar d) {
//        return d.get(Calendar.DAY_OF_WEEK);
//    }
}
