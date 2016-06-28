package utiles;

import java.math.BigDecimal;

public class inicial {

    public BigDecimal inicialCredito(String distrito, String tipov, BigDecimal precio, String modelo) {

        BigDecimal inicial = new BigDecimal(0);
        if (distrito != null) {

            switch (distrito) {
                case "CA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        if (modelo.equals("TX150")) {
                            inicial = BigDecimal.valueOf(1500.00);
                        } else {
                            inicial = precio.multiply(BigDecimal.valueOf(0.5));
                        }
                    }
                    break;
                case "YA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        if (modelo.equals("TX150")) {
                            inicial = BigDecimal.valueOf(1500.00);
                        } else {
                            inicial = precio.multiply(BigDecimal.valueOf(0.5));
                        }
                    }
                    break;
                case "MA":
                    if (tipov.equals("ML")) {
                        inicial = precio.multiply(BigDecimal.valueOf(0.4));
                    } else {
                        if (modelo.equals("TX150")) {
                            inicial = BigDecimal.valueOf(1500.00);
                        } else {
                            inicial = precio.multiply(BigDecimal.valueOf(0.5));
                        }
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
