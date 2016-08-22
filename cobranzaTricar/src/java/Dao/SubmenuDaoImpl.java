/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Submenu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class SubmenuDaoImpl implements SubmenuDao {

    @Override
    public Submenu verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Submenu) session.get(Submenu.class, codigoUsuario);
    }

    @Override
    public boolean registrar(Session session, Submenu perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Submenu> verTodo(Session session) throws Exception {
        String hql = "FROM Menu";
        Query query = session.createQuery(hql);

        List<Submenu> listaPerfil = (List<Submenu>) query.list();

        return listaPerfil;
    }

    @Override
    public boolean modificar(Session session, Submenu perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public Submenu verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);

        Submenu perfil = (Submenu) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminarPerfil(Session session, Submenu perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Submenu verByPerfilDifer(Session session, Integer idperfil, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE idperfil!=:idperfil and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idperfil", idperfil);
        query.setParameter("descripcion", descripcion);
        
        Submenu perfil = (Submenu) query.uniqueResult();
        
        return perfil;
    }

    @Override
    public List<Submenu> verTodoByMenu(Session session, Integer menu) throws Exception {
        String hql = "FROM Submenu WHERE idmenu=:menu";
        Query query = session.createQuery(hql);
        query.setParameter("menu", menu);

        List<Submenu> listaPerfil = (List<Submenu>) query.list();

        return listaPerfil;
    }

}
