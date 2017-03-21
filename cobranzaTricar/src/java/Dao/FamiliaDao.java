package Dao;

import Model.Familia;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface FamiliaDao {    
    public boolean registrar(Session session, Familia familia)throws Exception;
    public List<Familia> verTodo(Session session) throws Exception;    
    public List<Familia> filtarTipoDos();
    public Familia verByCodigo(Session session, Integer idfamilia) throws Exception;
    public Familia verByFamilia(Session session, String familia) throws Exception;
    public Familia verByFamiliaDifer(Session session,Integer idfamilia, String familia) throws Exception;
    public boolean modificar(Session session, Familia familia) throws Exception;
    public boolean eliminar (Session session, Familia familia) throws Exception;
}
