/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Menu;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface MenuDao {
    
    public boolean registrar(Session session, Menu perfil)throws Exception;
    public List<Menu> verTodo(Session session) throws Exception;
    public Menu verByCodigo(Session session, Integer idperfil) throws Exception;
    public Menu verByDescripcion(Session session, String descripcion) throws Exception;
    public Menu verByPerfilDifer(Session session,Integer idperfil, String descripcion) throws Exception;
    public boolean modificar(Session session, Menu perfil) throws Exception;
    public boolean eliminarPerfil (Session session, Menu perfil) throws Exception;
    
}
