import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date d = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fecha.format(d);
        System.out.println("fecha: "+convertido);
    }
}
