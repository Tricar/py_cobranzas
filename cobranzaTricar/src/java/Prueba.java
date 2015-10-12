
import java.math.BigDecimal;
import java.math.BigInteger;
import utiles.inicial;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        inicial Inicial = new inicial();
        BigDecimal calculo = new BigDecimal(BigInteger.ZERO);
        
        calculo = Inicial.inicialCredito("CA", "ML", BigDecimal.valueOf(2150.00));
        
        System.out.println("Inicial :"+calculo);
    }
}
