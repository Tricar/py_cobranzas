package Dao;

import Model.Caja;
import Model.Movcaja;
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
public class MovcajaDaoImp implements MovcajaDao {

    @Override
    public List<Movcaja> mostrarMovcajas() {
        Session session = null;
        List<Movcaja> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Movcaja");
            lista = (List<Movcaja>) query.list();
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
    public void insertarMovcaja(Movcaja movcaja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(movcaja);
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
    public void modificarMovcaja(Movcaja movcaja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(movcaja);
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
    public void eliminarMovcaja(Movcaja movcaja) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(movcaja);
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
    public Movcaja getbyId(Integer idmovcaja) {
        Session session = null;
        Movcaja caja = new Movcaja();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Movcaja WHERE idmovcaja=:id");
            query.setParameter("id", idmovcaja);
            caja = (Movcaja) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return caja;
    }

    @Override
    public Movcaja devolverxIdcajaIdconceptos(Integer idcaja, Integer idconceptos) {
        Session session = null;
        Movcaja caja = new Movcaja();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Movcaja WHERE idcaja=:id and anticipo=:id2");
            query.setParameter("id", idcaja);
            query.setParameter("id2", idconceptos);
            caja = (Movcaja) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return caja;
    }

    @Override
    public Movcaja devolverCajaPagos(Caja caja, Pagos pago) {
        Session session = null;
        Movcaja movcaja = new Movcaja();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Movcaja where idcaja=:caja and origen=:pago");
            query.setParameter("caja", caja.getIdcaja());
            query.setParameter("pago", pago.getIdpagos());
            movcaja = (Movcaja) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movcaja;
    }

}
