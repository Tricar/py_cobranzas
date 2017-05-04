package Dao;


import Model.Anexo;
import Model.Credito;
import java.util.List;
import Model.Creditoaval;

/**
 *
 * @author master
 */
public interface CredavalDao {
    public List<Creditoaval> mostrarCreditoavals();
    public List<Creditoaval> avales (Credito credito);
    public void insertarCreditoaval(Creditoaval credaval);
    public void modificarCreditoaval (Creditoaval credaval);
    public void eliminarCreditoaval (Creditoaval credaval);
    public Creditoaval getbyId(Integer idcredaval);
    public Creditoaval getbyAnexoCredito(Anexo anexo, Credito credito);
}
