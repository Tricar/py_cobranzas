/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Articulo;
import java.util.List;
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
    public Articulo verByDescripcion(Session session, String descripcion1) throws Exception {
        String hql = "FROM Articulo WHERE descripcion1=:descripcion1";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion1", descripcion1);
        Articulo repuesto = (Articulo) query.uniqueResult();
        return repuesto;
    }

    @Override
    public Articulo verByDescripcionDifer(Session session, Integer idarticulo, String descripcion1) throws Exception {
        String hql = "FROM Articulo WHERE idarticulo!=:idarticulo and descripcion1=:descripcion1";
        Query query = session.createQuery(hql);
        query.setParameter("idarticulo", idarticulo);
        query.setParameter("descripcion1", descripcion1);   
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
    public Articulo getByIdProducto(Session session, Integer idArticulo) throws Exception {
        return (Articulo) session.load(Articulo.class, idArticulo);
    }

    @Override
    public Articulo getByCodigoBarras(Session session, String codigo) throws Exception {
        String hql = "from Articulo where codigo=:codigo";
        Query query = session.createQuery(hql);
        query.setParameter("codigo", codigo);
        return (Articulo) query.uniqueResult();
    }
    
}