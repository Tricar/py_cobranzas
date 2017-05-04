package Dao;

import Model.Anexo;
import Model.Credito;
import Model.Conceptos;
import Persistencia.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class ConceptosDaoImp implements ConceptosDao{

    @Override
    public List<Conceptos> mostrarConceptos() {
        Session session = null;
        List<Conceptos> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Conceptos");
            lista = (List<Conceptos>)query.list();
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
    public void insertarConcepto(Conceptos conceptos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(conceptos);
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
    public void modificarConcepto(Conceptos conceptos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(conceptos);
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
    public void eliminarConcepto(Conceptos conceptos) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(conceptos);
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
    public List<Conceptos> mostrarConceptosXCred(Credito credito) {
        Session session = null;
        List<Conceptos> lista = null;
        Credito cred = new Credito();
        cred = credito;
        Integer idcred = cred.getIdventa();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Conceptos where idventa=:v");
            query.setParameter("v", idcred);
            lista = (List<Conceptos>)query.list();
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
    public List<Conceptos> mostrarConceptosxAnexo(Anexo anexo) {
        Session session = null;
        List<Conceptos> lista = null;        
        Integer idanexo = anexo.getIdanexo();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Conceptos where idanexo=:v");            
            query.setParameter("v", idanexo);
            lista = (List<Conceptos>)query.list();
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
    public boolean registrar(Session session, Conceptos conceptos) throws Exception {
        session.save(conceptos);
        return true;
    }    

    @Override
    public Conceptos veryId(Integer idconcepto) {
        Session session = null;
        Conceptos concepto = new Conceptos();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Conceptos WHERE idconceptos=:id" );
            query.setParameter("id", idconcepto);
            concepto = (Conceptos) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return concepto;
    }

    @Override
    public Conceptos veryIdCredito(Credito credito) {
        Session session = null;
        Conceptos concepto = new Conceptos();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Conceptos WHERE idventa=:id" );
            query.setParameter("id", credito);
            concepto = (Conceptos) query.uniqueResult();            
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return concepto;
    }

    @Override
    public List<Conceptos> filtrarxFechas(Date f1, Date f2, String tipo, Boolean cobrado) {
        Session session = null;
        List<Conceptos> lista = null;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(f1);
//        cal.add(Calendar.DAY_OF_YEAR, -1);
//        f1 = cal.getTime();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Conceptos where fecreg BETWEEN :f1 and :f2 and tipo=:tipo and cobrado=:cobrado");            
            query.setParameter("f1", f1);
            query.setParameter("f2", f2);
            query.setParameter("tipo", tipo);
            query.setParameter("cobrado", cobrado);
            lista = query.list();
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
    public List<Conceptos> mostrarPendXAnexos(Anexo anexo, Boolean cobrado) {
        Session session = null;
        List<Conceptos> lista = null;        
        Integer idanexo = anexo.getIdanexo();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Conceptos where idanexo=:v and cobrado=:u");
            query.setParameter("v", idanexo);
            query.setParameter("u", cobrado);
            lista = (List<Conceptos>)query.list();
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
    public Conceptos mostrarPendXCredito(Credito credito, Boolean cobrado) {
        Session session = null;
        Conceptos concepto = new Conceptos();        
        Integer idcredito = credito.getIdventa();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Conceptos where idventa=:v and cobrado=:u");
            query.setParameter("v", idcredito);
            query.setParameter("u", cobrado);
            concepto = (Conceptos)query.uniqueResult();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return concepto;
    }

   
        
}
