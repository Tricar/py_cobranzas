package Dao;

import Model.Caja;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class CajaDaoImp implements CajaDao {

    @Override
    public List<Caja> mostrarCajas() {
        Session session = null;
        List<Caja> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Caja");
            lista = (List<Caja>) query.list();
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
    public List<Caja> mostrarxTienda(String tienda) {
        Session session = null;
        List<Caja> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Caja WHERE tienda=:tienda");
            query.setParameter("tienda", tienda);
            lista = (List<Caja>) query.list();
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
    public void insertarCaja(Caja caja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(caja);
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
    public void modificarCaja(Caja caja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(caja);
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
    public void eliminarCaja(Caja caja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(caja);
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
    public Caja veryId(Integer idcaja) {
        Session session = null;
        Caja caja = new Caja();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Caja WHERE idcaja=:id" );
            query.setParameter("id", idcaja);
            caja = (Caja) query.uniqueResult();            
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

    @Override
    public boolean modificar(Caja caja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "UPDATE Caja set total = :total WHERE idcaja = :idcaja";
            Query query = session.createQuery(hql);
            query.setParameter("total", caja.getTotal());
            query.setParameter("idcaja", caja.getIdcaja());
            query.executeUpdate();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }       
}