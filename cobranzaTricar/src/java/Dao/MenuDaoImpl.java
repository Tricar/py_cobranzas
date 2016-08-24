/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Menu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class MenuDaoImpl implements MenuDao {

    @Override
    public Menu verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Menu) session.get(Menu.class, codigoUsuario);
    }

    @Override
    public boolean registrar(Session session, Menu perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Menu> verTodo(Session session) throws Exception {
        String hql = "FROM Menu";
        Query query = session.createQuery(hql);

        List<Menu> listaPerfil = (List<Menu>) query.list();

        return listaPerfil;
    }

    @Override
    public boolean modificar(Session session, Menu perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public Menu verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Menu WHERE menu=:menu";
        Query query = session.createQuery(hql);
        query.setParameter("menu", descripcion);

        Menu perfil = (Menu) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminar(Session session, Menu perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Menu verByDifer(Session session, Integer idmenu, String descripcion) throws Exception {
        String hql = "FROM Menu WHERE idmenu!=:idmenu and menu=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idmenu", idmenu);
        query.setParameter("descripcion", descripcion);
        
        Menu perfil = (Menu) query.uniqueResult();
        
        return perfil;
    }

}
