package Dao;

import Model.Anexo;
import Persistencia.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class AnexoDaoImplements implements AnexoDao {

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
    public List<Anexo> filtarTipoTres(String tipo, String tipo1, String tipo2) {
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
    public List<Anexo> buscarClienteNombre(String nombre, String tipo, String tipo1) {
        Session session = null;
//        Anexo anexo = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE (tipoanexo=:t OR tipoanexo=:t1) and nombre LIKE :n ");
            query.setParameter("t", tipo);
            query.setParameter("t1", tipo1);
            query.setParameter("n", "%" + nombre + "%");

            //query.setParameter("m", "%"+nombre+"%");
            //query.setParameter("l", "%"+nombre+"%");
            lista = (List<Anexo>) query.list();
            System.out.println("lista: " + lista);
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
    public List<Anexo> buscarClienteDni(String dni, String tipo, String tipo1) {
        Session session = null;
//        Anexo anexo = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE (tipoanexo=:t OR tipoanexo=:t1) and numdocumento LIKE :n");
            query.setParameter("t", tipo);
            query.setParameter("t1", tipo1);
            query.setParameter("n", dni + "%");
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

    @Override
    public boolean registrar(Session session, Anexo anexo) throws Exception {
        Date d = new Date();
        anexo.setFechareg(d);
        session.save(anexo);
        return true;
    }

    @Override
    public List<Anexo> verTodo(Session session) throws Exception {
        String hql = "FROM Anexo";
        Query query = session.createQuery(hql);

        List<Anexo> listaanexo = (List<Anexo>) query.list();

        return listaanexo;
    }

    @Override
    public Anexo verByIdanexo(Session session, Integer idanexo) throws Exception {
        String hql = "FROM Anexo WHERE idanexo=:idanexo";
        Query query = session.createQuery(hql);
        query.setParameter("idanexo", idanexo);
        Anexo anexo = (Anexo) query.uniqueResult();
        return anexo;
    }

    @Override
    public Anexo verByDocumento(Session session, String dni) throws Exception {
        String hql = "FROM Anexo WHERE numdocumento=:documento";
        Query query = session.createQuery(hql);
        query.setParameter("documento", dni);

        Anexo anexo = (Anexo) query.uniqueResult();

        return anexo;
    }

    @Override
    public List<Anexo> ListaConDocumento(Session session, String dni) throws Exception {
        String hql = "FROM Anexo WHERE numdocumento=:documento";
        Query query = session.createQuery(hql);
        query.setParameter("documento", dni);

        List<Anexo> anexos = (List<Anexo>) query.list();

        return anexos;
    }

    @Override
    public Anexo verByDocumentoDifer(Session session, Integer idanexo, String dni) throws Exception {
        String hql = "FROM Anexo WHERE idanexo!=:idanexo and numdocumento=:documento";
        Query query = session.createQuery(hql);
        query.setParameter("idanexo", idanexo);
        query.setParameter("documento", dni);

        Anexo anexo = (Anexo) query.uniqueResult();

        return anexo;
    }

    @Override
    public boolean modificar(Session session, Anexo anexo) throws Exception {
        session.update(anexo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Anexo anexo) throws Exception {
        session.delete(anexo);
        return true;
    }

    @Override
    public List<Anexo> verCliente(Session session) throws Exception {
        String hql = "FROM Anexo WHERE tipoanexo IN ('CN','CJ')";
        Query query = session.createQuery(hql);

        List<Anexo> listaanexo = (List<Anexo>) query.list();

        return listaanexo;
    }

    @Override
    public List<Anexo> verProveedor(Session session) throws Exception {
        String hql = "FROM Anexo WHERE tipoanexo IN ('PO')";
        Query query = session.createQuery(hql);

        List<Anexo> listaanexo = (List<Anexo>) query.list();

        return listaanexo;
    }

    @Override
    public List<Anexo> verAval(Session session) throws Exception {
        String hql = "FROM Anexo WHERE tipoanexo IN ('AV')";
        Query query = session.createQuery(hql);

        List<Anexo> listaanexo = (List<Anexo>) query.list();

        return listaanexo;
    }

    @Override
    public List<Anexo> verEmpleado(Session session) throws Exception {
        String hql = "FROM Anexo WHERE tipoanexo IN ('VE','AD','GE', 'AS', 'JE')";
        Query query = session.createQuery(hql);
        List<Anexo> listaanexo = (List<Anexo>) query.list();
        return listaanexo;
    }

    @Override
    public List<Anexo> verVendedor(Session session) throws Exception {
        String hql = "FROM Anexo WHERE tipoanexo IN ('VE')";
        Query query = session.createQuery(hql);
        List<Anexo> listaanexo = (List<Anexo>) query.list();
        return listaanexo;
    }

    @Override
    public List<Anexo> buscarClientexDoc(String dni, String tipo, String tipo1) {
        Session session = null;
//        Anexo anexo = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE numdocumento=:n and tipoanexo=:t or tipoanexo=:v");
            query.setParameter("t", tipo);
            query.setParameter("v", tipo1);
            query.setParameter("n", dni);
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
    public List<Anexo> filtarTipoDos(String tipo, String tipo1) {
        Session session = null;
        List<Anexo> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE tipoanexo=:u OR tipoanexo=:v");
            query.setParameter("u", tipo);
            query.setParameter("v", tipo1);
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
    public Anexo cargarClientexDoc(String dni, String tipo, String tipo1) {
        Session session = null;
        Anexo anexo = new Anexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Anexo WHERE numdocumento=:n and (tipoanexo=:t or tipoanexo=:v)");
            query.setParameter("n", dni);
            query.setParameter("t", tipo);
            query.setParameter("v", tipo1);
            anexo = (Anexo) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return anexo;
    }

    @Override
    public Anexo devolverNombre(Integer idanexo) {
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

    @Override
    public void insertar(Anexo anexo) {
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
    public List<Anexo> verByTipoventa(Session session, String tipoventa) throws Exception {
        String hql = "FROM Anexo WHERE tipodocumento=:tipoventa";
        Query query = session.createQuery(hql);
        query.setParameter("tipoventa", tipoventa);
        List<Anexo> lista = (List<Anexo>) query.list();

        return lista;
    }
}
