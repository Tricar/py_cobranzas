package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Ocupacion;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dajoh
 */
public class OcupacionDaoImpl implements OcupacionDao {

    @Override
    public void insertarOcupacion(Ocupacion ocupacion) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ocupacion);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void modificarOcupacion(Ocupacion ocupacion) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(ocupacion);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void eliminarOcupacion(Ocupacion ocupacion) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(ocupacion);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Ocupacion> mostrarOcupaciones() {
        Session session = null;
        List<Ocupacion> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Ocupacion");
            lista = (List<Ocupacion>) query.list();
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
    public List<Ocupacion> ocupacionesxIdanexo(Anexo anexo) {
        Session session = null;
        List<Ocupacion> lista = null;
        //Integer idanexo = anexo.getIdanexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Ocupacion WHERE idanexo=:v");
            query.setParameter("v", anexo);
            lista = (List<Ocupacion>) query.list();
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
    public Ocupacion verifyIdocup(Integer idocupacion) {
        Session session = null;
        Ocupacion ocupacion = new Ocupacion();
        try {
            System.out.println("Dao : "+idocupacion);
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Ocupacion WHERE id=:idocupacion";
            Query query = session.createQuery(hql);
            query.setParameter("idocupacion", idocupacion);
            ocupacion = (Ocupacion) query.uniqueResult();
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }        
        return ocupacion;
    }

    @Override
    public List<Ocupacion> ocupacionesxIdventa(Credito credito) {
        Session session = null;
        Integer idventa = credito.getIdventa();
        List<Ocupacion> lista = null;
        //Integer idanexo = anexo.getIdanexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Ocupacion WHERE idventa=:v");
            query.setParameter("v", idventa);
            lista = (List<Ocupacion>) query.list();
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
    public void delete(Ocupacion ocup) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(ocup);
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

}
