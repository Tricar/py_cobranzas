import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BigDecimal interes = new BigDecimal(BigInteger.ZERO);
        BigDecimal factormora = new BigDecimal(0.0016667);
        BigDecimal cien = BigDecimal.valueOf(100);
        long difdays = 9;
        BigDecimal monto = new BigDecimal(200);
        BigDecimal tasa = (factormora.multiply(BigDecimal.valueOf(difdays)).setScale(5, RoundingMode.HALF_DOWN));
        System.out.println("TASA "+tasa);
        BigDecimal cobrado = monto.multiply(tasa).setScale(0, RoundingMode.UP);
        System.out.println("Monto a cobrar "+cobrado);
    }

}
