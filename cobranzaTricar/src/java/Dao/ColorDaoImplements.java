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
    public List<Color> mostrarColor() {
        Session session = null;
        List<Color> lista = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Color");
            lista = (List<Color>)query.list();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return lista;
    }

    @Override
    public void insertarColor(Color color) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(color);
            session.getTransaction().commit();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void modificarColor(Color color) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(color);
            session.getTransaction().commit();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void eliminarColor(Color color) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(color);
            session.getTransaction().commit();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
    
}