package Dao;

import Model.Credito;
import Model.Letras;
import Model.Pagos;
import java.util.List;

/**
 *
 * @author master
 */
public interface PagosDao {
    public List<Pagos> mostrarPagos();
    public List<Pagos> mostrarPagosxCredito(Credito credito);
    public List<Pagos> mostrarPagosxLetras(Letras letra);
    public void insertarPago(Pagos pagos);
    public void modificarPago (Pagos pagos);
    public void eliminarPago (Pagos pagos);    
}
