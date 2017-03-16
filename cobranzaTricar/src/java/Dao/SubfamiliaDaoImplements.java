/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Subfamilia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class SubfamiliaDaoImplements implements SubfamiliaDao{    

    @Override
    public boolean registrar(Session session, Subfamilia subfamilia) throws Exception {
        session.save(subfamilia);
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
    public Subfamilia verByCodigo(Session session, Integer idsubfamilia) throws Exception {
        return (Subfamilia) session.get(Subfamilia.class, idsubfamilia);
    }

    @Override
    public Subfamilia verBySubfamilia(Session session, String subfamilia) throws Exception {
        String hql = "FROM Subfamilia WHERE subfamilia=:subfamilia";
        Query query = session.createQuery(hql);
        query.setParameter("subfamilia", subfamilia);
        Subfamilia subfamily = (Subfamilia) query.uniqueResult();
        return subfamily;
    }

    @Override
    public Subfamilia verBySubfamiliaD(Session session, Integer idsubfamilia, String subfamilia) throws Exception {
        String hql = "FROM Subfamilia WHERE idsubfamilia!=:idsubfamilia and subfamilia=:subfamilia";
        Query query = session.createQuery(hql);
        query.setParameter("idsubfamilia", idsubfamilia);
        query.setParameter("subfamilia", subfamilia);        
        Subfamilia subfamily = (Subfamilia) query.uniqueResult();
        return subfamily;
    }

    @Override
    public boolean modificar(Session session, Subfamilia subfamilia) throws Exception {
        session.update(subfamilia);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Subfamilia subfamilia) throws Exception {
        session.delete(subfamilia);
        return true;
    }
    
}