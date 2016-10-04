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
    public List<Tipodocument> verTodo(Session session) throws Exception {
        String hql = "FROM Tipodocument";
        Query query = session.createQuery(hql);
        List<Tipodocument> lista = (List<Tipodocument>) query.list();
        return lista;
    }
    
}