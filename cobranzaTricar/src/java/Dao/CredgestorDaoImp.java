package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Creditogestor;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class CredgestorDaoImp implements CredgestorDao {

    @Override
    public List<Creditogestor> mostrarCreditogestors() {
        Session session = null;
        List<Creditogestor> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditogestor");
            lista = (List<Creditogestor>) query.list();
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
    public List<Creditogestor> gestores (Credito credito) {
        Session session = null;
        List<Creditogestor> lista = null;        
        int idventa = credito.getIdventa();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditogestor WHERE idanexo=:id");
            query.setParameter("id", idventa);
            lista = (List<Creditogestor>) query.list();
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
    public void insertarCreditogestor(Creditogestor credgestor) {        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(credgestor);
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
    public void modificarCreditogestor(Creditogestor credgestor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(credgestor);
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
    public void eliminarCreditogestor(Creditogestor credgestor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(credgestor);
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
    public Creditogestor getbyId(Integer idcredgestor) {
        Session session = null;
        Creditogestor credgestor = new Creditogestor();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditogestor WHERE idcreditogestor=:id" );
            query.setParameter("id", idcredgestor);
            credgestor = (Creditogestor) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return credgestor;
    }

    @Override
    public Creditogestor getbyAnexoCredito(Anexo anexo, Credito credito) {
        Session session = null;
        Creditogestor credgestor = new Creditogestor();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditogestor WHERE idanexo=:idan and idventa=:idvta" );
            query.setParameter("idan", anexo);
            query.setParameter("idvta", credito);
            credgestor = (Creditogestor) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return credgestor;
    }
    
    @Override
    public List<Creditogestor> getbyAnexo(Anexo anexo) {
        Session session = null;
        List<Creditogestor> lista = null;          
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Creditogestor WHERE idanexo=:idan");
            query.setParameter("idan", anexo);            
            lista = query.list();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
       
}
