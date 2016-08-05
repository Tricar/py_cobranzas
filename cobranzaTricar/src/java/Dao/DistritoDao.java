package Dao;

import Model.Distrito;
import java.util.List;
import org.hibernate.Session;

public interface DistritoDao {
    
    public boolean registrar(Session session, Distrito distrito)throws Exception;
    public List<Distrito> verTodo(Session session) throws Exception;
    public Distrito verById(Session session, Integer iddistrito) throws Exception;
    public Distrito verByDistrito(Session session, String distrito) throws Exception;
    public Distrito verByDistritoDifer(Session session,Integer iddistrito, String distrito) throws Exception;
    public boolean modificar(Session session, Distrito distrito) throws Exception;
    public boolean eliminar (Session session, Distrito distrito) throws Exception;
    
}
