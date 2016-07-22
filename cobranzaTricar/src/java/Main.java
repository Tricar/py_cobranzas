import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.DATE, 11);
        int sumar = 0;
        int inicial = 0;
        int fin = 0;
        int difdays = 0;
        List<Date> fechas = new ArrayList();
        boolean sw = true;
        for (int i = 1; i < 11; i++) {
            if (sw) {
                inicial = hoy.get(Calendar.DATE);
                int j = 1;
                hoy.add(Calendar.MONTH, j);
                System.out.println(hoy.getTime());
                fechas.add(hoy.getTime());
                fin = hoy.get(Calendar.DATE);
            }
            sw = true;
            if (inicial - fin != 0) {
                difdays = inicial - fin;
                sumar = fin + difdays;
                hoy.add(Calendar.DATE, sumar);
                System.out.println(hoy.getTime());
                fechas.add(hoy.getTime());
                inicial = 0;
                fin = 0;
                sw = false;
            }
        }
        
        for (int i = 0; i < fechas.size(); i++) {
            Date get = fechas.get(i);
            System.out.println("Fecha: "+i+" "+get);
        }
////    }
//    public static void main(String[] args) {
//
//        // create a calendar
//        Calendar cal = Calendar.getInstance();
//
//        // displays the current calendar
//        System.out.println("Month is " + cal.get(Calendar.MONTH));
//
//        // roll month
//        cal.roll(Calendar.MONTH, true);
//
//        // print result after rolling
//        System.out.println("Month is " + cal.get(Calendar.MONTH));
//
//        // roll downwards
//        cal.roll(Calendar.MONTH, false);
//
//        // print result after rolling down
//        System.out.println("Month is " + cal.get(Calendar.MONTH));
//    }

    }
}
