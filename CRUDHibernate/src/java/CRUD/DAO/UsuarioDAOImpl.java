/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD.DAO;

import CRUD.Entidad.Usuario;
import org.hibernate.Session;

/**
 *
 * @author Sistemas2
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        Session sesion = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public Usuario Login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
