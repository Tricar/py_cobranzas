package Dao;

import Model.Modelo;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class ModeloDaoImplements implements ModeloDao {

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

    @Override
    public List<Modelo> modeloNombre() {
        Session session = null;
        List<Modelo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Modelo");
            lista = (List<Modelo>) query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }

    @Override
    public List<Modelo> modeloxTipo(String tipo) {
        Session session = null;
        List<Modelo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Modelo WHERE tipo=:tipo");
            query.setParameter("tipo", tipo);
            lista = (List<Modelo>) query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;

    }

    @Override
    public String modeloDevId(String modelo) {
        Session session = null;
        String idmodelo = "";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Modelo WHERE modelo=:modelo");
            query.setParameter("modelo", modelo);
            idmodelo = (String)query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idmodelo;
    }

}
