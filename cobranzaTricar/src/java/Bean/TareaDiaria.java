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

    @Schedule(hour = "01", minute = "0", second = "0", persistent = false)
    public void someDailyJob() {
        letrasBean letbean = new letrasBean();
        letraslista = new ArrayList();
        CreditoDao credao = new CreditoDaoImp();
        List<Letras> letritas = new ArrayList();
        creditos = credao.mostrarVentas();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        BigDecimal moraant = new BigDecimal(BigInteger.ZERO);
        BigDecimal moraact = new BigDecimal(BigInteger.ZERO);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal cien = new BigDecimal(100);
        cinco = cinco.divide(cien);
        LetrasDao letrasdao = new LetrasDaoImplements();
        for (int i = 0; i < creditos.size(); i++) {
            Credito cred = creditos.get(i);
            letritas = letrasdao.mostrarLetrasXCred(cred);
            for (int j = 0; j < letritas.size(); j++) {
                Letras get = letritas.get(j);
                if (get.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                    get.setEstado("CN");
                } else {
                    if (get.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
                        if (get.getFecven().after(fecha)) {
                            get.setEstado("PN");
                        } else {
                            get.setEstado("VN");
                            get.setDiffdays(Diffdays(get.getFecven()));
                            get.setCobradonc(true);
                        }
                    }
                }
                moraant = get.getMora();
                moraact = (get.getSaldo().multiply(cinco)).setScale(1, RoundingMode.UP);
                if (get.getEstado().equals("VN")) {
                    if (moraact.compareTo(moraant) == -1) {
                        get.setMora(moraant);
                    } else {
                        get.setMora(moraact);
                    }
                }
                letrasdao.modificarLetra(get);
            }
        }
        System.out.println("ejecuté toda la linea de actualizar los creditos");
    }

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
