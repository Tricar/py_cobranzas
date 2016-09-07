package Dao;

import Model.Repuesto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface repuestoDao {    
    public boolean registrar(Session session, Repuesto repuesto)throws Exception;
    public List<Repuesto> verTodo(Session session) throws Exception;
    public Repuesto verByCodigo(Session session, Integer idrepuesto) throws Exception;
    public Repuesto verByDescripcion(Session session, String descripcion) throws Exception;
    public Repuesto verByDescripcionDifer(Session session,Integer idrepuesto, String descripcion) throws Exception;
    public boolean modificar(Session session, Repuesto repuesto) throws Exception;
    public boolean eliminar (Session session, Repuesto repuesto) throws Exception;
}
