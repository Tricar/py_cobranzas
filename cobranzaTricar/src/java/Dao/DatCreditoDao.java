package Dao;

import Model.Datoscredito;
import java.util.List;

/**
 *
 * @author master
 */
public interface DatCreditoDao {
    public List<Datoscredito> mostrarDatCredito();
    public void insertarDatCredito(Datoscredito datcredito);
    public void modificarDatCredito (Datoscredito datcredito);
    public void eliminarDatCredito (Datoscredito datcredito);
}
