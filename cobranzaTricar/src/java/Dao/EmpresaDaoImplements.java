package Dao;

import Model.Empresa;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class EmpresaDaoImplements implements EmpresaDao{

    @Override
    public List<Empresa> mostrarEmpresa() {
        Session session = null;
        List<Empresa> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Empresa");
            lista = (List<Empresa>)query.list();
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
    public void insertarEmpresa(Empresa empresa) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(empresa);
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
    public void modificarEmpresa(Empresa empresa) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(empresa);
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
    public void eliminarEmpresa(Empresa empresa) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(empresa);
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
    
    public Empresa objEmpresa(int id) {
        Empresa obj = new Empresa();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.load(obj, id);
        session.close();
        return obj;
    }
    
}
