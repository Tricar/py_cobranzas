import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        String fecha2 = "14-07-2016";
        Date venc = new SimpleDateFormat("dd-MM-yyyy").parse(fecha2);
        if (venc.equals(fecha)){
            System.out.println("Fecha : "+fecha+" es mayor a fecha: "+fecha2+" PAGO PUNTUAL");
        }        
    }

}
