/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tipodocument;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TipodocumentDaoImpl implements TipodocumentDao{    

    @Override
    public boolean registrar(Session session, Tipodocument tipoventa) throws Exception {
        session.save(tipoventa);
        return true;
    }

    @Override
    public List<Tipodocument> verTodo(Session session) throws Exception {
        String hql = "FROM Tipodocument";
        Query query = session.createQuery(hql);
        List<Tipodocument> lista = (List<Tipodocument>) query.list();
        return lista;
    }

    @Override
    public Tipodocument verByCodigo(Session session, Integer idtipoventa) throws Exception {
        return (Tipodocument) session.get(Tipodocument.class, idtipoventa);
    }

    @Override
    public Tipodocument verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Tipodocument WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Tipodocument tipoventa = (Tipodocument) query.uniqueResult();
        return tipoventa;
    }

    @Override
    public Tipodocument verByColorDifer(Session session, Integer idtipoventa, String descripcion) throws Exception {
        String hql = "FROM Tipodocument WHERE idttipodocument!=:idtipoventa and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idtipoventa", idtipoventa);
        query.setParameter("descripcion", descripcion);        
        Tipodocument tipoventa = (Tipodocument) query.uniqueResult();
        return tipoventa;
    }

    @Override
    public boolean modificar(Session session, Tipodocument tipoventa) throws Exception {
        session.update(tipoventa);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Tipodocument tipoventa) throws Exception {
        session.delete(tipoventa);
        return true;
    }
    
}