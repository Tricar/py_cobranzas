package Dao;

import Model.Credito;
import Model.Letras;
import Model.Historialmora;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class HistoDaoImp implements HistoDao {

    @Override
    public List<Historialmora> mostrarHistoriales() {
        Session session = null;
        List<Historialmora> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Historialmora");
            lista = (List<Historialmora>) query.list();
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
    public List<Historialmora> mostrarHistxCredito(Credito credito) {
        Session session = null;
        List<Historialmora> lista = null;
        Integer idcred = credito.getIdventa();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Historialmora where letras.credito.idventa=:v order by fecreg desc, descripcion desc");
            query.setParameter("v", idcred);
            lista = (List<Historialmora>) query.list();
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
    public List<Historialmora> mostrarHistxLetras(Letras letra) {
        Session session = null;
        List<Historialmora> lista = null;
        Integer idletra = letra.getIdletras();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Historialmora WHERE idletras=:v");
            query.setParameter("v", idletra);
            lista = (List<Historialmora>) query.list();
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
    public void insertarHist(Historialmora historial) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(historial);
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
    public void modificarHist(Historialmora historials) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(historials);
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
    public void eliminarHist(Historialmora historial) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(historial);
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
    public Historialmora getbyId(Integer idhistorial) {
        Session session = null;
        Historialmora hist = new Historialmora();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Historialmora WHERE idhistorialmora=:id" );
            query.setParameter("id", idhistorial);
            hist = (Historialmora) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return hist;
    }
}
