package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Letras;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;


public interface CreditoDao {
    public List<Credito> mostrarVentas();
    public void insertarVenta(Credito credito);
    public void modificarVenta (Credito credito);
    public void eliminarVenta (Credito credito);
    public List<Credito> filtrarFechas (Date date1, Date date2, String estado);
    public Credito cargarCreditoxAnexo(Anexo anexo);
    public Credito cargarCreditoxLetra(Letras letra);
    public List<Credito> filtrarDni (String dni);
    public Integer ventasXdia(Session session);
    public Integer ventasXmes(Session session);
    public Integer creditoXaprobar(Session session);
    public Integer creditoAprobado(Session session);
    public Integer pagosxdia(Session session);
    public Integer pagosxmes(Session session);
    public List<Credito> filtrarCreditoxAnexo(Anexo anexo);
    public Credito cargarxCodigoEstado (String codigo, String estado);
    public List<Credito> cargarxEstado (String estado);
    public List<Credito> cargarxEstadoDistinto (String estado);
    public List<Credito> cargarTodosxCalif (String calif);
    public Credito cargarxCodigoCalif (String codigo, String calif);
    public Credito cargarxCodigoEstadoDos (String codigo, String estado, String estado1);    
    public Credito cargarxCodigoEst (String codigo, String condicionpago);
    public Credito cargarxCodigoVenta (String codigo, String estado, String estado1);  
    public Credito veryLiqventa (String liq);
    public Credito veryId(int idventa);
    public boolean registrar(Session session, Credito credito)throws Exception;
    public List<Credito> cargarxEstadoRef (String estado);
    public List<Credito> cargarxRef (Boolean valor);
}
