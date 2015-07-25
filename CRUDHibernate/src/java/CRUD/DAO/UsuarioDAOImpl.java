/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD.DAO;

import CRUD.Entidad.Usuario;
import HibernateUtil.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Sistemas2
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Usuario where usuario = "+ usuario.getUsuario() +"";
        try {
            sesion.beginTransaction();
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }
        return model;
    }

    @Override
    public Usuario Login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario);
        if (model != null) {
            if (usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
        return model;
    }
    
}
