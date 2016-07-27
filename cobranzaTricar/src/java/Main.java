
import java.math.BigDecimal;
import java.math.BigInteger;



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal otrocinco = new BigDecimal(5);        
        int res = cinco.compareTo(otrocinco);
        System.out.println("resultado "+res);
    }
}
