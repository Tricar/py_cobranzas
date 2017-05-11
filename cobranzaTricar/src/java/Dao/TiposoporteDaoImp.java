/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tiposoporte;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TiposoporteDaoImp implements TiposoporteDao{    

    @Override
    public boolean registrar(Session session, Tiposoporte tiposoporte) throws Exception {
        session.save(tiposoporte);
        return true;
    }

    @Override
    public List<Tiposoporte> verTodo(Session session) throws Exception {
        String hql = "FROM Tiposoporte";
        Query query = session.createQuery(hql);

        List<Tiposoporte> lista = (List<Tiposoporte>) query.list();

        return lista;
    }

    @Override
    public Tiposoporte verByCodigo(Session session, Integer idtiposoporte) throws Exception {
        return (Tiposoporte) session.get(Tiposoporte.class, idtiposoporte);
    }

    @Override
    public Tiposoporte verByDescripcion(Session session, String tiposoportes) throws Exception {
        String hql = "FROM Tiposoporte WHERE tiposoporte=:tiposoportes";
        Query query = session.createQuery(hql);
        query.setParameter("tiposoportes", tiposoportes);
        Tiposoporte colo = (Tiposoporte) query.uniqueResult();
        return colo;
    }

    @Override
    public Tiposoporte verByDescripcionDifer(Session session, Integer idtiposoporte, String tiposoportes) throws Exception {
        String hql = "FROM Tiposoporte WHERE idtiposoporte!=:idtiposoporte and tiposoporte=:tiposoportes";
        Query query = session.createQuery(hql);
        query.setParameter("idtiposoporte", idtiposoporte);
        query.setParameter("tiposoportes", tiposoportes);        
        Tiposoporte colo = (Tiposoporte) query.uniqueResult();
        return colo;
    }

    @Override
    public boolean modificar(Session session, Tiposoporte tiposoporte) throws Exception {
        session.update(tiposoporte);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Tiposoporte tiposoporte) throws Exception {
        session.delete(tiposoporte);
        return true;
    }
    
}