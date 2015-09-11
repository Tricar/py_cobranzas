package Dao;

import Model.Credito;
import Model.Pagos;
import java.util.List;

/**
 *
 * @author master
 */
public interface PagosDao {
    public List<Pagos> mostrarPagos();
    public List<Pagos> mostrarPagosxCliente();
    public List<Pagos> mostrarPagosXCred();
    public void insertarPago(Pagos pagos);
    public void modificarPago (Pagos pagos);
    public void eliminarPago (Pagos pagos);    
}
