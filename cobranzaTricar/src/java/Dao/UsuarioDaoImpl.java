package Dao;

import Model.Usuario;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario buscarPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "select u from Usuario u where usuario=:user and clave=:pass";
        Query query = session.createQuery(sql);
        query.setString("user", usuario.getUsuario());
        query.setString("pass", usuario.getClave());
        return (Usuario) query.uniqueResult();
    }

    @Override
    public List<Usuario> buscarTodos() {
         Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Usuario").list();
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
