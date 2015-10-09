package utiles;

import java.math.BigDecimal;

/**
 *
 * @author master
 */
public class inicial {

    public BigDecimal inicialCredito(String distrito, String tipov, BigDecimal precio) {

        BigDecimal inicial = new BigDecimal(0);
        if (distrito != null) {

            switch (distrito) {
                case "CA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        inicial = precio.multiply(BigDecimal.valueOf(0.5));
                    }
                    break;
                case "YA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        inicial = precio.multiply(BigDecimal.valueOf(0.5));
                    }
                    break;
                case "MA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        inicial = precio.multiply(BigDecimal.valueOf(0.5));
                    }
                    break;
                default:
                    inicial = precio.multiply(BigDecimal.valueOf(0.7));
                    break;
            }
        }
        return inicial;
    }
}
