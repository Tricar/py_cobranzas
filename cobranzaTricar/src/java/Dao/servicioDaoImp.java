/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Servicio;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class servicioDaoImp implements servicioDao{    

    @Override
    public boolean registrar(Session session, Servicio servicio) throws Exception {
        session.save(servicio);
        return true;
    }

    @Override
    public List<Servicio> verTodo(Session session) throws Exception {
        String hql = "FROM Servicio";
        Query query = session.createQuery(hql);

        List<Servicio> lista = (List<Servicio>) query.list();

        return lista;
    }

    @Override
    public Servicio verByCodigo(Session session, Integer idservicio) throws Exception {
        return (Servicio) session.get(Servicio.class, idservicio);
    }

    @Override
    public Servicio verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Servicio WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Servicio colo = (Servicio) query.uniqueResult();
        return colo;
    }

    @Override
    public Servicio verByDescripcionDifer(Session session, Integer idservicio, String descripcion) throws Exception {
        String hql = "FROM Servicio WHERE idservicio!=:idservicio and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idservicio", idservicio);
        query.setParameter("descripcion", descripcion);        
        Servicio colo = (Servicio) query.uniqueResult();
        return colo;
    }

    @Override
    public boolean modificar(Session session, Servicio servicio) throws Exception {
        session.update(servicio);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Servicio servicio) throws Exception {
        session.delete(servicio);
        return true;
    }
    
}