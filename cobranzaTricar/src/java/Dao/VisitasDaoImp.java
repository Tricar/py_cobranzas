package Dao;

import Model.Visitas;
import Persistencia.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class VisitasDaoImp implements VisitasDao {

    @Override
    public List<Visitas> mostrarVisitas() {
        Session session = null;
        List<Visitas> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Visitas");
            lista = (List<Visitas>) query.list();
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
    public void insertarVisitas(Visitas visitas) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(visitas);
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
    public void modificarVisitas(Visitas visitas) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(visitas);
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
    public void eliminarVisitas(Visitas visitas) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(visitas);
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
    public Visitas getbyId(Integer idvisitas) {
        Session session = null;
        Visitas caja = new Visitas();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Visitas WHERE idvisitas=:id" );
            query.setParameter("id", idvisitas);
            caja = (Visitas) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return caja;
    }
    
    public List<Visitas> historial (Date f1, Date f2){
        Session session = null;
        List<Visitas> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Visitas where fecreg BETWEEN :f1 and :f2");
            query.setParameter("f1", f1);
            query.setParameter("f2", f2);            
            lista = query.list();
        }catch (HibernateException e){            
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
       
}
