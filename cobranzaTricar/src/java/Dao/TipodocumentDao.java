package Dao;

import Model.Tipodocument;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public interface TipodocumentDao {
    public List<Tipodocument> verTodo(Session session) throws Exception;
}
