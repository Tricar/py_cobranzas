/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class OperacionDaoImp implements OperacionDao{

    @Override
    public boolean insertar(Session session, Operacion operacion) throws Exception {
        session.save(operacion);
        return true;
    }

    @Override
    public Operacion getUltimoRegistro(Session session) throws Exception {
        String hql = "from Operacion order by idoperacion desc";
        Query query = session.createQuery(hql).setMaxResults(1);        
        return (Operacion) query.uniqueResult();
    }
    
}
