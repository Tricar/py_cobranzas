package Dao;

import Model.Caja;
import java.util.List;

/**
 *
 * @author master
 */
public interface CajaDao {
    public List<Caja> mostrarCajas();
    public List<Caja> mostrarxTienda(String tienda);
    public void insertarCaja(Caja caja);
    public void modificarCaja (Caja caja);
    public void eliminarCaja (Caja caja);
    public Caja veryId(Integer idcaja);   
    public boolean modificar (Caja caja); 
}
