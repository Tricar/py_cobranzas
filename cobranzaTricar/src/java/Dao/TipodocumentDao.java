package Dao;

import Model.Tipodocument;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TipodocumentDao {    
    public boolean registrar(Session session, Tipodocument tipoventa)throws Exception;
    public List<Tipodocument> verTodo(Session session) throws Exception; 
    public Tipodocument verByCodigo(Session session, Integer idtipoventa) throws Exception;
    public Tipodocument verByDescripcion(Session session, String descripcion) throws Exception;
    public Tipodocument verByColorDifer(Session session,Integer idtipoventa, String descripcion) throws Exception;
    public boolean modificar(Session session, Tipodocument tipoventa) throws Exception;
    public boolean eliminar (Session session, Tipodocument tipoventa) throws Exception;
}
