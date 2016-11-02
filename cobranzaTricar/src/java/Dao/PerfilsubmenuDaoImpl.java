/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfilmenu;
import Model.Perfilsubmenu;
import Model.Submenu;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
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
    public void insertar(Perfilsubmenu perfsubmenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(perfsubmenu);
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
    public List<Perfilsubmenu> verTodo(Session session) throws Exception {
        String hql = "FROM Perfilsubmenu";
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
    public void modificarSolo(Perfilsubmenu perfsubmenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(perfsubmenu);
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
    
    @Override
    public List<Perfilsubmenu> submenus(int idperfil) {
        Session session = null;
        //int idperfil = perfil.getIdperfil();
        List<Perfilsubmenu> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilsubmenu where perfilmenu.perfil.idperfil=:u");
            query.setParameter("u", idperfil);
            lista = (List<Perfilsubmenu>)query.list();
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
    public List<Perfilsubmenu> verPorMenu(int idperfilmenu) {
        Session session = null;
        //int idperfil = perfil.getIdperfil();
        List<Perfilsubmenu> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilsubmenu where idperfilmenu=:u");
            query.setParameter("u", idperfilmenu);
            lista = (List<Perfilsubmenu>)query.list();
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
    public List<Perfilsubmenu> verSubMenu(int idsubmenu) {
        Session session = null;
        List<Perfilsubmenu> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilsubmenu where idsubmenu=:u");
            query.setParameter("u", idsubmenu);
            lista = (List<Perfilsubmenu>)query.list();
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
    public Perfilsubmenu verTodos(Perfilmenu perfilmenu, Submenu submenu) {
        Session session = null;
        Perfilsubmenu perfilsubmenu = new Perfilsubmenu();
        try {
            Integer idperfilmenu = perfilmenu.getIdperfilmenu();
            Integer idsubmenu = submenu.getIdsubmenu();
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Perfilsubmenu where idperfilmenu=:idperfilmenu and idsubmenu=:idsubmenu");
            query.setParameter("idperfilmenu", idperfilmenu);
            query.setParameter("idsubmenu", idsubmenu);
            perfilsubmenu = (Perfilsubmenu) query.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return perfilsubmenu;
    }

}
