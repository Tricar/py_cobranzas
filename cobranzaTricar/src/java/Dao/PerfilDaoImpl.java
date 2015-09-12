/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public class PerfilDaoImpl implements PerfilDao {

    @Override
    public Perfil verByCodigo(Session session, Integer codigoUsuario) throws Exception {
        return (Perfil) session.get(Perfil.class, codigoUsuario);
    }

    @Override
    public boolean registrar(Session session, Perfil perfil) throws Exception {
        session.save(perfil);
        return true;
    }

    @Override
    public List<Perfil> verTodo(Session session) throws Exception {
        String hql = "FROM Perfil";
        Query query = session.createQuery(hql);

        List<Perfil> listaPerfil = (List<Perfil>) query.list();

        return listaPerfil;
    }

    @Override
    public boolean modificar(Session session, Perfil perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public Perfil verByDescripcion(Session session, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("descripcion", descripcion);

        Perfil perfil = (Perfil) query.uniqueResult();

        return perfil;
    }

    @Override
    public boolean eliminarPerfil(Session session, Perfil perfil) throws Exception {
        session.delete(perfil);
        return true;
    }

    @Override
    public Perfil verByPerfilDifer(Session session, Integer idperfil, String descripcion) throws Exception {
        String hql = "FROM Perfil WHERE idperfil!=:idperfil and descripcion=:descripcion";
        Query query = session.createQuery(hql);
        query.setParameter("idperfil", idperfil);
        query.setParameter("descripcion", descripcion);
        
        Perfil perfil = (Perfil) query.uniqueResult();
        
        return perfil;
    }

}
