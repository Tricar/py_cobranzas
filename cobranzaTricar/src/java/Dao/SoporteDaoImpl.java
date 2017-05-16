package Dao;

import Model.Soporte;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dajoh
 */
public class SoporteDaoImpl implements SoporteDao {

    @Override
    public boolean registrar(Session session, Soporte soporte) throws Exception {
        session.save(soporte);
        return true;
    }

    @Override
    public List<Soporte> verTodo(Session session) throws Exception {
        String hql = "FROM Soporte";
        Query query = session.createQuery(hql);
        
        List<Soporte> listaTdistrito = (List<Soporte>)query.list();
        
        return listaTdistrito;
    }

    @Override
    public Soporte verByDistrito(Session session, String reporte) throws Exception {
        String hql = "FROM Soporte WHERE reporte=:reporte";
        Query query = session.createQuery(hql);
        query.setParameter("reporte", reporte);        
        Soporte tdistrito = (Soporte) query.uniqueResult();        
        return tdistrito;
    }

    @Override
    public Soporte verByDistritoDifer(Session session, Integer idsoporte, String reporte) throws Exception {
        String hql = "FROM Soporte WHERE idsoporte!=:idsoporte and reporte=:reporte";
        Query query = session.createQuery(hql);
        query.setParameter("idsoporte", idsoporte);
        query.setParameter("reporte", reporte);        
        Soporte tdistrito = (Soporte) query.uniqueResult();        
        return tdistrito;
    }

    @Override
    public boolean modificar(Session session, Soporte soporte) throws Exception {
        session.update(soporte);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Soporte soporte) throws Exception {
        session.delete(soporte);
        return true;
    }

    @Override
    public Soporte verById(Session session, Integer idsoporte) throws Exception {
        String hql = "FROM Soporte WHERE idsoporte=:idsoporte";
        Query query = session.createQuery(hql);
        query.setParameter("idsoporte", idsoporte);
        
        Soporte tdistrito = (Soporte) query.uniqueResult();
        
        return tdistrito;
    }
    
    @Override
    public List<Soporte> cargarxEstado(Integer estado) {
        Session session = null;
        List<Soporte> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Soporte WHERE estado=:v");
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
    public Soporte verByCodigos(Integer idoperacion) {
        Session session = null;
        Soporte operacion = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Soporte WHERE idsoporte=:idsoporte");
            query.setParameter("idsoporte", idoperacion);            
            operacion = (Soporte) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return operacion;
    }

}
