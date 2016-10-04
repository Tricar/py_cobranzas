/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Color;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class ColorDaoImplements implements ColorDao{    

    @Override
    public boolean registrar(Session session, Color colo) throws Exception {
        session.save(colo);
        return true;
    }

    @Override
    public List<Color> verTodo(Session session) throws Exception {
        String hql = "FROM Color";
        Query query = session.createQuery(hql);

        List<Color> lista = (List<Color>) query.list();

        return lista;
    }

    @Override
    public Color verByCodigo(Session session, Integer idcolor) throws Exception {
        return (Color) session.get(Color.class, idcolor);
    }

    @Override
    public Color verByColor(Session session, String color) throws Exception {
        String hql = "FROM Color WHERE color=:color";
        Query query = session.createQuery(hql);
        query.setParameter("color", color);
        Color colo = (Color) query.uniqueResult();
        return colo;
    }

    @Override
    public Color verByColorDifer(Session session, Integer idcolor, String color) throws Exception {
        String hql = "FROM Color WHERE idcolor!=:idcolor and color=:color";
        Query query = session.createQuery(hql);
        query.setParameter("idcolor", idcolor);
        query.setParameter("color", color);        
        Color colo = (Color) query.uniqueResult();
        return colo;
    }

    @Override
    public boolean modificar(Session session, Color colo) throws Exception {
        session.update(colo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Color colo) throws Exception {
        session.delete(colo);
        return true;
    }

    @Override
    public List<Color> filtarTipoDos() {
        Session session = null;
        List<Color> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Color");           
            lista = (List<Color>) query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }
    
}