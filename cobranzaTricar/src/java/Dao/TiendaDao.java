package Dao;

import Model.Tienda;
import java.util.List;

/**
 *
 * @author master
 */
public interface TiendaDao {
    public List<Tienda> mostrarTienda();
    public void insertarTienda(Tienda tienda);
    public void modificarTienda (Tienda tienda);
    public void eliminarTienda (Tienda tienda);
}
