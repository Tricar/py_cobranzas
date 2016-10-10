/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import Persistencia.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class OperacionDaoImp implements OperacionDao {

    @Override
    public boolean insertar(Session session, Operacion operacion) throws Exception {
        session.save(operacion);
        return true;
    }

    @Override
    public Operacion getUltimoRegistro(Session session) throws Exception {
        String hql = "from Operacion order by idoperacion desc";
        Query query = session.createQuery(hql).setMaxResults(1);
        return (Operacion) query.uniqueResult();
    }

    @Override
    public List<Operacion> filtrarFechas(Date date1, Date date2, Integer tipo) {
        Session session = null;
        List<Operacion> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Operacion WHERE created BETWEEN :start_date AND :end_date and idtipooperacioncontable=:tipo and (estado=:estado1 or estado=:estado2)");
            query.setParameter("start_date", date1);
            query.setParameter("end_date", date2);
            query.setParameter("tipo", tipo);
            query.setParameter("estado1", 1);
            query.setParameter("estado2", 0);
            lista = (List<Operacion>) query.list();
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
    public Operacion verByCodigo(Session session, Integer codigo) throws Exception {
        return (Operacion) session.get(Operacion.class, codigo);
    }

    @Override
    public boolean eliminar(Session session, Operacion perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public boolean modificar(Session session, Operacion articulo) throws Exception {
        session.update(articulo);
        return true;
    }

    @Override
    public Operacion verByCodigos(Integer idoperacion) {
        Session session = null;
        Operacion operacion = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Operacion WHERE idoperacion=:u");
            query.setParameter("u", idoperacion);            
            operacion = (Operacion) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return operacion;
    }

    @Override
    public boolean modificarOD(Operacion operacion) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "UPDATE Operacion set montototal = :montototal, estado = :estado WHERE idoperacion = :idoperacion";
            Query query = session.createQuery(hql);
            query.setParameter("montototal", operacion.getMontototal());
            query.setParameter("estado", operacion.getEstado());
            query.setParameter("idoperacion", operacion.getIdoperacion());
            query.executeUpdate();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public List<Operacion> cargarxEstado(Integer estado) {
        Session session = null;
        List<Operacion> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Operacion WHERE estado=:v");
            query.setParameter("v", estado);
            lista = query.list();
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
    public Operacion verByCodigoVenta(String codigo) {
        Session session = null;
        Operacion operacion = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Operacion WHERE codigo=:codigo");
            query.setParameter("codigo", codigo);            
            operacion = (Operacion) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return operacion;
    }

    @Override
    public List<Operacion> filtrarFechasTipo(Date date1, Date date2) {
        Session session = null;
        List<Operacion> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Operacion WHERE created BETWEEN :start_date AND :end_date");
            query.setParameter("start_date", date1);
            query.setParameter("end_date", date2);
            lista = (List<Operacion>) query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }

}
