package Dao;

import Model.Caja;
import Model.Movcaja;
import Model.Movcaja;
import Model.Pagos;
import java.util.List;

/**
 *
 * @author master
 */
public interface MovcajaDao {
    public List<Movcaja> mostrarMovcajas();    
    public void insertarMovcaja(Movcaja movcaja);
    public void modificarMovcaja (Movcaja movcaja);
    public void eliminarMovcaja (Movcaja movcaja);
    public Movcaja getbyId(Integer idmovcaja);
    public Movcaja devolverxIdcajaIdconceptos(Integer idcaja, Integer idconceptos);
    public Movcaja devolverCajaPagos(Caja caja, Pagos pago);
}
