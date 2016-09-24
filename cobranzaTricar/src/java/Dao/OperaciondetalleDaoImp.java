/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import Model.Operaciondetalle;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

    @Override
    public List<Operaciondetalle> mostrarSoloDetallexCompra(Operacion compra) {
        Session session = null;
        List<Operaciondetalle> lista = null;       
        Integer idcred = compra.getIdoperacion();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Operaciondetalle where idoperacion=:v ");
            query.setParameter("v", idcred);
            lista = (List<Operaciondetalle>)query.list();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (session != null){
                session.close();
            }
        }
        return lista;
    }

    @Override
    public boolean eliminar(Session session, Operaciondetalle perfil) throws Exception {
        session.delete(perfil);
        return true;
    }
    
    @Override
    public Operaciondetalle verByCodigo(Session session, Integer codigo) throws Exception {
        return (Operaciondetalle) session.get(Operaciondetalle.class, codigo);
    }
    
}
