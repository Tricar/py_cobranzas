/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tipocambio;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TipocambioDaoImp implements TipocambioDao{    

    @Override
    public boolean registrar(Session session, Tipocambio tipoc) throws Exception {
        session.save(tipoc);
        return true;
    }

    @Override
    public List<Tipocambio> verTodo(Session session) throws Exception {
        String hql = "FROM Tipocambio";
        Query query = session.createQuery(hql);
        List<Tipocambio> lista = (List<Tipocambio>) query.list();
        return lista;
    } 

    @Override
    public boolean modificar(Session session, Tipocambio tipoc) throws Exception {
        session.update(tipoc);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Tipocambio tipoc) throws Exception {
        session.delete(tipoc);
        return true;
    }
    
}