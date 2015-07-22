/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.dao;

import cobranza.models.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sistemas2
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {
    @PersistenceContext(unitName = "cobranzaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public Usuario getLogin(String usuario, String clave){
        try {
            Query query = em.createNamedQuery("Usuario.findLogin");
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            return (Usuario)query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
