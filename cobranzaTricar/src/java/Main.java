import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import utiles.recorrerCreditos;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

        // create an empty array list   
        ArrayList<String> color_list = new ArrayList<String>();

        // use add() method to add values in the list  
        color_list.add("White");
        color_list.add("Black");
        color_list.add("Red");
        // create an empty array sample with an initial capacity   
        ArrayList<String> sample = new ArrayList<String>();
        // use add() method to add values in the list   
        sample.add("Green");
        sample.add("Red");
        sample.add("White");
        // remove all elements from second list if it exists in first list  
        sample.removeAll(color_list);
        System.out.println("First List :" + color_list);
        System.out.println("Second List :" + sample);
    }

    public static int esDomingo(Calendar d) {
        return d.get(Calendar.DAY_OF_WEEK);
    }

}
