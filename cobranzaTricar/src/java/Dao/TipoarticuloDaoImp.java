/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tipoarticulo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class TipoarticuloDaoImp implements TipoarticuloDao{    

    @Override
    public boolean registrar(Session session, Tipoarticulo tipoarticulo) throws Exception {
        session.save(tipoarticulo);
        return true;
    }

    @Override
    public List<Tipoarticulo> verTodo(Session session) throws Exception {
        String hql = "FROM Tipoarticulo";
        Query query = session.createQuery(hql);

        List<Tipoarticulo> lista = (List<Tipoarticulo>) query.list();

        return lista;
    }

    @Override
    public Tipoarticulo verByCodigo(Session session, Integer idtipoarticulo) throws Exception {
        return (Tipoarticulo) session.get(Tipoarticulo.class, idtipoarticulo);
    }

    @Override
    public Tipoarticulo verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Tipoarticulo WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Tipoarticulo colo = (Tipoarticulo) query.uniqueResult();
        return colo;
    }

    @Override
    public Tipoarticulo verByDescripcionDifer(Session session, Integer idtipoarticulo, String descripcion) throws Exception {
        String hql = "FROM Tipoarticulo WHERE idtipoarticulo!=:idtipoarticulo and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idtipoarticulo", idtipoarticulo);
        query.setParameter("descripcion", descripcion);        
        Tipoarticulo colo = (Tipoarticulo) query.uniqueResult();
        return colo;
    }

    @Override
    public boolean modificar(Session session, Tipoarticulo tipoarticulo) throws Exception {
        session.update(tipoarticulo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Tipoarticulo tipoarticulo) throws Exception {
        session.delete(tipoarticulo);
        return true;
    }
    
}