/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Familia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class FamiliaDaoImplements implements FamiliaDao{    

    @Override
    public boolean registrar(Session session, Familia familia) throws Exception {
        session.save(familia);
        return true;
    }

    @Override
    public List<Familia> verTodo(Session session) throws Exception {
        String hql = "FROM Familia";
        Query query = session.createQuery(hql);
        List<Familia> lista = (List<Familia>) query.list();
        return lista;
    }

    @Override
    public Familia verByCodigo(Session session, Integer idfamilia) throws Exception {
        return (Familia) session.get(Familia.class, idfamilia);
    }

    @Override
    public Familia verByFamilia(Session session, String familia) throws Exception {
        String hql = "FROM Familia WHERE familia=:familia";
        Query query = session.createQuery(hql);
        query.setParameter("color", familia);
        Familia family = (Familia) query.uniqueResult();
        return family;
    }

    @Override
    public Familia verByFamiliaDifer(Session session, Integer idfamilia, String familia) throws Exception {
        String hql = "FROM Familia WHERE idfamilia!=:idfamilia and familia=:familia";
        Query query = session.createQuery(hql);
        query.setParameter("idfamilia", idfamilia);
        query.setParameter("familia", familia);        
        Familia family = (Familia) query.uniqueResult();
        return family;
    }

    @Override
    public boolean modificar(Session session, Familia familia) throws Exception {
        session.update(familia);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Familia familia) throws Exception {
        session.delete(familia);
        return true;
    }
    
}