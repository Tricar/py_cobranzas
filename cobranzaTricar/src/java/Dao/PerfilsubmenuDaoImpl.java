/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfilsubmenu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class PerfilsubmenuDaoImpl implements PerfilsubmenuDao {

    @Override
    public Perfilsubmenu verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Perfilsubmenu) session.get(Perfilsubmenu.class, codigoUsuario);
    }

    @Override
    public boolean registrar(Session session, Perfilsubmenu perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Perfilsubmenu> verTodo(Session session) throws Exception {
        String hql = "FROM Menu";
        Query query = session.createQuery(hql);

        List<Perfilsubmenu> listaPerfil = (List<Perfilsubmenu>) query.list();

        return listaPerfil;
    }

    @Override
    public boolean modificar(Session session, Perfilsubmenu perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public Perfilsubmenu verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);

        Perfilsubmenu perfil = (Perfilsubmenu) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminarPerfil(Session session, Perfilsubmenu perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Perfilsubmenu verByPerfilDifer(Session session, Integer idperfil, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE idperfil!=:idperfil and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idperfil", idperfil);
        query.setParameter("descripcion", descripcion);
        
        Perfilsubmenu perfil = (Perfilsubmenu) query.uniqueResult();
        
        return perfil;
    }

    @Override
    public List<Perfilsubmenu> verTodoByPerfilsubmenu(Session session, Integer menu) throws Exception {
        String hql = "from Perfilsubmenu where idperfilmenu=:menu";
        Query query = session.createQuery(hql);
        query.setParameter("menu", menu);

        List<Perfilsubmenu> listaPerfil = (List<Perfilsubmenu>) query.list();

        return listaPerfil;
    }

}
