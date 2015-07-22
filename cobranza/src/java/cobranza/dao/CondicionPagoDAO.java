/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.dao;

import cobranza.models.CondicionPago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sistemas2
 */
@Stateless
public class CondicionPagoDAO extends AbstractDAO<CondicionPago> {
    @PersistenceContext(unitName = "cobranzaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CondicionPagoDAO() {
        super(CondicionPago.class);
    }
    
}
