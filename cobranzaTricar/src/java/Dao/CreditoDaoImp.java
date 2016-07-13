package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Letras;
import Persistencia.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class CreditoDaoImp implements CreditoDao{

    @Override
    public List<Credito> mostrarVentas() {
        Session session = null;
        List<Credito> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito");
            lista = (List<Credito>)query.list();
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
    public void insertarVenta(Credito credito) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(credito);
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
    public void modificarVenta(Credito credito) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(credito);
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
    public void eliminarVenta(Credito credito) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(credito);
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
    public List<Credito> filtrarFechas(Date date1, Date date2, String estado) {
        Session session = null;
        List<Credito> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE fechareg BETWEEN :start_date AND :end_date and estado=:estado" );
            query.setParameter("start_date", date1);
            query.setParameter("end_date", date2);
            query.setParameter("estado",estado );
            lista = (List<Credito>)query.list();
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
    public Credito cargarCreditoxAnexo(Anexo anexo) {
        Session session = null;
        Credito credito = new Credito();
        Integer idanexo;
        idanexo = anexo.getIdanexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE idanexo=:w");
            query.setParameter("w", idanexo);
            credito = (Credito) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return credito;
    }    

    @Override
    public Credito cargarCreditoxLetra(Letras letra) {
        Session session = null;
        Credito credito = new Credito();
        Integer idcredito;
        idcredito = letra.getCredito().getIdventa();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE idventa=:w");
            query.setParameter("w", idcredito);
            credito = (Credito) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return credito;
    }

    @Override
    public List<Credito> filtrarDni(String dni) {
        Session session = null;
        List<Credito> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE " );
            query.setParameter("start_date", dni);            
            lista = (List<Credito>)query.list();
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
    public List<Credito> filtrarCreditoxAnexo(Anexo anexo) {
        Session session = null;
        List<Credito> lista = null;
        Integer idanexo;
        idanexo = anexo.getIdanexo();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE idanexo=:w");
            query.setParameter("w", idanexo);
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
    public Credito cargarxCodigoEstado(String codigo, String estado) {        
        Session session = null;
        Credito credito = new Credito();        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE liqventa=:w and estado=:v");
            query.setParameter("w", codigo);
            query.setParameter("v", estado);
            credito = (Credito) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return credito;
    }
    
    @Override
    public Credito cargarxCodigoEstadoDos(String codigo, String estado, String estado1) {
        Session session = null;
        Credito credito = new Credito();        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE liqventa=:w and (estado=:v or estado=:u)");
            query.setParameter("w", codigo);
            query.setParameter("v", estado);
            query.setParameter("u", estado1);
            credito = (Credito) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return credito;
    }

//    @Override
//    public List<Credito> cargarCreditoxNombre(String nombre) {
//        Session session = null;
//        List<Credito> lista = null;
//        try{
//            session = HibernateUtil.getSessionFactory().openSession();
//            Query query = session.createQuery("FROM Credito WHERE " );
//            query.setParameter("nombre",nombre);
//            lista = (List<Credito>)query.list();
//        }catch (HibernateException e){
//            System.out.println(e.getMessage());
//        }
//        finally{
//            if (session != null){
//                session.close();
//            }
//        }
//        return lista;
//    }

    @Override
    public boolean registrar(Session session, Credito credito) throws Exception {
        session.save(credito);
        return true;
    }

    @Override
    public Credito veryLiqventa(String liq) {
        Session session = null;
        Credito tcredito = new Credito();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE liqventa=:liq" );
            query.setParameter("liq", liq);
            tcredito = (Credito) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return tcredito;
    }
    
    @Override
    public Credito veryId(int idventa) {
        Session session = null;
        Credito tcredito = new Credito();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE idventa=:id" );
            query.setParameter("id", idventa);
            tcredito = (Credito) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return tcredito;
    }

    @Override
    public List<Credito> cargarxEstado(String estado) {
        Session session = null;
        List<Credito> lista = null;              
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE estado=:v");            
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
    public List<Credito> cargarTodosxCalif(String calif) {
        Session session = null;
        List<Credito> lista = null;              
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE calificacion=:v");            
            query.setParameter("v", calif);
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
    public Credito cargarxCodigoCalif(String codigo, String calif) {
        Session session = null;
        Credito credito = new Credito();        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE liqventa=:w and calificacion!=:v");
            query.setParameter("w", codigo);
            query.setParameter("v", calif);
            credito = (Credito) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return credito;
    }

    @Override
    public List<Credito> cargarxEstadoRef(String estado) {
        Session session = null;
        List<Credito> lista = null;              
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE estadoref=:v");            
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
    public List<Credito> cargarxRef(Boolean valor) {
        Session session = null;
        List<Credito> lista = null;              
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Credito WHERE refinanciado=:v");            
            query.setParameter("v", valor);
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
    
}