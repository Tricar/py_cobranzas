package Dao;

import Model.Tipodoc;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TipodocDaoImp implements TipodocDao {

    @Override
    public List<Tipodoc> mostrarTipoDocs() {
        Session session = null;
        List<Tipodoc> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Tipodoc");
            lista = (List<Tipodoc>) query.list();
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
    public List<Tipodoc> mostrarxTipo(String tipodoc, String tipos) {
        Session session = null;
        List<Tipodoc> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Tipodoc WHERE tipodoc=:tipo or tipodoc=:tipos");
            query.setParameter("tipo", tipodoc);
            query.setParameter("tipos", tipos);
            lista = (List<Tipodoc>) query.list();
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
    public void insertarTipodoc(Tipodoc tipodoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarTipodoc(Tipodoc tipodoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTipodoc(Tipodoc tipodoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
}
