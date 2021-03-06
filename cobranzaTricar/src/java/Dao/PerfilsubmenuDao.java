/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfilmenu;
import Model.Perfilsubmenu;
import Model.Submenu;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface PerfilsubmenuDao {
    
    public void insertar(Perfilsubmenu perfsubmenu);
    public Perfilsubmenu verTodos(Perfilmenu perfilmenu, Submenu submenu);
    public List<Perfilsubmenu> verTodo(Session session) throws Exception;
    public List<Perfilsubmenu> verTodoByPerfilsubmenu(Session session, Integer menu) throws Exception;
    public Perfilsubmenu verByCodigo(Session session, Integer idperfil) throws Exception;
    public Perfilsubmenu verByDescripcion(Session session, String descripcion) throws Exception;
    public Perfilsubmenu verByPerfilDifer(Session session,Integer idperfil, String descripcion) throws Exception;
    public boolean modificar(Session session, Perfilsubmenu perfil) throws Exception;
    public void modificarSolo(Perfilsubmenu perfsubmenu);
    public boolean eliminarPerfil (Session session, Perfilsubmenu perfil) throws Exception;
    public List<Perfilsubmenu> submenus(int idperfil);
    public List<Perfilsubmenu> verPorMenu(int idperfilmenu);
    public List<Perfilsubmenu> verSubMenu(int idsubmenu);
}
