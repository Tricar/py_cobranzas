/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfil;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class PerfilDaoImpl implements PerfilDao{
    
    @Override
    public void actualizar(Perfil perfil) {
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
            session.merge(perfil);
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Error en actualizar perfil " + e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            if (session != null) {
                
                session.close();
                
            }
        }
    }

    @Override
    public Perfil buscarPorId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Perfil) session.load(Perfil.class, id);
    }

    @Override
    public List<Perfil> buscarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Perfil").list();
    }

    @Override
    public void crearperfil(Perfil perfil) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(perfil);
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
