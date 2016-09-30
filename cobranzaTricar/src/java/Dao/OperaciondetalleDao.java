/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Operacion;
import Model.Operaciondetalle;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface OperaciondetalleDao {
    
    public boolean insertar(Session session, Operaciondetalle operaciondetalle) throws Exception;
    public List<Operaciondetalle> verTodosxId(Integer idoperacion);
    public List<Operaciondetalle> getAll(Session session) throws Exception;
    public List<Operaciondetalle> mostrarSoloDetallexCompra(Operacion compra);
    public Operaciondetalle verByCodigo(Session session, Integer codigo) throws Exception;
    public boolean eliminar(Session session, Operaciondetalle perfil) throws Exception;
    public boolean eliminarOD(Integer codigo);
    
}
