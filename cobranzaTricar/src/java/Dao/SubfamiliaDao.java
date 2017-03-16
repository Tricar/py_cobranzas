package Dao;

import Model.Subfamilia;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface SubfamiliaDao {    
    public boolean registrar(Session session, Subfamilia subfamilia)throws Exception;
    public List<Subfamilia> verTodo(Session session) throws Exception; 
    public Subfamilia verByCodigo(Session session, Integer idsubfamilia) throws Exception;
    public Subfamilia verBySubfamilia(Session session, String subfamilia) throws Exception;
    public Subfamilia verBySubfamiliaD(Session session,Integer idsubfamilia, String subfamilia) throws Exception;
    public boolean modificar(Session session, Subfamilia subfamilia) throws Exception;
    public boolean eliminar (Session session, Subfamilia subfamilia) throws Exception;
}
