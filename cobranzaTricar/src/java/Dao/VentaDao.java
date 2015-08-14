package Dao;

import Model.Venta;
import java.util.List;


public interface VentaDao {
    public List<Venta> mostrarVentas();
    public void insertarVenta(Venta venta);
    public void modificarVenta (Venta venta);
    public void eliminarVenta (Venta venta);
}
