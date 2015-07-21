/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranzas.dao;

import cobranzas.models.Empresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sistemas2
 */
@Stateless
public class EmpresaDAO extends AbstractDAO<Empresa> {
    @PersistenceContext(unitName = "cobranzasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaDAO() {
        super(Empresa.class);
    }
    
}
