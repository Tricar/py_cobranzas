package Dao;

import Model.Anexo;
import Model.Credito;
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
public class AnexoDaoImplements implements AnexoDao {

    @Override
    public List<Anexo> mostrarAnexo() {
        Session session = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo");
            lista = (List<Anexo>) query.list();
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
    public void insertarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(anexo);
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
    public void modificarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(anexo);
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
    public void eliminarAnexo(Anexo anexo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(anexo);
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
    public List<Anexo> filtarTipo(String tipo) {
        Session session = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE tipoanexo=:u");
            query.setParameter("u", tipo);
            lista = (List<Anexo>) query.list();
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
    public List<Anexo> buscarxNombre(String nombre) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Anexo.class);
            if (StringUtils.isNotBlank(nombre)) {
                criteria.add(Restrictions.ilike("nombre", nombre.toUpperCase(), MatchMode.START));
            }
            return criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Anexo> filtarTipoDos(String tipo, String tipo1, String tipo2) {
        Session session = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE tipoanexo=:u OR tipoanexo=:v OR tipoanexo=:w");
            query.setParameter("u", tipo);
            query.setParameter("v", tipo1);
            query.setParameter("w", tipo2);
            lista = (List<Anexo>) query.list();
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
    public List<Anexo> buscarCliente(String nombre, String tipo) {
        Session session = null;
//        Anexo anexo = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE tipoanexo=:t and nombre LIKE :n");
            query.setParameter("t", tipo);
            query.setParameter("n", nombre+"%");
            lista = (List<Anexo>) query.list();
//            anexo = (Anexo)query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
//        return anexo;
        return lista;
    }

    @Override
    public Anexo cargarxCredito(Integer idanexo) {
        Session session = null;
        Anexo anexito = new Anexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE idanexo=:w");
            query.setParameter("w", idanexo);
            anexito = (Anexo) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return anexito;
    }
}
