package utiles;

import java.math.BigDecimal;

/**
 *
 * @author master
 */
public class precio {

    public BigDecimal precioModelo(String modelo) {
        String model = modelo;
        BigDecimal precio = new BigDecimal(0);
        if (model != null) {

            switch (model) {
                case "VX150":
                    precio = BigDecimal.valueOf(5400.00);
                    break;
                case "MTXC VX150":
                    precio = BigDecimal.valueOf(6600.00);
                    break;
                case "TX200-C":
                    precio = BigDecimal.valueOf(6700.00);
                    break;
                case "TX250ZH-E":
                    precio = BigDecimal.valueOf(7700.00);
                    break;
                case "TX250ZH-M":
                    precio = BigDecimal.valueOf(8400.00);
                    break;
                case "VX110":
                    precio = BigDecimal.valueOf(2750.00);
                    break;
                case "TX110-A":
                    precio = BigDecimal.valueOf(2450.00);
                    break;
                case "VX110-7":
                    precio = BigDecimal.valueOf(2900.00);
                    break;
                case "VX110-9":
                    precio = BigDecimal.valueOf(2850.00);
                    break;
                case "VX125-2T":
                    precio = BigDecimal.valueOf(3100.00);
                    break;
                case "TX150-C":
                    precio = BigDecimal.valueOf(2800.00);
                    break;
                case "TX150-R":
                    precio = BigDecimal.valueOf(3950.00);
                    break;
                case "VX150-8":
                    precio = BigDecimal.valueOf(3000.00);
                    break;
                case "VX150-25":
                    precio = BigDecimal.valueOf(4400.00);
                    break;
                case "VX150GY-11B":
                    precio = BigDecimal.valueOf(3800.00);
                    break;
                case "VX150GY-11A":
                    precio = BigDecimal.valueOf(4600.00);
                    break;
                case "VX200GY-V2":
                    precio = BigDecimal.valueOf(4200.00);
                    break;
                case "TX150":
                    precio = BigDecimal.valueOf(4600.00);
                    break;

            }
        }
        return precio;
    }

}
