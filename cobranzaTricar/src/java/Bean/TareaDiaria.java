package Bean;

import Dao.CreditoDao;
import Dao.CreditoDaoImp;
import Dao.LetrasDao;
import Dao.LetrasDaoImplements;
import Model.Credito;
import Model.Letras;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup

public class TareaDiaria {

    private List<Letras> letraslista = new ArrayList();
    private List<Credito> creditos = new ArrayList();

//    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false) //correr cada minuto
    @Schedule(hour = "01", minute = "00", second = "0", persistent = false)
    public void someDailyJob() {
        letraslista = new ArrayList();
        CreditoDao credao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        creditos = credao.mostrarVentas();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
//        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
//        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal interes = new BigDecimal(BigInteger.ZERO);
        BigDecimal factormora = new BigDecimal(0.002);
        //BigDecimal cien = new BigDecimal(100);
        //cinco = cinco.divide(cien);
        LetrasDao letrasdao = new LetrasDaoImplements();
        for (int i = 0; i < creditos.size(); i++) {
            Credito cred = creditos.get(i);
            System.out.println("credito " + cred.getLiqventa() + " anexo" + cred.getAnexo().getNombres());
            int contvn = 0;
            if (!cred.getEstado().equals("EM")) {
                if (!cred.getCalificacion().equals("CN")) {
                    letritas = letrasdao.mostrarLetrasXCred(cred);
                    for (int j = 0; j < letritas.size(); j++) {
                        Letras letras = letritas.get(j);
//                    if (letras.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
//                        letras.setEstado("CN");
//                        contcn++;
//                    } else {
                        if (!letras.getDescripcion().equals("ND")) {
                            if (letras.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
                                if (letras.getFecven().after(fecha)) {
                                    letras.setEstado("PN");
                                } else {
                                    letras.setEstado("VN");
                                    letras.setDiffdays(Diffdays(letras.getFecven()));
                                    letras.setCobradonc(true);
                                    contvn++;
                                }
                            }
//                        moraant = letras.getMora();
//                        moraact = (letras.getSaldo().multiply(interes)).setScale(1, RoundingMode.UP);
                            if (letras.getEstado().equals("VN")) {
                                if (letras.getDiffdays() > 8) {
                                    interes = factormora.multiply(BigDecimal.valueOf(letras.getDiffdays())).setScale(5, RoundingMode.HALF_DOWN);
                                    letras.setMora(letras.getSaldo().multiply(interes).setScale(0, RoundingMode.UP));
                                } else {
                                    letras.setMora(BigDecimal.ZERO);
                                }
//                            if (moraact.compareTo(moraant) == -1) {
//                                letras.setMora(moraant);
//                            } else {
//                                letras.setMora(moraact);
//                            }
                            }
                            letrasdao.modificarLetra(letras);
                        }
//                    }
                    }
                    if (contvn == letritas.size()) {
                        cred.setCalificacion("VN");
                    } else {
                        cred.setCalificacion("PN");
                    }
                    credao.modificarVenta(cred);
                }
            }
        }
        System.out.println("ejecuté toda la linea de actualizar los creditos");
    }

//    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
//    public void someMinuteJob() {
//        // Do your job here which should run every 15 minute of hour.
//        System.out.println("Imprimir cada 1 mins");
//    }
    public long Diffdays(Date fechavenc) {
        long mili = fechavenc.getTime();
        long mili2 = new Date().getTime();
        long diff = mili2 - mili;
        long diffdays = diff / (24 * 60 * 60 * 1000);
        return diffdays;
    }

    public List<Letras> getLetraslista() {
        return letraslista;
    }

    public void setLetraslista(List<Letras> letraslista) {
        this.letraslista = letraslista;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

}
