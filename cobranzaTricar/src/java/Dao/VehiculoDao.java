
package Dao;

import Model.Vehiculo;
import java.util.List;
import org.hibernate.Session;

public interface VehiculoDao {
    
    public void modificarVehiculo (Vehiculo vehiculo);
    public List<Vehiculo> buscarxNombre(String nombre);
    public List<Vehiculo> filtarDisponible(String tipo);
    
    public boolean registrar(Session session, Vehiculo vehiculo)throws Exception;
    public List<Vehiculo> verTodo(Session session) throws Exception;
    public Vehiculo verByIdvehiculo(Session session, Integer idvehiculo) throws Exception;
    public Vehiculo verBySerie(Session session, String serie) throws Exception;
    public Vehiculo verBySerieDifer(Session session,Integer idvehiculo, String serie) throws Exception;
    public boolean modificar(Session session, Vehiculo vehiculo) throws Exception;
    public boolean eliminar (Session session, Vehiculo vehiculo) throws Exception;
}
