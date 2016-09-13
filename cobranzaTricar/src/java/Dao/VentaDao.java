/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Venta;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface VentaDao {
    
    public boolean insertar(Session session, Venta tventa) throws Exception;
    public Venta getUltimoRegistro(Session session) throws Exception;
    
}
