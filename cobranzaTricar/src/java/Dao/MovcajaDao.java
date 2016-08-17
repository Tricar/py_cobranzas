package Dao;

import Model.Movcaja;
import Model.Movcaja;
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
}
