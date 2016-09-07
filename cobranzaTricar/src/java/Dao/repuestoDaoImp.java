/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Repuesto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class repuestoDaoImp implements repuestoDao{    

    @Override
    public boolean registrar(Session session, Repuesto repuesto) throws Exception {
        session.save(repuesto);
        return true;
    }

    @Override
    public List<Repuesto> verTodo(Session session) throws Exception {
        String hql = "FROM Repuesto";
        Query query = session.createQuery(hql);

        List<Repuesto> lista = (List<Repuesto>) query.list();

        return lista;
    }

    @Override
    public Repuesto verByCodigo(Session session, Integer idrepuesto) throws Exception {
        return (Repuesto) session.get(Repuesto.class, idrepuesto);
    }

    @Override
    public Repuesto verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Repuesto WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Repuesto repuesto = (Repuesto) query.uniqueResult();
        return repuesto;
    }

    @Override
    public Repuesto verByDescripcionDifer(Session session, Integer idrepuesto, String descripcion) throws Exception {
        String hql = "FROM Repuesto WHERE idrepuesto!=:idrepuesto and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idrepuesto", idrepuesto);
        query.setParameter("descripcion", descripcion);        
        Repuesto repuesto = (Repuesto) query.uniqueResult();
        return repuesto;
    }

    @Override
    public boolean modificar(Session session, Repuesto repuesto) throws Exception {
        session.update(repuesto);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Repuesto repuesto) throws Exception {
        session.delete(repuesto);
        return true;
    }
    
}