import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Model.Anexo;
import java.util.ArrayList;
import java.util.List;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hola Mundo");
        AnexoDao anexodao = new AnexoDaoImplements();
        Anexo anexo = new Anexo();
        anexo = anexodao.cargarClientexDoc("00121429", "CN", "CJ");
        System.out.println("Este es el anexo :"+anexo.getNombre());

    }
}
