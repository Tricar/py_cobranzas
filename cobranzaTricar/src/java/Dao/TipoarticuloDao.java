package Dao;

import Model.Tipoarticulo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TipoarticuloDao {    
    public boolean registrar(Session session, Tipoarticulo tipoarticulo)throws Exception;
    public List<Tipoarticulo> verTodo(Session session) throws Exception;
    public Tipoarticulo verByCodigo(Session session, Integer idtipoarticulo) throws Exception;
    public Tipoarticulo verByDescripcion(Session session, String descripcion) throws Exception;
    public Tipoarticulo verByDescripcionDifer(Session session,Integer idtipoarticulo, String descripcion) throws Exception;
    public boolean modificar(Session session, Tipoarticulo tipoarticulo) throws Exception;
    public boolean eliminar (Session session, Tipoarticulo tipoarticulo) throws Exception;
}
