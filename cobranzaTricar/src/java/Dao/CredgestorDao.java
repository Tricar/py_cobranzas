package Dao;


import Model.Anexo;
import Model.Credito;
import java.util.List;
import Model.Creditogestor;

/**
 *
 * @author master
 */
public interface CredgestorDao {
    public List<Creditogestor> mostrarCreditogestors();
    public List<Creditogestor> gestores (Credito credito);
    public void insertarCreditogestor(Creditogestor credgestor);
    public void modificarCreditogestor (Creditogestor credgestor);
    public void eliminarCreditogestor (Creditogestor credgestor);
    public Creditogestor getbyId(Integer idcredgestor);
    public Creditogestor getbyAnexoCredito(Anexo anexo, Credito credito);
}
