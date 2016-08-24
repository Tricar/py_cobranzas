/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfilmenu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class PerfilmenuDaoImpl implements PerfilmenuDao {

    @Override
    public Perfilmenu verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Perfilmenu) session.get(Perfilmenu.class, codigoUsuario);
    }

    @Override
    public boolean registrar(Session session, Perfilmenu perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Perfilmenu> verTodo(Session session) throws Exception {
        String hql = "FROM Menu";
        Query query = session.createQuery(hql);

        List<Perfilmenu> listaPerfil = (List<Perfilmenu>) query.list();

        return listaPerfil;
    }

    @Override
    public boolean modificar(Session session, Perfilmenu perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public Perfilmenu verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);

        Perfilmenu perfil = (Perfilmenu) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminarPerfil(Session session, Perfilmenu perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Perfilmenu verByPerfilDifer(Session session, Integer idperfil, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE idperfil!=:idperfil and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idperfil", idperfil);
        query.setParameter("descripcion", descripcion);
        
        Perfilmenu perfil = (Perfilmenu) query.uniqueResult();
        
        return perfil;
    }

    @Override
    public List<Perfilmenu> verTodoByPerfilmenu(Session session, Integer menu) throws Exception {
        String hql = "from Perfilmenu where idperfil=:perfil";
        Query query = session.createQuery(hql);
        query.setParameter("perfil", menu);

        List<Perfilmenu> listaPerfil = (List<Perfilmenu>) query.list();

        return listaPerfil;
    }

}
