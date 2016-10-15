/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.*;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface SubmenuDao {
    
    public boolean registrar(Session session, Submenu perfil)throws Exception;
    public List<Submenu> verTodo(Session session) throws Exception;
    public List<Submenu> verTodosxId(Menu menu);
    public List<Submenu> verTodoByMenu(Session session, Integer menu) throws Exception;
    public Submenu verByCodigo(Session session, Integer idperfil) throws Exception;
    public Submenu verByDescripcion(Session session, String descripcion) throws Exception;
    public Submenu verByID(Session session, Integer idsubmenu) throws Exception;
    public Submenu verByDifer(Session session,Integer idperfil, String descripcion) throws Exception;
    public boolean modificar(Session session, Submenu perfil) throws Exception;
    public boolean eliminar (Session session, Submenu perfil) throws Exception;
    public void modificarSolo(Submenu submenu);
}
