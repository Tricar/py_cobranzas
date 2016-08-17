import Dao.ConceptosDao;
import Dao.ConceptosDaoImp;
import Model.Conceptos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConceptosDao condao = new ConceptosDaoImp();
        Date fecha1 = new Date();        
        Date fecha2 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha2);
        cal.add(Calendar.DAY_OF_YEAR, 10);
        fecha2 = cal.getTime();
        cal.setTime(fecha1);
        cal.add(Calendar.DAY_OF_YEAR, -10);
        fecha1 = cal.getTime();
        System.out.println("fechas: "+fecha1+"   fecha2 "+fecha2);
        List <Conceptos> todos = new ArrayList();
        todos = condao.filtrarxFechas(fecha1, fecha2);
        System.out.println("to :"+todos.size());
        for (int i = 0; i < todos.size(); i++) {
            Conceptos get = todos.get(i);
            System.out.println("objeto :"+get.getTipo());
        }
    }
}
