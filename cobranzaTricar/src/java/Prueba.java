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
        AnexoDao anexo = new AnexoDaoImplements();
        List<Anexo> anex = new ArrayList();
        anex = anexo.buscarCliente("j", "CL");
        System.out.println("Este es el anexo ");
        for (int i = 0; i < anex.size(); i++) {
            Anexo get = anex.get(i);
            int j=i+1;
            System.out.println("Objeto: "+j+" "+get.getNombre()+ " ");
        }

    }
}
