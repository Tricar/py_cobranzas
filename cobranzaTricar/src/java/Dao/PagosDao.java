package Dao;

import Model.Credito;
import Model.Letras;
import Model.Pagos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author master
 */
public interface PagosDao {
    public List<Pagos> mostrarPagos();
    public List<Pagos> mostrarPagosxCredito(Credito credito);
    public List<Pagos> mostrarPagosxLetras(Letras letra);
    public List<Pagos> filtrarFechas (Date date1, Date date2);
    public List<Pagos> filtrarxRi (String recibo);
    public Pagos mostrarPagosxVenta(String codigo);
    public Pagos devolverxIdconcepto(int idconceptos);
    public void insertarPago(Pagos pagos);
    public void modificarPago (Pagos pagos);
    public void eliminarPago (Pagos pagos);
    public boolean eliminar (String codigo);   
}
