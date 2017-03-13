/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Subfamilia;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class SubfamiliaDaoImplements implements SubfamiliaDao{    

    @Override
    public boolean registrar(Session session, Subfamilia colo) throws Exception {
        session.save(colo);
        return true;
    }

    @Override
    public List<Subfamilia> verTodo(Session session) throws Exception {
        String hql = "FROM Subfamilia";
        Query query = session.createQuery(hql);

        List<Subfamilia> lista = (List<Subfamilia>) query.list();

        return lista;
    }

    @Override
    public Subfamilia verByCodigo(Session session, Integer idcolor) throws Exception {
        return (Subfamilia) session.get(Subfamilia.class, idcolor);
    }

    @Override
    public Subfamilia verBySubfamilia(Session session, String color) throws Exception {
        String hql = "FROM Subfamilia WHERE color=:color";
        Query query = session.createQuery(hql);
        query.setParameter("color", color);
        Subfamilia colo = (Subfamilia) query.uniqueResult();
        return colo;
    }

    @Override
    public Subfamilia verBySubfamiliaD(Session session, Integer idcolor, String color) throws Exception {
        String hql = "FROM Subfamilia WHERE idcolor!=:idcolor and color=:color";
        Query query = session.createQuery(hql);
        query.setParameter("idcolor", idcolor);
        query.setParameter("color", color);        
        Subfamilia colo = (Subfamilia) query.uniqueResult();
        return colo;
    }

    @Override
    public boolean modificar(Session session, Subfamilia colo) throws Exception {
        session.update(colo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Subfamilia colo) throws Exception {
        session.delete(colo);
        return true;
    }

    @Override
    public List<Subfamilia> filtarTipoDos() {
        Session session = null;
        List<Subfamilia> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Subfamilia");           
            lista = (List<Subfamilia>) query.list();
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