/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operaciondetalle;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class OperaciondetalleDaoImp implements OperaciondetalleDao{

    @Override
    public boolean insertar(Session session, Operaciondetalle operaciondetalle) throws Exception {
        session.save(operaciondetalle);
        return true;
    }

    @Override
    public List<Operaciondetalle> getAll(Session session) throws Exception {
        return session.createCriteria(Operaciondetalle.class).list();
    }
    
}
