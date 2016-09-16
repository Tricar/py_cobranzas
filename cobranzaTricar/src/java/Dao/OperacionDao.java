/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface OperacionDao {
    
    public boolean insertar(Session session, Operacion operacion) throws Exception;
    public Operacion getUltimoRegistro(Session session) throws Exception;
    
}
