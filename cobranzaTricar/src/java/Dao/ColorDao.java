package Dao;

import Model.Color;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface ColorDao {    
    public boolean registrar(Session session, Color colo)throws Exception;
    public List<Color> verTodo(Session session) throws Exception;    
    public List<Color> filtarTipoDos();
    public Color verByCodigo(Session session, Integer idcolor) throws Exception;
    public Color verByColor(Session session, String color) throws Exception;
    public Color verByColorDifer(Session session,Integer idcolor, String color) throws Exception;
    public boolean modificar(Session session, Color colo) throws Exception;
    public boolean eliminar (Session session, Color colo) throws Exception;
}
