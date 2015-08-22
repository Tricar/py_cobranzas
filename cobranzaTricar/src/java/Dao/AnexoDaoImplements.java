package Dao;

import Model.Anexo;
import Persistencia.HibernateUtil;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dajoh
 */
public class AnexoDaoImplements implements AnexoDao{

    @Override
    public List<Anexo> mostrarAnexo() {
        Session session = null;
        List<Anexo> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Anexo");
            lista = (List<Anexo>)query.list();
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
    public void insertarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(anexo);
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
    public void modificarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(anexo);
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
    public void eliminarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(anexo);
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
    public List<Anexo> filtarTipoAnexo(int tipo) {
        Session session = null;
        List<Anexo> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Anexo where idtipoanexo=:v");
            query.setParameter("v", tipo);
            lista = (List<Anexo>)query.list();
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
    public List<Anexo> filtarTipoCliente(int tipo1, int tipo2) {
        Session session = null;
        List<Anexo> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Anexo where idtipoanexo=:u or idtipoanexo=:v");
            query.setParameter("u", tipo1);
            query.setParameter("v", tipo2);
            lista = (List<Anexo>)query.list();
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
    public List<Anexo> buscarxNombre(String nombre) {
        Session session = null;
        try{
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Anexo.class);
        if (StringUtils.isNotBlank(nombre)){
            criteria.add(Restrictions.ilike("nombre",nombre.toUpperCase(),MatchMode.START)); 
        }
        return criteria.list();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return null;
    }
           
}
