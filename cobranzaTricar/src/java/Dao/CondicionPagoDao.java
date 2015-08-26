package Dao;

import Model.Condicionpago;
import java.util.List;

/**
 *
 * @author master
 */
public interface CondicionPagoDao {
    public List<Condicionpago> mostrarCondicion();
    public void insertarCondicion(Condicionpago condicion);
    public void modificarCondicion (Condicionpago condicion);
    public void eliminarcondicion (Condicionpago condicion);
}
