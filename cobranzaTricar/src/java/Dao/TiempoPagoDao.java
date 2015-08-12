package Dao;

import Model.Tiempopago;
import java.util.List;

/**
 *
 * @author master
 */
public interface TiempoPagoDao {
    public List<Tiempopago> mostrarTpago();
    public void insertarTpago(Tiempopago tpago);
    public void modificarTpago (Tiempopago tpago);
    public void eliminarTpago (Tiempopago tpago);
}
