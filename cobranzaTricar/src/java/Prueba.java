
import Dao.AnexoDao;
import Dao.AnexoDaoImplements;
import Dao.OcupacionDao;
import Dao.OcupacionDaoImpl;
import Model.Anexo;
import Model.Ocupacion;
import java.util.ArrayList;
import java.util.List;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Anexo objanexo = new Anexo();
        AnexoDao anedao = new AnexoDaoImplements();
        OcupacionDao ocudao = new OcupacionDaoImpl();
        List<Ocupacion> lista = new ArrayList();
        objanexo = anedao.cargarClientexDoc("44182252", "CN", "AD");
        System.out.println("Anexo cargado:"+objanexo.getNombres());
        Ocupacion ocupacion = new Ocupacion();
       // ocupacion.setAnexo(objanexo);
        ocupacion.setIdocupacion(1);
        ocupacion.setTipo("IN");
        ocupacion.setClase("FR");
        ocupacion.setDescripcion("cajero en banca");
        ocudao.insertarOcupacion(ocupacion);
        lista = ocudao.ocupacionesxIdanexo(objanexo);
        for (int i = 0; i < lista.size(); i++) {
                Ocupacion get = lista.get(i);
                System.out.println("Lista: "+get.getDescripcion());
            }
    }
}
