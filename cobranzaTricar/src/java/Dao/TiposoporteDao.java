package Dao;

import Model.Tiposoporte;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TiposoporteDao {    
    public boolean registrar(Session session, Tiposoporte tiposoporte)throws Exception;
    public List<Tiposoporte> verTodo(Session session) throws Exception;
    public Tiposoporte verByCodigo(Session session, Integer idtiposoporte) throws Exception;
    public Tiposoporte verByDescripcion(Session session, String tiposoportes) throws Exception;
    public Tiposoporte verByDescripcionDifer(Session session,Integer idtiposoporte, String tiposoportes) throws Exception;
    public boolean modificar(Session session, Tiposoporte tiposoporte) throws Exception;
    public boolean eliminar (Session session, Tiposoporte tiposoporte) throws Exception;
}
