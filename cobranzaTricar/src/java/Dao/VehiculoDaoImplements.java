package Dao;

import Model.Vehiculo;
import Persistencia.HibernateUtil;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author master
 */
public class VehiculoDaoImplements implements VehiculoDao{

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(vehiculo);
            session.getTransaction().commit();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Vehiculo> buscarxNombre(String nombre) {
        Session session = null;
        try{
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Vehiculo.class);
        if (StringUtils.isNotBlank(nombre)){
            criteria.add(Restrictions.ilike("serie",nombre.toUpperCase(),MatchMode.START));
        }
        return criteria.list();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return null;
    }
    
    @Override
    public List<Vehiculo> filtarDisponible(String tipo) {
        Session session = null;
        List<Vehiculo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Vehiculo WHERE estado=:v");
            query.setParameter("v", tipo);            
            lista = (List<Vehiculo>) query.list();
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
    public boolean registrar(Session session, Vehiculo vehiculo) throws Exception {
        session.save(vehiculo);
        return true;
    }

    @Override
    public List<Vehiculo> verTodo(Session session) throws Exception {
        String hql = "FROM Vehiculo";
        Query query = session.createQuery(hql);        
        List<Vehiculo> lista = (List<Vehiculo>)query.list();        
        return lista;
    }

    @Override
    public Vehiculo verByIdvehiculo(Session session, Integer idvehiculo) throws Exception {
        String hql = "FROM Vehiculo WHERE idvehiculo=:idvehiculo";
        Query query = session.createQuery(hql);
        query.setParameter("idvehiculo", idvehiculo);        
        Vehiculo vehiculo = (Vehiculo) query.uniqueResult();        
        return vehiculo;
    }

    @Override
    public Vehiculo verBySerie(Session session, String serie) throws Exception {
        String hql = "FROM Vehiculo WHERE serie=:serie";
        Query query = session.createQuery(hql);
        query.setParameter("serie", serie);        
        Vehiculo tserie = (Vehiculo) query.uniqueResult();        
        return tserie;
    }

    @Override
    public Vehiculo verBySerieDifer(Session session, Integer idvehiculo, String serie) throws Exception {
        String hql = "FROM Vehiculo WHERE idvehiculo!=:idvehiculo and serie=:serie";
        Query query = session.createQuery(hql);
        query.setParameter("idvehiculo", idvehiculo);
        query.setParameter("serie", serie);        
        Vehiculo tserie = (Vehiculo) query.uniqueResult();        
        return tserie;
    }

    @Override
    public boolean modificar(Session session, Vehiculo vehiculo) throws Exception {
        session.update(vehiculo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Vehiculo vehiculo) throws Exception {
        session.delete(vehiculo);
        return true;
    }
}
