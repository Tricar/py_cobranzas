package Dao;

import Model.Credito;
import Model.Letras;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class LetrasDaoImplements implements LetrasDao{

    @Override
    public List<Letras> mostrarLetras() {
        Session session = null;
        List<Letras> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Letras");
            lista = (List<Letras>)query.list();
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
    public void insertarLetra(Letras letras) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(letras);
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
    public void modificarLetra(Letras letras) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(letras);
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
    public void eliminarLetra(Letras letras) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(letras);
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
    public List<Letras> mostrarLetrasXCred(Credito credito) {
        Session session = null;
        List<Letras> lista = null;
        Credito cred = new Credito();
        cred = credito;
        Integer idcred = cred.getIdventa();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Letras where idventa=:v");
            query.setParameter("v", idcred);
            lista = (List<Letras>)query.list();
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
    public List<Letras> mostrarSoloLetrasxCred(Credito credito, String let) {
        Session session = null;
        List<Letras> lista = null;
        Credito cred = new Credito();        
        cred = credito;
        Integer idcred = cred.getIdventa();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Letras where idventa=:v and descripcion!=:u");
            query.setParameter("u", let);
            query.setParameter("v", idcred);
            lista = (List<Letras>)query.list();
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
    public boolean registrar(Session session, Letras letras) throws Exception {
        session.save(letras);
        return true;
    }    

    
        
}
