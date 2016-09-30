package Dao;

import Model.Articulo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface ArticuloDao {    
    public boolean registrar(Session session, Articulo articulo)throws Exception;
    public List<Articulo> verTodo(Session session) throws Exception;
    public Articulo verByCodigo(Session session, Integer idarticulo) throws Exception;
    public Articulo getByIdProducto(Session session, Integer idArticulo) throws Exception;
    public Articulo getByCodigoBarras(Session session, String codigo) throws Exception;
    public List<Articulo> filtarTipo(Integer tipo);
    public Articulo verByCodigos(Integer idarticulo);    
    public boolean modificarOD(Articulo articulo);
    public Articulo verByDescripcion(Session session, String descripcion1) throws Exception;
    public Articulo verByDescripcionDifer(Session session,Integer idarticulo, String descripcion1) throws Exception;
    public boolean modificar(Session session, Articulo articulo) throws Exception;
    public boolean eliminar (Session session, Articulo articulo) throws Exception;
}
