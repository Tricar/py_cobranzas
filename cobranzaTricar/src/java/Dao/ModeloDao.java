package Dao;

import Model.Modelo;
import java.util.List;

/**
 *
 * @author master
 */
public interface ModeloDao {
    public List<Modelo> mostrarModelo();
    public void insertarModelo(Modelo modelo);
    public void modificarModelo (Modelo modelo);
    public void eliminarModelo (Modelo modelo);
}
