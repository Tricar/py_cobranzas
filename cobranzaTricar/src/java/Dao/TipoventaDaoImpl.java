/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tipoventa;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TipoventaDaoImpl implements TipoventaDao{    

    @Override
    public boolean registrar(Session session, Tipoventa tipoventa) throws Exception {
        session.save(tipoventa);
        return true;
    }

    @Override
    public List<Tipoventa> verTodo(Session session) throws Exception {
        String hql = "FROM Tipoventa";
        Query query = session.createQuery(hql);
        List<Tipoventa> lista = (List<Tipoventa>) query.list();
        return lista;
    }

    @Override
    public Tipoventa verByCodigo(Session session, Integer idtipoventa) throws Exception {
        return (Tipoventa) session.get(Tipoventa.class, idtipoventa);
    }

    @Override
    public Tipoventa verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Tipoventa WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Tipoventa tipoventa = (Tipoventa) query.uniqueResult();
        return tipoventa;
    }

    @Override
    public Tipoventa verByColorDifer(Session session, Integer idtipoventa, String descripcion) throws Exception {
        String hql = "FROM Tipoventa WHERE idtipoventa!=:idtipoventa and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idtipoventa", idtipoventa);
        query.setParameter("descripcion", descripcion);        
        Tipoventa tipoventa = (Tipoventa) query.uniqueResult();
        return tipoventa;
    }

    @Override
    public boolean modificar(Session session, Tipoventa tipoventa) throws Exception {
        session.update(tipoventa);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Tipoventa tipoventa) throws Exception {
        session.delete(tipoventa);
        return true;
    }

    @Override
    public List<Tipoventa> filtarTipoDos() {
        Session session = null;
        List<Tipoventa> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Tipoventa");           
            lista = (List<Tipoventa>) query.list();
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