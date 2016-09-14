package Dao;

import Model.Tipoventa;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TipoventaDao {    
    public boolean registrar(Session session, Tipoventa tipoventa)throws Exception;
    public List<Tipoventa> verTodo(Session session) throws Exception;    
    public List<Tipoventa> filtarTipoDos();
    public Tipoventa verByCodigo(Session session, Integer idtipoventa) throws Exception;
    public Tipoventa verByDescripcion(Session session, String descripcion) throws Exception;
    public Tipoventa verByColorDifer(Session session,Integer idtipoventa, String descripcion) throws Exception;
    public boolean modificar(Session session, Tipoventa tipoventa) throws Exception;
    public boolean eliminar (Session session, Tipoventa tipoventa) throws Exception;
}
