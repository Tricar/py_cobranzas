package Dao;

import Model.Tipocambio;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TipocambioDao {    
    public boolean registrar(Session session, Tipocambio tipoc)throws Exception;
    public List<Tipocambio> verTodo(Session session) throws Exception;    
    public boolean modificar(Session session, Tipocambio tipoc) throws Exception;
    public boolean eliminar (Session session, Tipocambio tipoc) throws Exception;
}
