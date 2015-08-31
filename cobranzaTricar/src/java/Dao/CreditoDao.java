package Dao;

import Model.Credito;
import java.util.List;


public interface CreditoDao {
    public List<Credito> mostrarVentas();
    public void insertarVenta(Credito credito);
    public void modificarVenta (Credito credito);
    public void eliminarVenta (Credito credito);
}
