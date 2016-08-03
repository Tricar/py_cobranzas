package Dao;

import Model.Credito;
import Model.Requisitos;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class RequisitosDaoImp implements RequisitosDao{

    @Override
    public List<Requisitos> mostrarRequisitos() {
        Session session = null;
        List<Requisitos> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Requisitos");
            lista = (List<Requisitos>)query.list();
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

    @Override
    public void insertarRequisito(Requisitos requisitos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(requisitos);
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
    public void modificarRequisito(Requisitos requisitos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(requisitos);
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
    public void eliminarRequisito(Requisitos requisitos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(requisitos);
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
    public Requisitos mostrarRequisitosXCred(Credito credito) {
        Session session = null;
        Requisitos req = null;
        Integer idcred = credito.getIdventa();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Requisitos where idventa=:v");
            query.setParameter("v", idcred);
            req = (Requisitos)query.uniqueResult();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return req;
    }
    
    @Override
    public Requisitos veryId(Integer idrequisitos) {
        Session session = null;
        Requisitos concepto = new Requisitos();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Requisitos WHERE idrequisitos=:id" );
            query.setParameter("id", idrequisitos);
            concepto = (Requisitos) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return concepto;
    }

    @Override
    public Requisitos veryIdCredito(Credito credito) {
        Session session = null;
        Requisitos concepto = new Requisitos();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Requisitos WHERE idventa=:id" );
            query.setParameter("id", credito);
            concepto = (Requisitos) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return concepto;
    }

    
        
}

