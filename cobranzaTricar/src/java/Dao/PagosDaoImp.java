package Dao;

import Model.Credito;
import Model.Letras;
import Model.Pagos;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class PagosDaoImp implements PagosDao {

    @Override
    public List<Pagos> mostrarPagos() {
        Session session = null;
        List<Pagos> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Pagos");
            lista = (List<Pagos>) query.list();
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
    public List<Pagos> mostrarPagosxCredito(Credito credito) {
        Session session = null;
        List<Pagos> lista = null;
        Integer idcred = credito.getIdventa();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Pagos where letras.credito.idventa=:v order by fecreg desc, descripcion desc");
            query.setParameter("v", idcred);
            lista = (List<Pagos>) query.list();
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
    public List<Pagos> mostrarPagosxLetras(Letras letra) {
        Session session = null;
        List<Pagos> lista = null;
        Integer idletra = letra.getIdletras();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Pagos WHERE idletras=:v");
            query.setParameter("v", idletra);
            lista = (List<Pagos>) query.list();
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
    public void insertarPago(Pagos pago) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pago);
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
    public void modificarPago(Pagos pagos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(pagos);
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
    public void eliminarPago(Pagos pago) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(pago);
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
}
