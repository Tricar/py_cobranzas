/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Soporte;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Dajoh
 */
public interface SoporteDao {
    public boolean registrar(Session session, Soporte soporte)throws Exception;
    public List<Soporte> verTodo(Session session) throws Exception;
    public List<Soporte> cargarxEstado (Integer estado);
    public Soporte verById(Session session, Integer idsoporte) throws Exception;        
    public Soporte verByCodigos(Integer idsoporte);
    public Soporte verByDistrito(Session session, String reporte) throws Exception;
    public Soporte verByDistritoDifer(Session session,Integer idsoporte, String reporte) throws Exception;
    public boolean modificar(Session session, Soporte soporte) throws Exception;
    public boolean eliminar (Session session, Soporte soporte) throws Exception;
}
