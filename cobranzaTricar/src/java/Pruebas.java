
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/captcha?accion=image").get();
        System.out.println(doc);
    }
    
}
