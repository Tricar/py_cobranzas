package Dao;

import Model.Anexo;
import Model.Conceptos;
import Model.Credito;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface ConceptosDao{
    public List<Conceptos> mostrarConceptos();
    public List<Conceptos> mostrarConceptosXCred(Credito credito);
    public List<Conceptos> mostrarConceptosxAnexo(Anexo anexo);
    public void insertarConcepto(Conceptos conceptos);
    public void modificarConcepto (Conceptos conceptos);
    public void eliminarConcepto (Conceptos conceptos);
    public boolean registrar(Session session, Conceptos conceptos)throws Exception;
    public Conceptos veryId(Integer idconcepto);
    public Conceptos veryIdCredito(Credito credito);
    public List<Conceptos> filtrarxFechas(Date f1, Date f2, String tipo, Boolean cobrado);
    public List<Conceptos> mostrarPendXAnexos(Anexo anexo, Boolean cobrado);
    public Conceptos mostrarPendXCredito(Credito credito, Boolean cobrado);
}

