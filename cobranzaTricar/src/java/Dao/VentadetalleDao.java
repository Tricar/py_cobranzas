/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Ventadetalle;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface VentadetalleDao {
    
    public boolean insertar(Session session, Ventadetalle tventadetalle) throws Exception;
    public List<Ventadetalle> getAll(Session session) throws Exception;
    
}
