package Dao;

import Model.Servicio;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface servicioDao {    
    public boolean registrar(Session session, Servicio servicio)throws Exception;
    public List<Servicio> verTodo(Session session) throws Exception;
    public Servicio verByCodigo(Session session, Integer idservicio) throws Exception;
    public Servicio verByDescripcion(Session session, String descripcion) throws Exception;
    public Servicio verByDescripcionDifer(Session session,Integer idservicio, String descripcion) throws Exception;
    public boolean modificar(Session session, Servicio servicio) throws Exception;
    public boolean eliminar (Session session, Servicio servicio) throws Exception;
}
