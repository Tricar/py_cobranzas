
import Clases.n2t;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        n2t numero;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num;
        String res;
        System.out.print("Ingrese numero : ");
        num = Integer.parseInt(in.readLine());
        numero = new n2t(num);
        res = numero.convertirLetras(num);
        System.out.print(res);
        System.out.println("\n");
    }

    public static int esDomingo(Calendar d) {
        return d.get(Calendar.DAY_OF_WEEK);
    }

}
