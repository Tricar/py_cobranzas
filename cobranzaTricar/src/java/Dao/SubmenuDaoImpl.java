/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.*;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class SubmenuDaoImpl implements SubmenuDao {

    @Override
    public Submenu verByCodigo(Session session, Integer idsubmenu) throws Exception {
        return (Submenu) session.get(Submenu.class, idsubmenu);
    }

    @Override
    public boolean registrar(Session session, Submenu perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Submenu> verTodo(Session session) throws Exception {
        String hql = "FROM Submenu";
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
        String hql = "FROM Submenu WHERE submenu=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);

        Submenu perfil = (Submenu) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminar(Session session, Submenu perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Submenu verByDifer(Session session, Integer idperfil, String descripcion) throws Exception {
        String hql = "FROM Submenu WHERE idsubmenu!=:idsubmenu and submenu=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idsubmenu", idperfil);
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

    @Override
    public List<Submenu> verTodosxId(Menu menu) {
        Session session = null;
        List<Submenu> lista = null;
        Integer id = menu.getIdmenu();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Submenu where idmenu =:v");
            query.setParameter("v", id);
            lista = (List<Submenu>) query.list();
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
    public Submenu verByID(Session session, Integer idsubmenu) throws Exception {
        String hql = "FROM Submenu WHERE idsubmenu=:idsubmenu";
        Query query = session.createQuery(hql);
        query.setParameter("idsubmenu", idsubmenu);
        Submenu submenu = (Submenu) query.uniqueResult();
        return submenu;
    }

    @Override
    public void modificarSolo(Submenu submenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(submenu);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
