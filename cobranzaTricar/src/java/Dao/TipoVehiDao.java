package Dao;

import Model.Tipovehiculo;
import java.util.List;

/**
 *
 * @author master
 */
public interface TipoVehiDao {
    public List<Tipovehiculo> mostrarTipoVehiculo();
    public void insertarTipoVehiculo(Tipovehiculo tipovehi);
    public void modificarTipoVehiculo (Tipovehiculo tipovehi);
    public void eliminarTipoVehiculo (Tipovehiculo tipovehi);
    
}
