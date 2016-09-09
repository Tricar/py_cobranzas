/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Articulo;
import Model.Color;
import Persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author master
 */
public class ArticuloDaoImp implements ArticuloDao{    

    @Override
    public boolean registrar(Session session, Articulo articulo) throws Exception {
        session.save(articulo);
        return true;
    }

    @Override
    public List<Articulo> verTodo(Session session) throws Exception {
        String hql = "FROM Articulo";
        Query query = session.createQuery(hql);

        List<Articulo> lista = (List<Articulo>) query.list();

        return lista;
    }

    @Override
    public Articulo verByCodigo(Session session, Integer idarticulo) throws Exception {
        return (Articulo) session.get(Articulo.class, idarticulo);
    }

    @Override
    public Articulo verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Articulo WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);
        Articulo repuesto = (Articulo) query.uniqueResult();
        return repuesto;
    }

    @Override
    public Articulo verByDescripcionDifer(Session session, Integer idarticulo, String descripcion) throws Exception {
        String hql = "FROM Articulo WHERE idarticulo!=:idarticulo and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idarticulo", idarticulo);
        query.setParameter("descripcion", descripcion);        
        Articulo repuesto = (Articulo) query.uniqueResult();
        return repuesto;
    }

    @Override
    public boolean modificar(Session session, Articulo articulo) throws Exception {
        session.update(articulo);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Articulo articulo) throws Exception {
        session.delete(articulo);
        return true;
    }

    @Override
    public List<Color> filtarTipoDos() {
        Session session = null;
        List<Color> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Color");           
            lista = (List<Color>) query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }
    
}