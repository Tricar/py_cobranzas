package Dao;

import Model.Anexo;
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
public class OcupacionDaoImpl implements OcupacionDao{

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
    
}