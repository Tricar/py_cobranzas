package Dao;

import Model.Articulo;
import Model.Color;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface ArticuloDao {    
    public boolean registrar(Session session, Articulo articulo)throws Exception;
    public List<Articulo> verTodo(Session session) throws Exception;    
    public List<Color> filtarTipoDos();
    public Articulo verByCodigo(Session session, Integer idarticulo) throws Exception;
    public Articulo verByDescripcion(Session session, String descripcion) throws Exception;
    public Articulo verByDescripcionDifer(Session session,Integer idarticulo, String descripcion) throws Exception;
    public boolean modificar(Session session, Articulo articulo) throws Exception;
    public boolean eliminar (Session session, Articulo articulo) throws Exception;
}
