package Bean;

import Dao.RequisitosDao;
import Dao.RequisitosDaoImp;
import Model.Credito;
import Model.Requisitos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped

public class requisitosBean implements Serializable {

    public Requisitos requisito = new Requisitos();
    public List<Requisitos> requisitos;
    private Credito credito = new Credito();
    private String[] selectedReqs;
    private List<String> reqs;

    public requisitosBean() {
    }

    public List<Requisitos> getRequisitos() {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisitos = linkdao.mostrarRequisitos();
        return requisitos;
    }

    public String[] mostrarSoloRequisitosxCred(Credito cred) {
        credito = cred;
        List<String> where = new ArrayList<String>();
        RequisitosDao letdao = new RequisitosDaoImp();
        requisito = letdao.mostrarRequisitosXCred(credito);
        if (requisito.getCopdni() != null) {
            where.add(requisito.getCopdni().substring(2));
        }
        if (requisito.getCopdnicon() != null) {
            where.add(requisito.getCopdnicon().substring(2));
        }
        if (requisito.getCopdniaval() != null) {
            where.add(requisito.getCopdniaval().substring(2));
        }
        if (requisito.getCoptitulo() != null) {
            where.add(requisito.getCoptitulo().substring(2));
        }
        if (requisito.getRecibagua() != null) {
            where.add(requisito.getRecibagua().substring(2));
        }
        if (requisito.getRecibluz() != null) {
            where.add(requisito.getRecibluz().substring(2));
        }
        if (requisito.getCroq() != null) {
            where.add(requisito.getCroq().substring(2));
        }
        if (requisito.getCoprecibo() != null) {
            where.add(requisito.getCoprecibo().substring(2));
        }
        if (requisito.getOtros() != null) {
            where.add(requisito.getOtros().substring(2));
        }
        String[] selectedReqs = new String[where.size()];
        where.toArray(selectedReqs);
        return selectedReqs;
    }
    
    public String[] mostrarReqsParaModificar(Credito cred) {
        credito = cred;
        List<String> where = new ArrayList<String>();
        RequisitosDao letdao = new RequisitosDaoImp();
        requisito = letdao.mostrarRequisitosXCred(credito);
        if (requisito.getCopdni() != null) {
            where.add(requisito.getCopdni());
        }
        if (requisito.getCopdnicon() != null) {
            where.add(requisito.getCopdnicon());
        }
        if (requisito.getCopdniaval() != null) {
            where.add(requisito.getCopdniaval());
        }
        if (requisito.getCoptitulo() != null) {
            where.add(requisito.getCoptitulo());
        }
        if (requisito.getRecibagua() != null) {
            where.add(requisito.getRecibagua());
        }
        if (requisito.getRecibluz() != null) {
            where.add(requisito.getRecibluz());
        }
        if (requisito.getCroq() != null) {
            where.add(requisito.getCroq());
        }
        if (requisito.getCoprecibo() != null) {
            where.add(requisito.getCoprecibo());
        }
        if (requisito.getOtros() != null) {
            where.add(requisito.getOtros());
        }
        String[] selectedReqs = new String[where.size()];
        where.toArray(selectedReqs);
        return selectedReqs;
    }

    public void eliminarParaModificar(Credito cred) {
        credito = cred;
        RequisitosDao reqdao = new RequisitosDaoImp();
        requisito = reqdao.mostrarRequisitosXCred(credito);
        requisito.setCopdni(null);
        requisito.setCopdnicon(null);
        requisito.setCopdniaval(null);
        requisito.setCoptitulo(null);
        requisito.setRecibagua(null);
        requisito.setRecibluz(null);
        requisito.setCroq(null);
        requisito.setCoprecibo(null);
        requisito.setOtros(null);
        reqdao.modificarRequisito(requisito);
    }

    public void insertar(Credito credito, String[] requis) {
        RequisitosDao reqdao = new RequisitosDaoImp();
        selectedReqs = requis;
        requisito.setCredito(credito);
        for (int i = 0; i < requis.length; i++) {
            String requi = requis[i];
            char letra = requi.charAt(0);
            System.out.println("letra inicial: " + letra);
            System.out.println("Objeto requisito: " + requi);
            switch (letra) {
                case '1':
                    requisito.setCopdni(requi);
                    break;
                case '2':
                    requisito.setCopdnicon(requi);
                    break;
                case '3':
                    requisito.setCopdniaval(requi);
                    break;
                case '4':
                    requisito.setCoptitulo(requi);
                    break;
                case '5':
                    requisito.setRecibagua(requi);
                    break;
                case '6':
                    requisito.setRecibluz(requi);
                    break;
                case '7':
                    requisito.setCroq(requi);
                    break;
                case '8':
                    requisito.setCoprecibo(requi);
                    break;
                case '9':
                    requisito.setOtros(requi);
                    break;
            }

        }
        reqdao.insertarRequisito(requisito);
        requisito = new Requisitos();
    }

    public void modificar(Credito credito, String[] requis) {
        RequisitosDao reqdao = new RequisitosDaoImp();
        eliminarParaModificar(credito);
        for (int i = 0; i < requis.length; i++) {
            String requi = requis[i];
            char letra = requi.charAt(0);
            System.out.println("letra inicial: " + letra);
            System.out.println("Objeto requisito: " + requi);
            switch (letra) {
                case '1':
                    requisito.setCopdni(requi);
                    break;
                case '2':
                    requisito.setCopdnicon(requi);
                    break;
                case '3':
                    requisito.setCopdniaval(requi);
                    break;
                case '4':
                    requisito.setCoptitulo(requi);
                    break;
                case '5':
                    requisito.setRecibagua(requi);
                    break;
                case '6':
                    requisito.setRecibluz(requi);
                    break;
                case '7':
                    requisito.setCroq(requi);
                    break;
                case '8':
                    requisito.setCoprecibo(requi);
                    break;
                case '9':
                    requisito.setOtros(requi);
                    break;
            }
        }
        reqdao.modificarRequisito(requisito);
        requisito = new Requisitos();
    }

    public void cargarPorIdModify(Integer idrequisito) {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisito = linkdao.veryId(idrequisito);
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void cargarPorIdDelete(Integer idrequisito) {
        RequisitosDao linkdao = new RequisitosDaoImp();
        requisito = linkdao.veryId(idrequisito);
//        RequestContext.getCurrentInstance().update("formInsertar");
//        RequestContext.getCurrentInstance().execute("PF('dlginsertar').show()");
    }

    public void eliminar() {
        RequisitosDao linkdao = new RequisitosDaoImp();
        linkdao.eliminarRequisito(requisito);
    }

    public Requisitos getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisitos requisito) {
        this.requisito = requisito;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public List<String> getReqs() {
        return reqs;
    }

    public String[] getSelectedReqs() {
        return selectedReqs;
    }

    public void setSelectedReqs(String[] selectedReqs) {
        this.selectedReqs = selectedReqs;
    }

}
