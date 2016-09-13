/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Venta;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class VentaDaoImp implements VentaDao{

    @Override
    public boolean insertar(Session session, Venta tventa) throws Exception {
        session.save(tventa);
        return true;
    }

    @Override
    public Venta getUltimoRegistro(Session session) throws Exception {
        String hql = "from Venta order by idventa desc";
        Query query = session.createQuery(hql).setMaxResults(1);        
        return (Venta) query.uniqueResult();
    }
    
}
