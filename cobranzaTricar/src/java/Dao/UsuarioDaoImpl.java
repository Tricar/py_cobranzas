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
    public List<Usuario> buscarTodos() {
        List<Usuario> listado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        listado = session.createQuery("from Usuario").list();
        return listado;
    }

    @Override
    public boolean registrar(Usuario usuario) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(usuario);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean modificar(Usuario usuario) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(usuario);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean eliminar(Integer id) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            session.delete(usuario);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }
        return flag;
    }
}
