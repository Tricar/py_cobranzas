package Dao;

import Model.Morosidad;
import java.util.List;

/**
 *
 * @author master
 */
public interface MorosidadDao {
    public List<Morosidad> todas();
    public List<Morosidad> morosidadxTienda(String tienda);
    public void insertarMorosidad(Morosidad morosidad);
    public void modificarMorosidad (Morosidad morosidad);
    public void eliminarMorosidad (Morosidad morosidad);
    public Morosidad getbyId(Integer idmorosidad);    
}
