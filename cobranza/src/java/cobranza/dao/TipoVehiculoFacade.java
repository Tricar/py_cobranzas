/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobranza.dao;

import cobranza.models.TipoVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sistemas2
 */
@Stateless
public class TipoVehiculoFacade extends AbstractDAO<TipoVehiculo> {
    @PersistenceContext(unitName = "cobranzaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoVehiculoFacade() {
        super(TipoVehiculo.class);
    }
    
}
