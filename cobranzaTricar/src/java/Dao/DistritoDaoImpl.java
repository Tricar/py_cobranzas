package Dao;

import Model.Distrito;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DistritoDaoImpl implements DistritoDao {

    @Override
    public boolean registrar(Session session, Distrito distrito) throws Exception {
        Date d = new Date();
        distrito.setFechreg(d);
        session.save(distrito);
        return true;
    }

    @Override
    public List<Distrito> verTodo(Session session) throws Exception {
        String hql = "FROM Distrito";
        Query query = session.createQuery(hql);
        
        List<Distrito> listaTdistrito = (List<Distrito>)query.list();
        
        return listaTdistrito;
    }

    @Override
    public Distrito verByDistrito(Session session, String distrito) throws Exception {
        String hql = "FROM Distrito WHERE distrito=:distrito";
        Query query = session.createQuery(hql);
        query.setParameter("distrito", distrito);        
        Distrito tdistrito = (Distrito) query.uniqueResult();        
        return tdistrito;
    }

    @Override
    public Distrito verByDistritoDifer(Session session, Integer iddistrito, String distrito) throws Exception {
        String hql = "FROM Distrito WHERE iddistrito!=:iddistrito and distrito=:distrito";
        Query query = session.createQuery(hql);
        query.setParameter("iddistrito", iddistrito);
        query.setParameter("distrito", distrito);        
        Distrito tdistrito = (Distrito) query.uniqueResult();        
        return tdistrito;
    }

    @Override
    public boolean modificar(Session session, Distrito distrito) throws Exception {
        session.update(distrito);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Distrito distrito) throws Exception {
        session.delete(distrito);
        return true;
    }

    @Override
    public Distrito verById(Session session, Integer iddistrito) throws Exception {
        String hql = "FROM Distrito WHERE iddistrito=:iddistrito";
        Query query = session.createQuery(hql);
        query.setParameter("iddistrito", iddistrito);
        
        Distrito tdistrito = (Distrito) query.uniqueResult();
        
        return tdistrito;
    }

    
    
}