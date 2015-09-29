package Dao;

import Model.Modelo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface ModeloDao {    
    public boolean registrar(Session session, Modelo model)throws Exception;
    public List<Modelo> verTodo(Session session) throws Exception;
    public Modelo verByCodigo(Session session, Integer idmodelo) throws Exception;
    public Modelo verByModelo(Session session, String modelo) throws Exception;
    public Modelo verByModeloDifer(Session session,Integer idmodelo, String modelo) throws Exception;
    public boolean modificar(Session session, Modelo model) throws Exception;
    public boolean eliminar (Session session, Modelo model) throws Exception;
}
