package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Creditoaval;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class CredavalDaoImp implements CredavalDao {

    @Override
    public List<Creditoaval> mostrarCreditoavals() {
        Session session = null;
        List<Creditoaval> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditoaval");
            lista = (List<Creditoaval>) query.list();
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
    public List<Creditoaval> avales (Credito credito) {
        Session session = null;
        List<Creditoaval> lista = null;        
        int idventa = credito.getIdventa();        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditoaval WHERE idventa=:id");
            query.setParameter("id", idventa);
            lista = (List<Creditoaval>) query.list();
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
    public void insertarCreditoaval(Creditoaval credaval) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(credaval);
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
    public void modificarCreditoaval(Creditoaval credaval) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(credaval);
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
    public void eliminarCreditoaval(Creditoaval credaval) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(credaval);
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
    public Creditoaval getbyId(Integer idcredaval) {
        Session session = null;
        Creditoaval credaval = new Creditoaval();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditoaval WHERE idcreditoaval=:id" );
            query.setParameter("id", idcredaval);
            credaval = (Creditoaval) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return credaval;
    }

    @Override
    public Creditoaval getbyAnexoCredito(Anexo anexo, Credito credito) {
        Session session = null;
        Creditoaval credaval = new Creditoaval();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditoaval WHERE idanexo=:idan and idventa=:idvta" );
            query.setParameter("idan", anexo);
            query.setParameter("idvta", credito);
            credaval = (Creditoaval) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return credaval;
    }
       
}
