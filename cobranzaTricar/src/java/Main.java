
import java.math.BigDecimal;
import java.math.BigInteger;
import utiles.recorrerCreditos;



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        recorrerCreditos recorre = new recorrerCreditos();
        BigDecimal pendiente = recorre.montosDet("V2","CA","PN");
        BigDecimal vencida = recorre.montosDet("V2","CA","VN");
        BigDecimal suma = recorre.montosTotal("V2", "CA");
        
        System.out.println("total: "+suma+" pendiente: "+pendiente+" vencida"+vencida);
    }
}
