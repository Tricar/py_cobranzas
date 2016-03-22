
import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Model.Anexo;
import Model.Credito;
import Model.Letras;
import java.util.ArrayList;
import java.util.List;
import utiles.dbManager;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Credito cred = new Credito();
//        CreditoDao creddao = new CreditoDaoImp();
//        cred = creddao.cargarxCodigoEstado("S1506030", "AP");
//        System.out.println("Anexo : "+cred.getAnexoByIdanexo().getNombres() );
//        List<Letras> letritas = new ArrayList();
//        LetrasDao letrasdao = new LetrasDaoImplements();
//        letritas= letrasdao.mostrarLetrasXCred(cred);
//        long mili = letritas.get(0).getFecven().getTime();
//        System.out.println("Fecha venc: "+letritas.get(0).getFecven());
//        long mil2 = letritas.get(0).getFecini().getTime();
//        System.out.println("Fecha inicio: "+letritas.get(0).getFecini());
//        long diff = mili - mil2;
//        long diffdays = diff / (24*60*60*1000);
//        System.out.println("Dife : "+diffdays);
        dbManager conn = new dbManager();
        conn.getConnection();
        System.out.println(conn.getConnection());
        Credito objcredito = new Credito();
        Letras objletras = new Letras();
        Anexo objanexo = new Anexo();
        
    }
}
