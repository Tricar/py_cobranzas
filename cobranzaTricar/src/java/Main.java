
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utiles.recorrerCreditos;



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Date> fechas = new ArrayList();
        recorrerCreditos recorre = new recorrerCreditos();
        fechas = recorre.devolverfechasAcum();
        for (int i = 0; i < fechas.size(); i++) {
            Date get = fechas.get(i);
            System.out.println("fecha: "+i+1+" mas :"+get);
        }
    }
}
