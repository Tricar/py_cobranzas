package Dao;

import Model.Usuario;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario buscarPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "select u from Usuario u where usuario=:user";
        Query query = session.createQuery(sql);
        query.setString("user", usuario.getUsuario());
        query.setString("pass", usuario.getClave());
        return (Usuario) query.uniqueResult();
    }

    @Override
    public Usuario login(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "select u from Usuario u where usuario=:user and clave=:pass";
        Query query = session.createQuery(sql);
        query.setString("user", usuario.getUsuario());
        query.setString("pass", usuario.getClave());
        return (Usuario) query.uniqueResult();
    }

    @Override
    public List<Usuario> mostrarUsuario() {
        Session session = null;
        List<Usuario> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario");
            lista = (List<Usuario>)query.list();
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
    public void insertarUsuario(Usuario usuario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(usuario);
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
    public void modificarUsuario(Usuario usuario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
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
    public void eliminarUsuario(Usuario usuario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(usuario);
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
}