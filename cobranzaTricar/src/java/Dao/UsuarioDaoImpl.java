package Dao;

import Model.Usuario;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public boolean registrar(Session session, Usuario usuario) throws Exception {
        Date d = new Date();
        usuario.setFechareg(d);
        session.save(usuario);
        return true;
    }

    @Override
    public List<Usuario> verTodo(Session session) throws Exception {
        String hql = "FROM Usuario";
        Query query = session.createQuery(hql);
        
        List<Usuario> listaTusuario = (List<Usuario>)query.list();
        
        return listaTusuario;
    }

    @Override
    public Usuario verByUsuario(Session session, String usuario) throws Exception {
        String hql = "FROM Usuario WHERE usuario=:usuario";
        Query query = session.createQuery(hql);
        query.setParameter("usuario", usuario);
        
        Usuario tusuario = (Usuario) query.uniqueResult();
        
        return tusuario;
    }

    @Override
    public Usuario verByUsuarioDifer(Session session, int idusuario, String usuario) throws Exception {
        String hql = "FROM Usuario WHERE idusuario!=:idusuario and usuario=:usuario";
        Query query = session.createQuery(hql);
        query.setParameter("idusuario", idusuario);
        query.setParameter("usuario", usuario);
        
        Usuario tusuario = (Usuario) query.uniqueResult();
        
        return tusuario;
    }

    @Override
    public boolean modificar(Session session, Usuario usuario) throws Exception {
        session.update(usuario);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Usuario usuario) throws Exception {
        session.delete(usuario);
        return true;
    }

    @Override
    public Usuario verByIdusuario(Session session, int idusuario) throws Exception {
        String hql = "FROM Usuario WHERE idusuario=:idusuario";
        Query query = session.createQuery(hql);
        query.setParameter("idusuario", idusuario);
        
        Usuario tusuario = (Usuario) query.uniqueResult();
        
        return tusuario;
    }

    
    
}