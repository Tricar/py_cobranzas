import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
//        Anexo objanexo = new Anexo();
//        AnexoDao anedao = new AnexoDaoImplements();
//        OcupacionDao ocudao = new OcupacionDaoImpl();
//        List<Ocupacion> lista = new ArrayList();
//        objanexo = anedao.cargarClientexDoc("44182252", "CN", "AD");
//        System.out.println("Anexo cargado:"+objanexo.getNombres());
//        Ocupacion ocupacion = new Ocupacion();
//       // ocupacion.setAnexo(objanexo);
//        ocupacion.setIdocupacion(1);
//        ocupacion.setTipo("IN");
//        ocupacion.setClase("FR");
//        ocupacion.setDescripcion("cajero en banca");
//        ocudao.insertarOcupacion(ocupacion);
//        lista = ocudao.ocupacionesxIdanexo(objanexo);
//        for (int i = 0; i < lista.size(); i++) {
//                Ocupacion get = lista.get(i);
//                System.out.println("Lista: "+get.getDescripcion());
//            }
        Date date = new Date();
        System.out.println(date.toString());

		// Se pueden definir formatos diferentes con la clase DateFormat
        // Obtenemos la fecha y la hora con el formato yyyy-MM-dd HH:mm:ss
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String convertido = fechaHora.format(date);
        System.out.println(convertido);

        // Obtenemos solamente la fecha pero usamos slash en lugar de guiones
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        convertido = fecha.format(date);
        System.out.println(convertido);

        // Tambien se puede obtener solo la hora
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        convertido = hora.format(date);
        System.out.println(convertido);
    }

}
