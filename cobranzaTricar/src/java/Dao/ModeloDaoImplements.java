package Dao;

import Model.Modelo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class ModeloDaoImplements implements ModeloDao{

    @Override
    public boolean registrar(Session session, Modelo model) throws Exception {
        session.save(model);
        return true;
    }

    @Override
    public List<Modelo> verTodo(Session session) throws Exception {
        String hql = "FROM Modelo";
        Query query = session.createQuery(hql);

        List<Modelo> listaModelo = (List<Modelo>) query.list();

        return listaModelo;
    }

    @Override
    public Modelo verByCodigo(Session session, Integer idmodelo) throws Exception {
        return (Modelo) session.get(Modelo.class, idmodelo);
    }

    @Override
    public Modelo verByModelo(Session session, String modelo) throws Exception {
        String hql = "FROM Modelo WHERE modelo=:modelo";
        Query query = session.createQuery(hql);
        query.setParameter("modelo", modelo);

        Modelo model = (Modelo) query.uniqueResult();

        return model;
    }

    @Override
    public Modelo verByModeloDifer(Session session, Integer idmodelo, String modelo) throws Exception {
        String hql = "FROM Modelo WHERE idmodelo!=:idmodelo and modelo=:modelo";
        Query query = session.createQuery(hql);
        query.setParameter("idmodelo", idmodelo);
        query.setParameter("modelo", modelo);
        
        Modelo model = (Modelo) query.uniqueResult();
        
        return model;
    }

    @Override
    public boolean modificar(Session session, Modelo model) throws Exception {
        session.update(model);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Modelo model) throws Exception {
        session.delete(model);
        return true;
    }
    
}
