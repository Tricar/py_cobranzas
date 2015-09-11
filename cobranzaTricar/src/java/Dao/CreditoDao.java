package Dao;

import Model.Anexo;
import Model.Credito;
import java.util.Date;
import java.util.List;


public interface CreditoDao {
    public List<Credito> mostrarVentas();
    public void insertarVenta(Credito credito);
    public void modificarVenta (Credito credito);
    public void eliminarVenta (Credito credito);
    public List<Credito> filtrarFechas (Date date1, Date date2);
    public Credito cargarCredito(Anexo anexo);
}
