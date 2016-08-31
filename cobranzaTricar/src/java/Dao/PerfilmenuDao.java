/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Perfilmenu;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ricardo
 */
public interface PerfilmenuDao {
    
    public boolean registrar(Session session, Perfilmenu perfil)throws Exception;
    public List<Perfilmenu> verTodo(Session session) throws Exception;
    public Perfilmenu mostrarRequisitosXCred(Integer menu); 
    public List<Perfilmenu> verTodoByPerfilmenu(Session session, Integer menu) throws Exception;
    public Perfilmenu verByCodigo(Session session, Integer idperfil) throws Exception;
    public Perfilmenu verByDescripcion(Session session, String descripcion) throws Exception;
    public Perfilmenu verByPerfilDifer(Session session,Integer idperfil, String descripcion) throws Exception;
    public boolean modificar(Session session, Perfilmenu perfil) throws Exception;
    public boolean eliminarPerfil (Session session, Perfilmenu perfil) throws Exception;
    
}
