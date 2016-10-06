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
public class PerfilmenuDaoImpl implements PerfilmenuDao {

    @Override
    public Perfilmenu verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Perfilmenu) session.get(Perfilmenu.class, codigoUsuario);
    }

    @Override
    public void insertar(Perfilmenu perfilmenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(perfilmenu);
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

    @Override
    public List<Perfilmenu> verTodo(Session session) throws Exception {
        String hql = "FROM Perfilmenu";
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
        String hql = "FROM Perfilmenu WHERE descripcion=:descripcion";
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
        String hql = "FROM Perfilmenu WHERE idperfil!=:idperfil and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idperfil", idperfil);
        query.setParameter("descripcion", descripcion);

        Perfilmenu perfil = (Perfilmenu) query.uniqueResult();

        return perfil;
    }

    @Override
    public List<Perfilmenu> verTodoByPerfilmenu(Session session, Integer menu, Boolean estado) throws Exception {
        String hql = "from Perfilmenu where idperfil=:perfil AND estado=:v";
        Query query = session.createQuery(hql);
        query.setParameter("perfil", menu);
        query.setParameter("v", estado);

        List<Perfilmenu> listaPerfil = (List<Perfilmenu>) query.list();

        return listaPerfil;
    }

    @Override
    public Perfilmenu verTodos(Perfil perfil, Menu menu) {
        Session session = null;
        Perfilmenu perfilmenu = new Perfilmenu();
        try {
            Integer idperfil = perfil.getIdperfil();
            Integer idmenu = menu.getIdmenu();
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilmenu where idperfil=:perfil and idmenu=:menu");
            query.setParameter("perfil", idperfil);
            query.setParameter("menu", idmenu);
            perfilmenu = (Perfilmenu) query.uniqueResult();
            System.out.println("idperfil: " + idperfil);
            System.out.println("idmenu: " + idmenu);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return perfilmenu;
    }

    @Override
    public List<Perfilmenu> verTodoByPerfil(int idperfil) {
        Session session = null;
        //int idperfil = perfil.getIdperfil();
        List<Perfilmenu> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilmenu where idperfil=:u");
            query.setParameter("u", idperfil);
            lista = (List<Perfilmenu>) query.list();
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
    public void modificarSolo(Perfilmenu perfmenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(perfmenu);
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
